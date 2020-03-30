package com.boot4.han;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HanApplication {

  @Bean
  public ServerProperties serverProperties() {
    return new ServerProperties();
  }

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(HanApplication.class);
    app.setWebApplicationType(WebApplicationType.NONE);
    app.run(args);
  }

}
