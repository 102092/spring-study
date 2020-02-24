package me.donghwan.demosprinioc.databind;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@WebMvcTest
public class EventControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void getTest() throws Exception {
    mockMvc.perform(get("/event/1"))
        .andExpect(status().isOk())
        .andExpect(content().string("1"));
  }

}
