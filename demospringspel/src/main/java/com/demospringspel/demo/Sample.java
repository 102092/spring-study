package com.demospringspel.demo;

import org.springframework.stereotype.Component;

@Component
public class Sample {

  private int data = 200;

  public int getData() {
    return data;
  }

  public Sample setData(int data) {
    this.data = data;
    return this;
  }
}
