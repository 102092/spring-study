# actuator intro

- App 환경을 살펴볼 수 있는 EndPoint를 제공함.

- conditions : 자동설정 관련
- httptrace : 최근 100개의 http 요청들을 보여준다.
- info : app 관련 임의의 정보
- loggers : 어떤 패키지가 어떤 로깅 레벨을 가지고 있는지, 수정할 수 도 있음.
  
  - 즉 운영중에 로깅 레벨을 수정할 수 있음.
- liquibase : flyway랑 비슷
- metrics : app의 핵심이 되는 정보들(memory, cpu)
  
- 특정 수치가 넘으면, 알림을 발생시키도록 할 수도 있음.
  
- mappings : controller mapping 정보

- scheduledtasks : 주기적으로 돌리는 annotation에 해당하는 정보

- shutdown : app 종료를 지원해줌

  - 기본적으로 비활성화 되어있음.

- threadDump

  

  ## /actuator

- hateoas 형식으로 알려준다

- hypermedia as the engine of application state
  
  - 현재 리소스와 연관되어 있는 링크 정보들을 만들어줘라

- 보안적인 이슈 때문에 web에서는 health와 info 정보만 보여주고 있음

## 참고

- https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-endpoints 

# 2부

- `jconsole` 입력

  - JMX 실행됨. Java Monitoring , Management Console

- Endpoint가 좀 더 많다

- `jvisualvm`

  - 다운받아서 실행해야함
  - 플러그인 지원되는 것이 있어서,`jconsole` 보다 보기 좋을 때 있음.

- 웹에서 보고 싶을 때

  ```properties
  management.endpoints.web.exposure.include=*
  management.endpoints.web.exposure.exclude=env,beans
  ```

  



# 3부

- Spring boot Admin : 제 3자가 제공하는 오픈소스 프로젝트

  - actuator 정보를 ui에서 확인할 수 있는.

- spring boot version과 의존성 버젼을 잘 확인해야할듯

- 서버 설정

  ```java
  <dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-server</artifactId>
    <version>2.0.1</version>
  </dependency>
  ```

  - 민간한 정보들이 있을 수 있기 때문에, 보안이 필요할 듯
  - 스프링 시큐리티를 꼭 설정해줘야 함
  - 특정 url 밑으로는, 특정 권한만 가진 유저만 보도록





- client 설정

  ```
  <dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>2.0.1</version>
  </dependency>
  ```

  

