package com.boot4.han.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
class AccountRepositoryTest {

  @Autowired
  AccountRepository accountRepository;

  @Test
  void findByEmail() {
    Account account = new Account();
    account.setUsername("test");
    account.setEmail("test@gmail.com");

    accountRepository.insert(account);

    Optional<Account> byId = accountRepository.findById(account.getId());
    assertThat(byId).isNotEmpty();

    Optional<Account> byEmail = accountRepository.findByEmail(account.getEmail());
    assertThat(byEmail).isNotEmpty();
    assertThat(byEmail.get().getUsername()).isEqualTo("test");
  }

}
