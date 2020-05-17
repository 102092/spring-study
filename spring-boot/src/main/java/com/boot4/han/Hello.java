package com.boot4.han;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Hello {

  private String prefix;

  private String name;

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("prefix", prefix)
        .append("name", name)
        .toString();
  }
}
