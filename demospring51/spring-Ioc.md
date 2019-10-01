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