package com.boot4.han;

import java.util.Arrays;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleListener implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    Arrays.stream(args).forEach(System.out::println);

  }
}
