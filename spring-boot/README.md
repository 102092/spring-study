## SpringApplication
- VM option : `-Ddebug` 설정시, 디버그 모드로 어플리케이션이 샐행됨, 로그도 디버그 레벨까지

  - or program arguments : `--debug`

- 어떠한 자동설정이 적용되었는지, 안되었는지 볼 수 있음.

- FailureAnalyzers : 에러가 출력되었을 때, 예쁘게 출력해주는 것.

- 배너
  - resource 디렉토리 바로 아래에 `banner.txt` 로 만들어서, 안에 정의해주면 자신만의 배너를 만들어 줄 수 있다.
  - 아스키 제너레이터 해서 예쁘게 만들어보자 ㅎㅎ
  - 사용할 수 있는 변수들도 있음(레퍼렌스 확인), 몇가지 변수들은 MANIFEST 파일이 있어야함.
  - 배너는 이미지 파일도 사용할 수 있음!!
  - 메인 앱에서 설정해줄 수도 있고,
  - `SpringApplicationBuilder()` 를 이용하는 방법도 있다.
  - 무궁무진!
  
- 아무런 변경도 하지않고 프로젝트를 실행하면, 로그 레벨은 **info** level

- `Application Context`가 만들어진 다음에 발생하는 이벤트들은, `bean` 들을 실행시킬 수 있음.

  - 그 이전에 발생한 이벤트는 어떻게 될까?
  - 실행할 때, `addListener`를 해야함

  ```java
  package com.boot4.han;
  
  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  
  @SpringBootApplication
  public class HanApplication {
  
    public static void main(String[] args) {
      SpringApplication app = new SpringApplication(HanApplication.class);
      app.addListeners(new SampleListener());
      app.run(args);
    }
  
  }
  ```

- setWebApplicationType
  - 첫번째 조건은 servlet이 있는가 없는가.
  - 두번째, servlet, webflux 둘다 있아면, servlet으로 우선 실행됨.
  - 개발자가 근데 webflux를 사용하고 싶다면, `WebApplicationType.REACTIVE`를 설정
- ApplicationArguments
  - VM option -Dfoo --> false
  - Program arguments --bar --> true
  - **-D** 옵션으로 오는 것은 아규먼트로 인식하지 못함.
  - **--** 옵션만 아규먼트로 인식하는 구나.
  - 유용한 메서드 많다.
- 테스트 스킵하는거 깜박했다 -- 그러면 오래걸린당.
- Spring boot application이 다 뜬다음에 무언가를 더 실행하고 싶을 때.
  - Application runner
    - 굉장히 추상화 된 객체 지만, 쓰기 편하다.
    - 로우레벨 까지 개발자가 건드리지 않아서 괜찮다.
    - 여러개면 `@order()`를 줄 수 있음
      - 숫자가 낮은 것이 높은것 1등이 먼저들어온다.
- CommandLiner
  
  - JVM option은 아예 신경쓰지 않는다.



## 외부 설정 

### 1부

- `application.properties`  

  - 스프링부트 규약, 컨벤션

  - key, value 값

- *<u>프로퍼티 우선순위</u>*
  1. 유저 홈 디렉토리에 있는 spring-boot-dev-tools.properties
  2. 테스트에 있는 @TestPropertySource
  3. @SpringBootTest 애노테이션의 properties 애트리뷰트
  4. 커맨드 라인 아규먼트
  5. SPRING_APPLICATION_JSON (환경 변수 또는 시스템 프로티) 에 들어있는 프로퍼티
  6. ServletConfig 파라미터
  7. ServletContext 파라미터
  8. java:comp/env JNDI 애트리뷰트
  9. System.getProperties() 자바 시스템 프로퍼티
  10. OS 환경 변수
  11. RandomValuePropertySource
  12. JAR 밖에 있는 특정 프로파일용 application properties
  13. JAR 안에 있는 특정 프로파일용 application properties
  14. JAR 밖에 있는 application properties
  15. JAR 안에 있는 application properties
  16. @PropertySource
  17. 기본 프로퍼티 (SpringApplication.setDefaultProperties)

- 프로퍼티 기억해야할 추가 기능들

  - **랜덤값 설정하기**
    - ${random.*}
  - **플레이스 홀더**
    - name = keesun
    - fullName = ${name} baik

- java -jar target/han-0.0.1-SNAPSHOT.jar --han.name=kimdonghwan

  - = 부분 띄어쓰기 하면 안됨.

- `mvn clean package -DskipTests`

  - 테스트를 돌리지 않고 빌드.
  - 그렇지만 업무에서 실행하는 패키지는 반드시 테스트를 돌리고 빌드해야함

  

- 테스트 용도 application.properties 를 생성함.
  - 첫번째 빌드할때 main 폴더에 있는 것이 컴파일 되어서 클래스패스에 들어가게 되고,
  - 그리고 테스트 코드 폴더가 컴파일되어서 클래스패스에 들어가므로, 테스트 폴더/리소스 폴더 아래 있는 application.properties로 최종 override됨.