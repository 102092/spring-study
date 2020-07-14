# 2부 인메모리 데이터베이스

- `h2database`

```java
import java.sql.Connection;
import java.sql.Statement;
import javax.sql.DataSource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class H2Runner implements ApplicationRunner {

  private final DataSource dataSource;

  private final JdbcTemplate jdbcTemplate;

  public H2Runner(DataSource dataSource, JdbcTemplate jdbcTemplate) {
    this.dataSource = dataSource;
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Connection connection = dataSource.getConnection();
    System.out.println(connection.getMetaData().getURL());
    System.out.println(connection.getMetaData().getUserName());

    Statement statement = connection.createStatement();
    //language=sql
    String sql = "CREATE TABLE USER (ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id)); ";
    statement.execute(sql);

    jdbcTemplate.execute("INSERT INTO USER VALUES (1, 'han')");

  }
}
;

```



# 3부 MySql

## DBCP

- 스프링부트가 제공하는 데이터베이스와 연결에 필요한 커넥션 풀
- 미리 커넥션 풀을 만들어놓고, 커넥션이 필요할 때마다 이 풀에서 가져오는 작업임.
- 왜? 이러한 커넥션을 생성하는데, 생각보다 많은 자원을 소모하기 때문에.
- DBCP 성능이 application 성능에 많은 부분을 차지하고 있음.



- properties 설정은..

```properties
spring.datasource.hikari.*
spring.datasource.tomcat.*
spring.datasource.dbcp2.*
```



## HikariCP

- 스프링 부트가 기본적으로 선택한 DBCP
- 기본적인 Timeout 설정은 30초



## MySQL

```xml
<dependency>
   <groupId>mysql</groupId>
   <artifactId>mysql-connector-java</artifactId>
</dependency>
```

- DataSource Driver의 구현체

> 참고

- https://github.com/brettwooldridge/HikariCP#frequently-used



## docker

- 컨테이너
- 커널을 공유하기 때문에, 설치를 빠르게 할 수 있다.



- mariaDB 커뮤니티 버젼 사용시, 소스코드 공개의무가 생길 수 있음.
- 그러므로 PostgreSQL을 사용하는게 나을 수도



# 4부 PostgreSQL

```
docker run -p 5432:5432 -e POSTGRES_PASSWORD=pass -e POSTGRES_USER=keesun -e POSTGRES_DB=springboot --name postgres_boot -d postgres

docker exec -i -t postgres_boot bash

su - postgres

psql -U keesun springboot

데이터베이스 조회
\list

테이블 조회
\dt

쿼리
SELECT * FROM account;
```



# 5부 Spring data JPA 개요

## ORM

- 객체와 관계(DB)를 맵핑할 때 발생하는 개념적인 불일치를 어떻게 해결할 것인가에 대한 해결책을 제공하는 **프레임워크** 
- 개념적인 불일치?
  - 객체는 크기가 굉장히 다양함
  - 그에 반해 테이블은 크기가 굉장히 한정적이다.
  - 그럼 어떻게 복잡한 객체를 테이블에 매칭시킬 수 있을 것인가?
  - 그리고 테이블은 상속이 없는데, 객체는 상속이 있다! 이 부분을 어떻게 구현할 것인가?



## JPA

- Java Persistence API

- 대부분의 JPA spec이 Hibernate를 기반으로 만들어져있음.
- ORM을 위한 자바 (EE) 표준을 의미



## Spring data jpa

- JPA 표준 스펙을 아주 쉽게 사용할 수 있게끔, Spring data 로 추상화 시켜놓은 것.
- JPA 구현체인 Hibernate를 사용해서
- JPA는 EntityManager로 감싸서 사용하게 됨.

- Spring data JPA -> JPA -> Hibernate -> Datasource
  - Jdbc 를 사용할 수도 있으면서
  - 추가적으로 Hibernate..등에서 제공하는 기능들도 사용할 수 있다는 장점이 있음!



# 6부 Spring data JPA 사용

## @DataJpaTest

- **SlicingTest**
- 이 테스트를 진행할 때는 반드시 인메모리 데이터베이스가 필요하다.

- 인메모리 DB가 아닌 데이터베이스를 사용하는 경우에는 URL을 명시해줘야한다.



## @SpiringBootTest

- 통합 테스트
- 왜? 
  - 스프링 부트에 있는 모든 annotation이 다 등록된다.
  - 모든 빈설정을 다한다

- 권장되진 않는다.
  - 테스트를 돌릴 때는 embbedd db로 돌리는게 훨씬 빠르다.



## @Query

- nativeQuery 옵션을 줘서 사용할 수도 있고
- 기본값은 JPQL이다



# 7부 데이터베이스 초기화

## JPA

- spring.jpa.hibernate.ddl-auto

  - create : 초반에 새로 생성(데이터 유지 안됨, 운영상황에서 절대 이옵션 아용하면 안됨)

  - create-drop : 생성-삭제(데이터 유지 안됨)

  - update : 기본 스키마 나두고, 추가된 것만 변경

    - 개발할때 많이 사용하는 옵션

  - validate : 검증하는! ddl에 변경을 가하진 않음

    ```properties
    spring.jpa.hibernate.ddl-auto=validate
    spring.jpa.generate-ddl=false
    ```

    - 이 옵션이 운영 상황에서는 훨씬 안정적이다.

- spring.jpa.generate-ddl = true
  
- 자동으로 스키마 생성됨
  
- `Schema-validation: missing column [email] in table [account]`
  
- validate 옵션에서 어떤 컬럼을 매칭할 수 없을 때 발생하는 오류
  
- update

  - `Hibernate: alter table if exists account add column email varchar(255)`
  - 컬럼명이 변경된 것 같은 경우 인식하지 못한다.
  - 컬럼이 새로 생성, 삭제된것만 알 수 있음.

- 테스트에서 사용하려면

  ```properties
  #spring.jpa.hibernate.ddl-auto=validate
  #spring.jpa.generate-ddl=false
  ```

  - 이 옵션이 없어야 할듯



## SQL 스크립트

- schema.sql 또는 schema-${platform}.sql

- data.sql 또는 data-${platform}.sql
- ${platform} 값은 spring.datasource.platform 으로 설정 가능.



# 8부 데이터베이스 마이그레이션

## flyway

- DB 변경도 버전관리하듯이 관리할 수 있다

- 도커는 컨테이너가 사라지만 데이터가 날아간다
- **한번 적용된 스크립트 파일은 절대 건드리면 안된다.** 
  - 어떠한 파일이던지 새로 만들어서 적용해야한다.
- 데이터 조작도 가능하다



## flyway-디렉토리

- db/migration 또는 db/migration/{vendor}
- spring.flyway.locations로 변경 가능



## flyway-파일 이름

- V숫자__이름.sql
- V는 꼭 대문자로.
- 숫자는 순차적으로 (타임스탬프 권장)
- 숫자와 이름 사이에 언더바 **두 개**.
- 이름은 가능한 서술적으로.



# 9부 Redis

- NoSQL
- 기본 포트는 6379포트
- 레디스...



## 설치 및 실행

```
docker run -p 6379:6379 --name redis_boot -d redis
docker exec -i -t redis_boot redis-cli
```

- -d : demon
- -i : interactive
- -t : target



## 주요 커맨드

- keys *
- get {key}
- hgetall {key}
- hget {key} {column}

## 참고

- https://projects.spring.io/spring-data-redis/
  - StringRedisTemplate 또는 RedisTemplate
  - extends CrudRepository
- https://redis.io/commands



# 10부 MongoDB

- @Document(collection = "account")
  - collection이 table 이름 정도

- 내장형 몽고 DB도 있다!

## 설치 및 실행

```
docker run -p 27017:27017 --name mongo_boot -d mongo
docker exec -i -t mongo_boot bash
mongo
```



## @DataMongoTest

- slicingTest



# 11부 Neo4j

- 링크타서 찾는 경우 유리한 DB
  - 다시 말해 노드간의 연관관계를 영속화?? 하는데 유리한 그래프 데이터베이스!
- 하위 호환성이 전혀 좋지않다.
  - 별로임



## 설치 및 실행

```
docker run -p 7474:7474 -p 7687:7687 -d --name noe4j_boot neo4j
http://localhost:7474/browser
```



# 12부 정리

- HikariCP : Datasource Pool
- spring.jpa.generate-ddl = false로 바꿔줘야, ddl auto option이 적용이 된다.
- open-session-view : view를 랜더링하는 것과 관련이 있음
  - spring web mvc에서 view를 랜더링하는 과정이 있음.
  - 기본적으로는 true option



# 시큐리티 1부

- 401 : unauthorized

- spring security를 추가하면, 모든 요청에서 인증이 필요하게 된다.

- 이 요청이 원하는 응답의 형태 (accept header)에 따라 ....

  - 기본 accept header가 text

  ```java
    @Test
    void hello() throws Exception {
      mockMvc.perform(get("/hello")
          .accept(MediaType.TEXT_HTML)) //요청을 보낼때 설정된 accept header
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(view().name("hello"));
    }
  
    @Test
    void my() throws Exception {
      mockMvc.perform(get("/my"))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(view().name("my"));
    }
  ```

  - `hello()`는 응답이 302 , redirect해서 spring security에서 제공해주는login page로 보내고 있다
  - `my()` Basic Authenticate header가 응답에 담겨져서 오고 있음

- security에서 자동 제공하는 user / password 있음
  - `Using generated security password: e69b6e40-ea6a-4aa7-9068-a060fa448d53`
- Spring Security starter가 등록되었을 때, 추가되는 의존성
  - Event Publisher
- 시큐리티 유저 관련된 설정 --> `UserDetailsServiceAutoConfiguration`
  - InMemory에 유저 하나를 만들어줌.
- 시큐리티 관련 기본 설정 --> `WebSecurityConfigurerAdapter`
- 스프링 부트에서 제공하는 시큐리티 starter는 진짜로 쓸 필요가 없어진다.



- sping-security-test

  ```xml
  
  <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-test</artifactId>
      <version>5.2.4.RELEASE</version>
      <scope>test</scope>
  </dependency>
  
  ```

  - `@WithMockUser`



# 시큐리티 2부

- starter-security를 사용하면 기본적으로 모든 요청에 인증을 요구한다
- `UserDetailsService` 를 직접 구현해야한다.
- `java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"`
  - 다양한 passwordEncoder가 있음.
  - Encoding을 하지 않고 패스워드를 저장하면, 심각한 보안이슈가 발생할 수 있음.
- `PasswordEncoder`
  - `han password : {bcrypt}$2a$10$6RZzv8R7qz6cg/OB4.p1DeN4mJLgfTN4saM9xfEDwWGOVSDUUrqMm`