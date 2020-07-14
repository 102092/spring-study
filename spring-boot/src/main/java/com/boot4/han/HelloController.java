package com.boot4.han;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "index";
  }

  @GetMapping("/my")
  public String my() {
    return "my";
  }
}
