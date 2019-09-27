## IoC 컨테이너

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

### Bean

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

### Pojo

- 

### Singleton

- 하나의 객체만 만들어서 사용하는 것.
  - 코드에서 보면 `BookService` 에서, 주입다는 `BookRepository` 객체에서 생성된 인스턴스는 싱글톤 타입으로, 한번만 만들어짐.
  - 그러면 효율적.
  - 왜? <u>메모리 면에서도 효율</u>적이고, 이미 만들어진 객체를 사용하기 때문에 <u>런타임시 성능 최적화에도 유리</u>하다.

- singleton이 아닌 것은? 
  - Prototype : 매번 다른 객체로 만들어서 사용하는 것.



### ApplicationContext

- 인터페이스

- Bean factory 상속받음.
- `Message Source` 다국화 메세지용 기능...



### xml

- ClassPathXmlApplicationContext
  - 빈들을 주입하는 것이 굉장히 귀찮음.



### @Component

- bean으로 등록하는 어노테이션
- 이 어노테이션이 확장된 것이 @Servie, @Repository...
- `ComponentScan(basePackageClasses = DemoApplication.class)`
- [참고](https://galid1.tistory.com/494)



### @Configuration

- 어노테이션 환경설정을 돕는 어노테이션.
- 클래스가 한개 이상의 @Bean이 등록될것으로 기대됨.

- [참고]([http://tech.javacafe.io/spring/2018/11/04/%EC%8A%A4%ED%94%84%EB%A7%81-Configuration-%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98-%EC%98%88%EC%A0%9C/](http://tech.javacafe.io/spring/2018/11/04/스프링-Configuration-어노테이션-예제/))



### @SpringBootApplication

- @componetScan과 @configuration이 포함되어있는 어노테이션.

