package me.han.hanspringbootstarter;

public class Holoman {

  String name;

  int howLong;

  public String getName() {
    return name;
  }

  public Holoman setName(String name) {
    this.name = name;
    return this;
  }

  public int getHowLong() {
    return howLong;
  }

  public Holoman setHowLong(int howLong) {
    this.howLong = howLong;
    return this;
  }

  @Override
  public String toString() {
    return "Holoman{" +
        "name='" + name + '\'' +
        ", howLong=" + howLong +
        '}';
  }
}
