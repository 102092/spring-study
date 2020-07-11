package com.boot4.han;

import com.boot4.han.account.Account;
import com.boot4.han.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class HanApplication {

  @Autowired
  MongoTemplate mongoTemplate;

  @Autowired
  AccountRepository accountRepository;

  public static void main(String[] args) {
    SpringApplication.run(HanApplication.class);
  }

  @Bean
  public ApplicationRunner applicationRunner() {
    return args -> {
      Account account = new Account();
      account.setUsername("han12312");
      account.setEmail("han4124123@gmail.com");

      accountRepository.insert(account);

      System.out.println("finished");
    };
  }
}
