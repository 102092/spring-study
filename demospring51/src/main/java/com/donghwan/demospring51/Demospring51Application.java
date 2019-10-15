package com.donghwan.demospring51;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/app.properties")
public class Demospring51Application {

	public static void main(String[] args) {
		SpringApplication.run(Demospring51Application.class, args);
	}

}
