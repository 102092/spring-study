package com.boot4.han;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:/test.properties")
class HanApplicationTests {

  @Autowired
  Environment environment;

  @Test
  void contextLoads() {
    assertThat(environment.getProperty("han.name"))
        .isEqualTo("han2");
  }

}
