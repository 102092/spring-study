package com.boot4.han;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

  @GetMapping("/hello")
  public String hello() {
    return "hello"; //view 이름을 return
  }

  @GetMapping("/my")
  public String my() {
    return "my";
  }
}
