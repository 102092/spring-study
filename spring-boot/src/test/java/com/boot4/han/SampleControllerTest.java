package com.boot4.han;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SampleController.class)
class SampleControllerTest {

  @MockBean
  SampleService sampleService;

  @Autowired
  MockMvc mockMvc;

  @Test
  public void hello() throws Exception {
    when(sampleService.getName()).thenReturn("han");

    mockMvc.perform(get("/hello"))
        .andExpect(content().string("hello han"));
  }


}
