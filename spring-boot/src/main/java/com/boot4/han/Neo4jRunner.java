package com.boot4.han;

import com.boot4.han.account.Account;
import com.boot4.han.account.AccountRepository;
import com.boot4.han.account.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Neo4jRunner implements ApplicationRunner {



  @Autowired
  AccountRepository accountRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Account account = new Account();
    account.setEmail("aaaasdq@gmail.com");
    account.setUsername("teasdqwest");

    Role role = new Role();
    role.setName("user");
    account.getRoles().add(role);

    accountRepository.save(account);

    System.out.println("finished");
  }
}
