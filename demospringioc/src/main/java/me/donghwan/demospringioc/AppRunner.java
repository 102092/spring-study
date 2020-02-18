package me.donghwan.demospringioc;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AppRunner implements ApplicationRunner {

  @Autowired
  Validator validator;

  @Override
  public void run(ApplicationArguments args) {
    System.out.println(validator.getClass());
    Event event = new Event();

    event.setSize(-1);
    event.setEmail("asdasd");
    Errors errors = new BeanPropertyBindingResult(event, "event");

    validator.validate(event, errors);
    System.out.println(errors.hasErrors());
    errors.getAllErrors().forEach(error -> {
      System.out.println("======error code======");
      Arrays.stream(error.getCodes()).forEach(System.out::println);
      System.out.println(error.getDefaultMessage());
    });
  }
}
