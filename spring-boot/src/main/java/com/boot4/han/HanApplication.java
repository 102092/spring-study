package com.boot4.han;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HanApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(HanApplication.class);
//    app.addListeners(new SampleListener());
    app.run(args);
  }

}
