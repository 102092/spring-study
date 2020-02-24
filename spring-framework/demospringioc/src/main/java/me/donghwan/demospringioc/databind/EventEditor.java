package me.donghwan.demospringioc.databind;

import java.beans.PropertyEditorSupport;

public class EventEditor extends PropertyEditorSupport {

  @Override
  public String getAsText() {
    return super.getAsText();
  }

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    setValue(new Event(Integer.parseInt(text)));
  }
}
