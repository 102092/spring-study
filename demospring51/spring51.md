## IoC 컨테이너

### @autowired

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



## @Component, 컴포넌트 스캔

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



### bean scope

- Singleton scope

  - 애플리케이션 전반에 걸쳐서, 해당 빈의 인스턴스가 하나다.

  ```java
  package com.donghwan.demospring51;
  
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
  package com.donghwan.demospring51;
  
  import org.springframework.context.annotation.Scope;
  import org.springframework.stereotype.Component;
  
  @Component @Scope("prototype")
  public class Proto {
  
  }
  ```

  - @Scope를 통해, prototype이라 선언해줬고, 이러면 proto 빈을 받아올 때마다 인스턴스가 새롭게 생성된다.



- Singleton, Prototype

```java
package com.donghwan.demospring51;

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
  package com.donghwan.demospring51;
  
  import org.springframework.context.annotation.Scope;
  import org.springframework.context.annotation.ScopedProxyMode;
  import org.springframework.stereotype.Component;
  
  @Component @Scope (value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
  public class Proto {
  
  }
  
  ```

  ```java
  package com.donghwan.demospring51;
  
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



## 6. IoC 컨테이너 Environmnet

### 프로파일

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

### 프로퍼티

- hierarchical 계층적. 
- 만약 프로퍼티가 겹친다면, source에 정의된 properties보다, VM에 정의된 것이 우선되어 나옴