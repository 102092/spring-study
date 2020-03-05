package me.han.hanspringbootstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("holoman")
public class HolomanProperties {

  private String name;
  private int howLong;

  public String getName() {
    return name;
  }

  public HolomanProperties setName(String name) {
    this.name = name;
    return this;
  }

  public int getHowLong() {
    return howLong;
  }

  public HolomanProperties setHowLong(int howLong) {
    this.howLong = howLong;
    return this;
  }
}
