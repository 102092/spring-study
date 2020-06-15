# 2부 : 인메모리 데이터베이스

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



## 3부 MySql

### DBCP

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



### HikariCP

- 스프링 부트가 기본적으로 선택한 DBCP
- 기본적인 Timeout 설정은 30초



### MySQL

```xml
<dependency>
   <groupId>mysql</groupId>
   <artifactId>mysql-connector-java</artifactId>
</dependency>
```

- DataSource Driver의 구현체

> 참고

- https://github.com/brettwooldridge/HikariCP#frequently-used



### docker

- 컨테이너
- 커널을 공유하기 때문에, 설치를 빠르게 할 수 있다.



- mariaDB 커뮤니티 버젼 사용시, 소스코드 공개의무가 생길 수 있음.
- 그러므로 PostgreSQL을 사용하는게 나을 수도