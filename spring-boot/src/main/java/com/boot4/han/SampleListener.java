package com.boot4.han;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleListener implements ApplicationRunner {

  @Autowired
  HanProperties hanProperties;

  @Override
  public void run(ApplicationArguments args) {
    System.out.println("==============");
    System.out.println(hanProperties.name);
    System.out.println(hanProperties.age);
    System.out.println(hanProperties.fullName);
    System.out.println("==============");

  }
}
