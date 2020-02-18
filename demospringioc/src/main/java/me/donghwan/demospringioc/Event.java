package me.donghwan.demospringioc;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Event {

  Integer id;

  @NotEmpty
  String title;

  @Min(0)
  Integer size;

  @Email
  String email;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getSize() {
    return size;
  }

  public String getEmail() {
    return email;
  }
}
