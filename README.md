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



### 데이터 바인딩

#### 데이터 바인딩 추상화 : PropertyEditor

- 사용자가 입력할 값을, 동적으로 할당하는 것을 의미함.
- 왜 바인딩?
  - 입력값이 보통 `string` 
  - 그런데 도메인 객체는 숫자로 혹은 boolean등등 변환이 필요한 경우가 있기 때문에.
  - 이런 기능을 하는 것을 데이터 바인딩이라 하고 함.
- 스프링에서는 DataBinder Interface가 있음.

- 웹 MVC에서 주로 사용.

- setValue, getValue
  - 에서 공유하고 있는 Value는 공유 한다.
  - 즉 스레드 세이프 하지 않음.
  - 즉 이 클래스를 Bean으로 등록해서 쓰면 안된다.
  - PropertyEditor는 **절대로** bean으로 등록하면 안됨.



#### 데이터 바인딩 추상화 : Convertor, Formatter

- PropertyEditor 대신에 사용할 수 있는 코드

  ```java
  import org.springframework.core.convert.converter.Converter;
  
  public class EventConverter {
  
    public static class StringToEventConverter implements Converter<String, Event> {
  
      @Override
      public Event convert(String s) {
        return new Event(Integer.parseInt(s));
      }
    }
  
    public static class EventToStringConverter implements Converter<Event, String> {
  
      @Override
      public String convert(Event event) {
        return event.getId().toString();
      }
    }
  
  }
  ```

- 상태정보가 없기에, bean으로 등록해서 사용해도 상관없음.

- bean으로 등록하는 것이 아닌, Registry를 통해서 관리해주는 방법도 있음.

- primitive type이면 기본적인 것들은 converter해줌

- i18n , 메세지 다국화.



- Formatter

  ```java
  package me.donghwan.demospringioc.databind;
  
  import java.text.ParseException;
  import java.util.Locale;
  import org.springframework.format.Formatter;
  
  //thread safe
  public class EventFormatter implements Formatter<Event> {
    
    @Override
    public Event parse(String s, Locale locale) throws ParseException {
      return new Event(Integer.parseInt(s));
    }
  
    @Override
    public String print(Event event, Locale locale) {
      return event.getId().toString();
    }
  }
  ```

  - bean으로 등록해도 됨. 왜? thread safe해서
  - formatter를 관리하는 Registerd에 등록해줘도 되구.

- Conversion Service
  - 인터페이스
  - 이를 통해 converter, formatter들은 thread safe 하게 이용할 수 있음.



## SpEL

- Sping Expression Language

  - Jsp에서 사용하는 expression language 를 사용하기 위해 

  ```jsp
  <C:....>
  </C:....>
  ```

- Spring Security 뿐만 아니라 여러 많은 Spring 에서 사용됨.

- 가장 간단한 예제

  ```java
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.boot.ApplicationArguments;
  import org.springframework.boot.ApplicationRunner;
  import org.springframework.stereotype.Component;
  
  @Component
  public class AppRunner implements ApplicationRunner {
  
    @Value("#{1 + 1}") //spel 표현식
    int value;
  
    @Value("#{'hello' + ' world'}")
    String greeting;
  
    @Value("#{1 eq 1}")
    boolean trueOrFalse;
    
    @Value("hello")
    String hello;
    
    @Value("${my.value}") //application.properties 에 있는 값을 주입받는 연산자
    int myValue;
  
  
    @Override
    public void run(ApplicationArguments args) throws Exception {
      System.out.println("================");
      System.out.println(value); //2
      System.out.println(greeting); //hello world
      System.out.println(trueOrFalse); // true
      System.out.println(hello); //hello
    }
  }
  ```

  - Springboot가 실행될 때, 아래 내용이 등록되어서 실행됨.
  - 표현식 : #{"....."}
  - 프로퍼티 : ${"......"}
  - 주의할점
    - 표현식 안에서는 프로퍼티 사용 가능
    - 그렇지만 프로퍼티 내부에서는 표현식 사용불가.

- SpEL reference를 한번 훝어보자.
- 장점 : 메서드를 호출 하는 기능이 있음.
  - 무슨말?
- 어디서 쓰이고 있는가?
  1. @Value
  2. @ConditionalOnExpression
     - 선택적으로 bean을 등록, 혹은 data를 읽어 들일 때 사용하는 어노테이션.
  3. Spring Data @Query
  4. Thymeleaf에서도 쓸 수 있어
- 문자열에 # 형태 가 붙었으면, Spring Expression이구나라고 이해하면 됨.



### AOP

#### AOP 개념

- 가장 간단하게, 흩어진 aspect를 module화 할 수 있는 프로그래밍 **기법** 
  - OOP도 기법임.

- 중복된 코드, 부분을 aspect를 통해서 하나로 모은다.
- 해야할 일과, 그 일을 어디에 전송해야하는 지를 묶어서 module화 하는 것을 AOP임.
- Aspect는 하나의 모듈을 일컫는 말.
  - 이 모듈안에 advice와, pointcut이 들어감
  - advice는 해야할 일들
  - pointCut은 어디에 적용해야하는지를 기억함.
    - 합류 지점.
    - A classs에 example method를 실행할 때 합류해라 라고 알려주는 것.
  - joinPoint?
    - 메서드 실행 시점에서 사용.
    - 메서드를 실행할 때, 이 advice를 끼어들어라고 말하려 할 때, 그 끼어드는 시점을 이야기함.
  - target 적용이 되는 대상
- joinCut vs pointCut
- AOP 적용 방법
  1. 컴파일 시에
     - class 파일이 만들어졌을 때 무언가 바꿔서 컴파일을 하게 만들고.
     - *컴파일을 다시해야함.*
  2. 로딩 시에
     - 클래스를 로딩하는 시점에 
     - byteCode는 그대로 이지만, JVM에 올라갔을 때는 무언가 끼어 넣어서 실행됨.
     - *로딩 시점에 약간의 부하가 생김.*
     - *로드 타임 위버 설정을 해줘야함*
  3. 런타임 시에
     - A 라는 bean을 만들 때, 
     - A 라는 타입의 proxy bean을 만들어서
     - proxy bean이 실제 A가 가지고 있는 foo라는 메서드가 실행하기 직전에, 원하는 메서드를 실행시키도록 하고
     - 그 다음에 나머지가 실행됨.
     - 주로 스프링에서 사용되는 방법
     - bean을 만드는 시에는 약간의 부하
     - 그렇지만 요청이 들어올 때마다 부하가 걸리는 것이 아님. == 로딩시와 부하가 비슷할듯.
     - 그렇지만 아무런 설정을 하지 않아도 됨.
     - 문법이 쉬움! --> 별도의 AOP용 공부를 많이할 필요가 없음.

- AspectJ
  - 자유도 높음
  - 반면 Spring AOP는 제한되어있음.

