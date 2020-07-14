package com.boot4.han;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TestController.class)
class TestControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  @WithMockUser
  void hello() throws Exception {
    mockMvc.perform(get("/hello")
        .accept(MediaType.TEXT_HTML))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("hello"));
  }

  @Test
  void hello_without_user() throws Exception {
    mockMvc.perform(get("/hello"))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }


  @Test
  @WithMockUser
  void my() throws Exception {
    mockMvc.perform(get("/my"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("my"));
  }
}
