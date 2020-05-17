package com.boot4.han;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {


  @GetMapping("/hello")
  public String hello() {
    throw new SampleException();
  }

  @ExceptionHandler(SampleException.class)
  public @ResponseBody
  AppError sampleError(SampleException e) {
    AppError appError = new AppError();
    appError.setMessage("error.app.key");
    appError.setReason("나도 모름");
    return appError;
  }
}
