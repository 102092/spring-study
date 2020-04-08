package com.boot4.han.user;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  void hello() throws Exception {
    mockMvc.perform(get("/hello"))
        .andExpect(status().isOk())
        .andExpect(content().string("hello"));
  }

  @Test
  void createUser_JSON() throws Exception {
    String userJson = "{\"username\":\"han\", \"password\":\"1234\"}";
    mockMvc.perform(post("/users/create")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(userJson))
        .andExpect(jsonPath("$.username", Matchers.is(Matchers.equalTo("han"))))
        .andExpect(jsonPath("$.password", Matchers.is(Matchers.equalTo("1234"))));

  }

}
