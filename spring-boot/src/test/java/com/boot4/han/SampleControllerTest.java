package com.boot4.han;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SampleControllerTest {

  @Autowired
  WebTestClient webTestClient;

  @MockBean
  SampleService sampleService;

  @Test
  public void hello() {
    when(sampleService.getName()).thenReturn("han");

    webTestClient.get().uri("/hello").exchange()
        .expectStatus().isOk()
        .expectBody(String.class)
        .isEqualTo("hello han");
  }


}
