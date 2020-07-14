package com.boot4.han;

import com.boot4.han.account.Account;
import com.boot4.han.account.AccountService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {

  final AccountService accountService;

  public AccountRunner(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Account han = accountService.createAccount("han", "1234");
    System.out.println(han.getUsername() + " password : "+ han.getPassword());
  }
}
