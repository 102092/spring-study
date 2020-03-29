package com.boot4.han;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SampleListener implements ApplicationListener<ApplicationStartedEvent> {

  //현재 시점에서는 Application Context가 만들어지지 않았다.
  //그래서 실행해도 아래 코드가 찍히질 않는다.
  //이런 경우에는 직접 등록을 해줘야한다.


  @Override
  public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
    System.out.println("=================");
    System.out.println("Application Started");
    System.out.println("=================");

  }
}
