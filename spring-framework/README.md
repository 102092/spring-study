### IoC 컨테이너

- IoC : Inversion of Control

  - 의존 관계 주입.
  - 특정 객체가 사용하는 의존 객체를 **직접** 만들어서 사용하는 것이 아니라, 주입 받아 사용하는 방법을 의미.
  - 왜 사용?
    - 무수히 많은 개발자들의 의견을 바탕으로 정립된 방법.

- 의존관계를 주입받는 방법은?

  - 주입을 받으려면 , 그 객체가 빈으로 등록되어있어야함

  1. 생성자를 통해
  2. `@Autowired` 어노테이션 사용
     - Pojo객체를 빈으로 등록할 때!
     - 또한 빈으로 등록되어있는 객체를 주입받으려 할때

#### 1. Bean

- 컨테이너 안에 들어있는 객체를 일컫는 말.
  - 컨테이너가 관리하는 객체
- 스프링 IoC에 등록되는 빈들은 기본적으로 **싱글톤 스코프**로 등록됨.
- Bean Factory
  - spring ioc container에 가장 최상위에 있는 interface.
  - 가장 핵심.
  - 가장 중요한 메서드는 ? `getBean`

- 장점
  1. 의존성관리
  2. 싱글톤
  3. 라이프 사이클 인터페이스를 지원
     - 어떤 bean이 만들어졌을 때, 추가적인 작업을 하고 싶다.

- Pojo

- Singleton

  - 하나의 객체만 만들어서 사용하는 것.
    - 코드에서 보면 `BookService` 에서, 주입다는 `BookRepository` 객체에서 생성된 인스턴스는 싱글톤 타입으로, 한번만 만들어짐.
    - 그러면 효율적.
    - 왜? <u>메모리 면에서도 효율</u>적이고, 이미 만들어진 객체를 사용하기 때문에 <u>런타임시 성능 최적화에도 유리</u>하다.

- singleton이 아닌 것은? 
  - Prototype : 매번 다른 객체로 만들어서 사용하는 것.



#### 2. ApplicationContext

- 인터페이스

- Bean factory 상속받음.
- `Message Source` 다국화 메세지용 기능...



- xml

  - ClassPathXmlApplicationContext
  - 빈들을 주입하는 것이 굉장히 귀찮음.



- @Component

  - bean으로 등록하는 어노테이션
  - 이 어노테이션이 확장된 것이 @Servie, @Repository...
  - `ComponentScan(basePackageClasses = DemoApplication.class)`
  - [참고](https://galid1.tistory.com/494)



- @Configuration

  - 어노테이션 환경설정을 돕는 어노테이션.

  - 클래스가 한개 이상의 @Bean이 등록될것으로 기대됨.

  - [참고]([http://tech.javacafe.io/spring/2018/11/04/%EC%8A%A4%ED%94%84%EB%A7%81-Configuration-%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98-%EC%98%88%EC%A0%9C/](http://tech.javacafe.io/spring/2018/11/04/스프링-Configuration-어노테이션-예제/))



- @SpringBootApplication

  - @componetScan과 @configuration이 포함되어있는 어노테이션.



#### 3. @autowired

- 경우의 수
  1. 해당 타입의 빈이 없는 경우
  2. 해당 타입의 빈이 한 개인 경우
  3. 해당 타입의 빈이 여러개 인 경우
     - 빈 이름으로 시도,
       - 같은 이름의 빈 찾으면 해당 빈 사용
       - 못찾으면 실패.
- 같은 타입의 빈이 여러개 일경우, 
  - 스프링에게 어떤 빈이 먼저 주입될 지,  적절한 어노테이션을 붙어줘야함.
  - @Primary, @Qualifier, bean이름과 같은 이름으로 주입받기 -> 헷갈릴수 있음
  - 이중 전자를 사용하는 것이 낫다.

- 기본적으로 빈의 이름은, 클래스 이름에 소문자로 출발하게됨 
  - ex) `dongBookRepository`



1. `beanFactory` 가 BeanPostProcessor타입의 등록이 되어있는 bean을 찾는다.
2. `AutowiredAnnotationBeanPostProcessor` extends BeanPostProcessor
   - 스프링이 제공하는 @Autowired, @Value 어노테이션 그리고 JSR-330 @Inject 어노테이션을 지원하는 어노테이션 처리기.

동작원리

- 빈을 생성하고

  1. BeanPostProcessor 라이프 사이클 인터페이스의 postProcessBeforeintialization 메서드로 초기화 작업을 수행하고,

  2. ...작업을 수행하고

  3. postProcessAfterintialization 메서드를 실행하고,

  4. IntializingBean 인터페이스의 afterPropertiesSet 메서드를 오버라드 해서 실행

     혹은 @PostConstruct 어노테이션이 적용된 메서드를 실행하는 것.



#### 4. @Component, 컴포넌트 스캔

- Type-safe?
- @ComponentScan
  - 기본적으로 Component를 붙이고 있는 패키지 이하 부터, 스캔을 한다.
  - 그러면 패키지 밖에 있는 것은 스캔이 되지 않는다.
  - 스캔이 되지 않으면 빈으로 등록되지 않고, 그러면 @Autowired되지 않음.



- filter
  - component 스캔을 한다고해서 모든 빈을 등록하진 않음.
  - filter option을 통해, 스캔할 빈의 옵션을 설정할 수 있음. 걸러낼 것들을 정할 수 있다는 말이지.



- @Component 하위요소
  - @Repository
  - @Service
  - @Controller
  - @Configuration
  - 사실상 위의 하위 어노테이션도 모두 @Component라 할 수 있음.



- 구동시간에 민감하다면? function을 사용한 빈을 등록하면 됨. 성능상에 이점 있음.



#### 5. bean scope

- Singleton scope

  - 애플리케이션 전반에 걸쳐서, 해당 빈의 인스턴스가 하나다.

  ```java
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.boot.ApplicationArguments;
  import org.springframework.boot.ApplicationRunner;
  import org.springframework.stereotype.Component;
  
  @Component
  public class AppRunner implements ApplicationRunner{
  
  	@Autowired
  	Single single;
  	
  	@Autowired
  	Proto proto;
  	
  	@Override
  	public void run(ApplicationArguments args) throws Exception {
  		//app전체를 걸쳐서 하나의 인스턴스만 사용하는 것이 singleton type
  		System.out.println(proto);
  		System.out.println(single.getProto());
  		
  	}
  
  }
  ```
  
  - 결과는?

  ```
com.donghwan.demospring51.Proto@3704122f
  com.donghwan.demospring51.Proto@3704122f
  //주소가 같다. 즉 같은 인스턴스에서 비롯되었음을 알 수 있지.
  ```
  
  

- proto type

  ```java
  import org.springframework.context.annotation.Scope;
  import org.springframework.stereotype.Component;
  
  @Component @Scope("prototype")
  public class Proto {
  
  }
  ```
  
  - @Scope를 통해, prototype이라 선언해줬고, 이러면 proto 빈을 받아올 때마다 인스턴스가 새롭게 생성된다.



- Singleton, Prototype

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner{

	@Autowired
	ApplicationContext ctx;
		
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		System.out.println("proto");
		System.out.println(ctx.getBean(Proto.class));
		System.out.println(ctx.getBean(Proto.class));
		System.out.println(ctx.getBean(Proto.class));
		
		System.out.println("singleton");
		System.out.println(ctx.getBean(Single.class));
		System.out.println(ctx.getBean(Single.class));
		System.out.println(ctx.getBean(Single.class));
		
	}

}

//결과
proto
com.donghwan.demospring51.Proto@106faf11
com.donghwan.demospring51.Proto@70f43b45
com.donghwan.demospring51.Proto@26d10f2e
singleton
com.donghwan.demospring51.Single@10ad20cb
com.donghwan.demospring51.Single@10ad20cb
com.donghwan.demospring51.Single@10ad20cb
```



- if 프로토 타입의 빈이 싱글톤 타입의 빈을 참조한다면?

  - 뭐 singleton타입 빈은 하나의 인스턴스만 만들어 졌을 테니까.

- 그러면 싱글톤 타입의 빈이, 프로토타입의 빈을 참조하면?

  - prototype 빈의 역할을 할 수 없음...
  - 이걸 해결해주기 위해서는? 
  - `proxy mode` 를 설정해줘야함
    - 클래스 기반에 proxy로 감싸라. 
    - singleton bean이 직접 참조하지 못하게 하고, 프록시를 거치도록 만들어주는 것.
    - 하나의 장벽, 문을 만들어주는 느낌.

  ```java
  import org.springframework.context.annotation.Scope;
  import org.springframework.context.annotation.ScopedProxyMode;
  import org.springframework.stereotype.Component;
  
  @Component @Scope (value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
  public class Proto {
  
  }
  
  ```
  
  ```java
import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.boot.ApplicationArguments;
  import org.springframework.boot.ApplicationRunner;
  import org.springframework.context.ApplicationContext;
  import org.springframework.stereotype.Component;
  
  @Component
  public class AppRunner implements ApplicationRunner{
  
  	@Autowired
  	ApplicationContext ctx;
  		
  	@Override
  	public void run(ApplicationArguments args) throws Exception {
  		
  		System.out.println("proto by single");
  		System.out.println(ctx.getBean(Single.class).getProto());
  		System.out.println(ctx.getBean(Single.class).getProto());
  		System.out.println(ctx.getBean(Single.class).getProto());
  	}
  
  }
  proto by single
  com.donghwan.demospring51.Proto@632aa1a3
  com.donghwan.demospring51.Proto@20765ed5
  com.donghwan.demospring51.Proto@3b582111
  ```


- 대부분 상황에서는 싱글톤 타입만 사용할 가능성이 높음
- Thread safe한 방식으로 코딩을 하자.



#### 6. IoC 컨테이너 Environmnet

#### 프로파일

- ApplicationContext는 빈과 관련된 기능만 담당하는 것 이 아니다.
- 프로파일이라는 기능도 있음.
- 프로파일은 빈들의 묶음
  
- 언제? 개발환경마다 필요한 빈들이 다를 수 있다. 개발환경, 배포환경...이런식으로 다를 수 있으니까.
  
- @Profile(...)

  - 어떤 환경일때 실행시켜준다는 어노테이션

  ```java
  //클래스에 적용
  @Configuration
  @Profile("test")
  
  @Component
  @Profile("test")
  
  //메소드에 정의
  @Bean
  @Profile("test")
  
  ```

  - `@Profile('!prod')` prod가 아닐 경우에 빈으로 등록되도록 해라.



- 프로파일 표현식
  - ! not
  - & and
  - | or

#### 프로퍼티

- hierarchical 계층적. 
- 만약 프로퍼티가 겹친다면, source에 정의된 properties보다, VM에 정의된 것이 우선되어 나옴



#### 7. MessageSource

- i18 기능을 제공하는 인터페이스

```java
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(3); //캐시하는 시간을 최대 3초까지만 하고 다시 읽도록
		return messageSource;
	}

```

- `ReloadableResourceBundleMessageSource` 사용했기에, 메세지가 출력되는 와중에도 바꿀 수 있어야함



```java
@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	MessageSource messageSource;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		while (true) {
			System.out.println(messageSource.getMessage("greeting", new String[] { "donghwan" }, Locale.KOREA));
			System.out.println(messageSource.getMessage("greeting", new String[] { "donghwan" }, Locale.getDefault()));
			Thread.sleep(1000l); //1초간격으로 찍히도록

		}
	}
```



#### 8. ApplicationEventPublisher

- 인터페이스
- 옵저버 패턴의 구현체
- 이벤트 프로그래밍 할때 유용한 구현체



1. 이벤트 만들기

```java
package com.donghwan.demospring51;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent{
	
	private int data;

	public MyEvent(Object source) {
		super(source);
		
	}
	
	public MyEvent(Object source, int data) {
		super(source);
		this.data = data;
	}
	
	public int getData() {
		return this.data; 
	}

}
```

- ApplicationEvent를 통해 MyEvent를 만든다.



2. 이벤트 발생시키기

```java
@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	ApplicationEventPublisher publishEvent;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		publishEvent.publishEvent(new MyEvent(this, 100));

	}
}
```



3. 이벤트 처리하기

```java
@Component
public class MyEventHandler implements ApplicationListener<MyEvent> {

	@Override
	public void onApplicationEvent(MyEvent event) {
		System.out.println("이벤트를 받았다. 데이터는 "+ event.getData());
		
	}

}

```



4. 이렇게 하면..

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.8.RELEASE)

....
이벤트를 받았다. 데이터는 100
```

- 이렇게 로고가 찍힌다!

- 그렇지만 스프링 4.2부터 조금 바뀌었다? 어떻게? POJO스럽게 작성할 수 있도록





1. 이벤트 생성하기

```java
public class MyEvent {

	private int data;

	private Object source;

	public MyEvent(Object source, int data) {
		this.source = source;
		this.data = data;
	}

	public int getData() {
		return this.data;
	}

}
```

3. 이벤트 처리하기

```java
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventHandler{

	@EventListener
	public void onApplicationEvent(MyEvent event) {
		System.out.println("이벤트를 받았다. 데이터는 "+ event.getData());
		
	}

}

```

- 위와 같은 결과.
- 이렇게 코드를 짜면, 깔끔하고 유지보수하기 쉬워짐. 
  - 왜? 패키지가 import되지 않았고
  - 어노테이션만으로 Event를 다루고 있으니까.



> 하나의 이벤트에, 두 개의 이벤트 리스너에서 받는다면 어떻게 될까?

- 동시에 실행될까? 멀티쓰레드 같이? ..아닐것 같음.

- 그럼 차례대로 실행될것같은데.

  - 차례대로면 어떻게 차례를 줄까?
  - 랜덤으로?
  - `Thread.currentThread().toString()` 를 통해 알아보자

  ```
  Thread[main,5,main]
  Another 100
  Thread[main,5,main]
  이벤트를 받았다. 데이터는 100
  ```

  - 메인쓰레드에서 찍혔음.
  - `@Order` 어노테이션으로 실행차례를 조정할 수 있음.
  - `@Asycn` 를 통해 비동기적으로 실행시킬 수도 있음. 이걸 쓰면 다른 각각 쓰레드 풀에서 실행될 것이고, 스케쥴링에 따라 실행될것이므로 @Order 어노테이션이 무쓸모해질듯.

  ```
  Thread[task-1,5,main]
  Another 100
  Thread[task-2,5,main]
  이벤트를 받았다. 데이터는 100
  ```



- 다양한 이벤트들

```java
@Component
public class MyEventHandler{

	@EventListener
	@Async
	public void onApplicationEvent(MyEvent event) {
		System.out.println(Thread.currentThread().toString());
		System.out.println("이벤트를 받았다. 데이터는 "+ event.getData());
		
	}

	@EventListener
	@Async
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println(Thread.currentThread().toString());
		System.out.println("ContextRefreshedEvent");
		
	}
  //context가 refreshed될때 출력됨.
	
	@EventListener
	@Async
	public void onApplicationEvent(ContextClosedEvent event) {
		System.out.println(Thread.currentThread().toString());
		System.out.println("ContextClosedEvent");
		
	}
  //종료될 때 출력됨


}
```



#### 9. ResourceLoader

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

  

#### 프록시 기반 AOP

- 스프링 Bean에만 AOP를 적용시킬수 있음.

- `@Primary`

  - 같은 타입의 빈이 여러가지 있을 때, 그 중 하나를 선택하는 어노테이션

- Spring boot , Web을 실행시키지 않고 app만 실행시키는 방법

  ```java
  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.WebApplicationType;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  
  @SpringBootApplication
  public class DemoApplication {
  
    public static void main(String[] args) {
      SpringApplication app = new SpringApplication(DemoApplication.class);
      app.setWebApplicationType(WebApplicationType.NONE);
      app.run(args);
    }
  
  }
  ```

- 프록시를 클래스로 만들어서(Proxy-pattern), 사용하는 방법도 있지만 클래스를 새로 만들어야하고 (자원 소모), 이 클래스를 구성하는 데도 어느정도의 시간이 소요됨.
- 그래서 스프링 AOP가 등장

- AbstractAutoProxyCreator 는 BeanPostProcessor의 구현체
  
  - 토비의 스프링 3에도 나옴. 이 책 참조.



#### @AOP

- advice, poinCut 2가지를 정의해야함

  ```java
  import org.aspectj.lang.ProceedingJoinPoint;
  import org.aspectj.lang.annotation.Around;
  import org.aspectj.lang.annotation.Aspect;
  import org.springframework.stereotype.Component;
  
  @Component
  @Aspect
  public class PerfAspect {
  
   @Around("execution(* com.demospringspel..*.EventService.*(..))") 	     //@Around 의 value 가 pointCut
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {//이부분이 advice
      long begin = System.currentTimeMillis();
      Object retVal = pjp.proceed();
      System.out.println(System.currentTimeMillis() - begin);
      return retVal;
    }
  }
  
  ```

  `@Around("execution(* com.demospringspel..*.EventService.*(..))")`

  - PointCut 표현식.

- Annotation 클래스

  ```java
  package com.demospringspel.demo;
  
  import java.lang.annotation.Retention;
  import java.lang.annotation.RetentionPolicy;
  
  @Retention(RetentionPolicy.CLASS) // 이 annotation class를 class 파일 까지 유지하겠다라는 의미
  public @interface PerfLogging {
  
  }
  
  ```

  - 컴파일해도 이 annotation 정보가 남아 있음.
  - 기본 값이 `CLASS`
  - `RetentionPolicy.SOURCE` 면 컴파일 하면 사라짐. 

  ```java
  import org.aspectj.lang.ProceedingJoinPoint;
  import org.aspectj.lang.annotation.Around;
  import org.aspectj.lang.annotation.Aspect;
  import org.springframework.stereotype.Component;
  
  @Component
  @Aspect
  public class PerfAspect {
  
    @Around("@annotation(PerfLogging)")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
      long begin = System.currentTimeMillis();
      Object retVal = pjp.proceed();
      System.out.println(System.currentTimeMillis() - begin);
      return retVal;
    }
  }
  ```

  - @PerfLogging 이 달려있는 곳에, 아래 코드를 적용해라라는 의미.
  - 좀 더 편하게 Aspect를 적용시킬 수 있음.

- bean을 통해 적용

  ```java
  import org.aspectj.lang.ProceedingJoinPoint;
  import org.aspectj.lang.annotation.Around;
  import org.aspectj.lang.annotation.Aspect;
  import org.springframework.stereotype.Component;
  
  @Component
  @Aspect
  public class PerfAspect {
  
    @Around("bean(simpleEventService)")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
      long begin = System.currentTimeMillis();
      Object retVal = pjp.proceed();
      System.out.println(System.currentTimeMillis() - begin);
      return retVal;
    }
  }
  
  ```

  

- @Before

  ```java
    @Before("bean(simpleEventService)")
    public void hello() {
      System.out.println("hello");
    }
  }
  ```

  - bean simpleEventServie 각각 method 이전에 아래 코드가 실행됨.



### Null-Safety

- 목적 : annotation을 통해서 Null에 대한 설정을 미리 함.
- 그래서 complie 타임에 null point exception 오류를 미리 예방할 수 있음.
- @NonNull
- @Nullable
- 패키지 파일에 적용할 수 있음.
- 그러면 패키지 아래 파일 모두에 NonNull이 적용됨.
- Null을 허용하는 곳에서만 Nullable annoation을 붙이는 방식