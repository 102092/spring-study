

## 2부

- `mvn package` 를 통해서 `jar` 파일 생성(snap shot)
- 이 파일은 `java -jar` 명령어를 통해서 실행 시킬 수 있음. 실행시키면 IDE에서 Springboot 실행시키는 것과 똑같이 실행됨.

- IDE에서 생성시킬수도, 혹은 spring.io에서도 가능
- main application의 추천하는 위치는 최상위 패키지에 있어야함
  - default 패키지 이하 부터 componet scan을 하기 때문



## 3부

##### 의존성 관리 이해

- spring-boot-dependencies-2.2.5...
  - spring-boot-parent-2.2.5...
    - 내 프로젝트!

- 단계식으로 의존성을 만들어 놓으면, 결과적으로 개발자가 관리해야할 의존성이 줄어듬. 
- 그리고 관리하는 의존성도 굉장히 핵심적인 것들만 관리할 수 있게 됨.
- 만약에 Pom.xml 지원하지 않는 의존성 같은 경우에는 버젼을 명시해야 하지만, Spring boot 가 관리하는 의존성 목록에 있다면, 굳이 버젼을 명시하지 않아도 됨.
- Spring boot 에서 의존성을 관리하는 방법은 `<parent></parent>` 를 사용하는 방법

- dependencymanagement setting도 의존성을 관리할 수 있는 방법이지만 parent로 받아야, 더 많은 기본세팅이 가능함.

  - <u>우리만의 계층 구조? depency를 사용할 일이 있을까?</u>

  

##### 의존성 관리 응용

- 스프링 부트가 버젼관리를 해주면 IDE 왼쪽에 **화살표** 를 가지고 있는 표시가 뜸.
- 기본적으로 버젼을 명시해주는 것이 best practice
- 스프링 부트에서 버젼을 바꿔주고 싶으면 `<properties>` 를 이용하여 바꿈
  - 현재 자신의 프로젝트에 주입되고 있는 의존성 파일에서, 오버라이드 하고 싶은 것들을 가져와서 특정 버젼으로 바꿔주면 됨.



##### 자동 설정 이해

- **@SpringBootApplication** 아래 annotation의 일련의 집합
  - @SpringBootConfiguration
  - @ComponentScan
    - @Componet라는 어노테이션이 달린 class들을 스캔해서 bean으로 등록하는 것.
    - @Configuration, @Repository, @Service, @Controller 모두 등록
    - 다른 패키지에서까지 search 하진 않음. 즉 package를 주의해야함
  - @EnableAutoConfiguration 
    - ComponentScan으로 등록된 bean들 외의 추가적으로 읽어서 등록을 하는 것
    - spring.factories key 쭉 봐서, condition이 맞으면 bean을 등록하는데 , 이 등록할 때도 bean이 있으면 등록안하고, bean이 없으면 등록함.
    - 조건에 따라 적용이 됨.
- Configuration
  - bean을 등록하는 java 설정 파일

- annotation 위치는 상관 없음.



##### 자동 설정 만들기 1부,2부

- 프로젝트를 명명하는 약속도 있고.
- META-INF
  - spring.factories
- Autoconfiguration 
  - 다른 프로젝트에서 생성한 빈? 을 가져와서 쓸 수 있게 해줌.

- AutoConfiguration으로 주입한 빈을 같은 이름으로 현재 프로젝트에서 재설정할 수 없음.
  - 해결 : application.properties에서 `spring.main.allow-bean-definition-overriding=true` 설정을 통해 main에서 override 하게 했음.
  - 오류 없이 실행은 되지만, 내가 설정한 빈이 우선시 되지 않음.
  - 즉 이미 주입된 bean 우선됨.
- @ConditionalOnMissingBean
  - 이 annotation은, 아래 작성된 타입의 bean이 없으면 이 bean을 등록해라.
  - 즉 이 bean 등록이 후순위로 밀리는 효과?
  - 이러한 방법이 커스터마이즈 하는 기본적인 방법.

- 주입된 빈에서 일부만 바꾸고 싶으면?
  - @EnableConfigurationProperties 을 사용한다.
  - 빈을 주입받지만, 해당 속성들을 해당 프로젝트에서 properties에서 정의한 내용으로 대체하는 것.