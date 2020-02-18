# spring-study



## 목표

- 로드맵 : https://www.inflearn.com/roadmaps/8

- 백기선님의 스프링 강좌를 따라하면서, **스프링부트** 전반적인 내용을 공부하기를 목적.

  - 19.09.27

  1. Spring boot 의 전반적인 흐름을 이해하고,
  2. 핵심적인 Annotation을 정리하고,
  3. 앞으로 배워나갈 기초를 확립한다.

- 예정강좌
  1. 스프링 프레임워크 핵심기술
  2. 스프링 부트 개념과 활용....



## 목록



## 스프링 프레임워크 핵심기술

### IOC container, bean

- Inversion Of Control

#### Ioc-9

- `ApplicationContext extend ResourceLoader`
  1. 파일 시스템 읽어오고,
  2. 클래스 패스에서 릭어오고
  3. URL로 읽어오고
  4. 상대/절대 경로에서 읽어올 수 있는 기능을 가지고 있음.
- 접두어
  - classpath



### Resource/Validation

#### Resource 추상화

- org.springframework.core.io.Resource

- java.net.URL를 Resource 라는 클래스로 감싸서, 실제로 low level 에 있는 resource에 가져 올 수 있도록 기능들을 구현해놓은것.
- 왜?

  - 기존에 있는 java.net.URL 클래스가 classpath 기준으로 가져오지 않아서.
- 그래서

  - Resource로 통일.
  - 즉 자원을 가져오는 방법을 하나(Resource class)로 통일했음.
- Resource를 가져올 때, 항상 존재(exits) 한다고 가정하지 않음.

  - 즉 boolean으로 판단할 수 있음.
- 구현체

  - URLResource
  - ClassPathResource
    - classPath?
      - JVM이  `class` 이 파일을 찾는 기준이 되는 경로
      - Spring에서는 classpath를 통해 resource를 가져옴.
      - 추가해주기 위해서는, project settings-models에서 해당 폴더를 resource에 등록시켜야 springd이 찾을 수 있음.
  - FileSystemResource
  - SevletContextResource : 웹 애플리케이션 에서 상대경로로 리소스를 찾는 클래스

    - 가장 많이 사용
    - 
  - 읽어 들이는 Resource type은 ApplicationContext 타입에 따라 결정됨.
    - ClassPathXmlApplicationContext -> ClassPathResource
    - FileSystemXmlApplicationContext -> FileSystemResource
    - WebApplicationContext -> SevletContextResource
- ApplicationContext를 주입해서 실행하면 기본적으로는 WebApplicationContext type으로 리소르를 찾게되고(SevletContextResource)
  - 찾고싶은 파일에 만약에 접두어 classpath가 붙는다면
  - ClassPathXmlApplicationContext를 통해 ClassPathResource type으로 리소스를 찾게된다.

- 접두어를 사용해서, resource를 찾는 것을 추천.
  - 왜?
  - **명시적**이니까 다른 사람들이 봐도, 그 부분만 봐도 알 수 있으니까.

- 스프링 부트 기반은 classpath 기준으로 resource를 찾는다.
  - 그냥 이름만 적으면, SevletContextResource로 찾게된다.



#### Validation 추상화

- org.springframework.validation.Validator

- 검증할 때 사용하는 인터페이스
- 웹, 서비스, 데이터 등등 모두 사용할 수 있는 일반적인 인터페이스
- Spring boot 2.0.5 이상 버전이면
  - validator bean을 자동적으로 등록, 사용할 수 있음.

- annotation으로 검증할 수 있는 간단한 것들은 이미 정의된 validator를 통해 검증할 수 있음.



