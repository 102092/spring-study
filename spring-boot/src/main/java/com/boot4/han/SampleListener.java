package com.boot4.han;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleListener implements ApplicationRunner {

  @Value("${han.name}")
  private String name;

  @Override
  public void run(ApplicationArguments args) {
    System.out.println("==============");
    System.out.println(name);
    System.out.println("==============");

  }
}
