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