package com.boot4.han;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

@DataJpaTest
class AccountTest {

  @Autowired
  DataSource dataSource;

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Autowired
  AccountRepository accountRepository;

  @Test
  public void di() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      DatabaseMetaData metaData = connection.getMetaData();
      System.out.println(metaData.getURL());
      System.out.println(metaData.getDriverName());
      System.out.println(metaData.getUserName());
    }
  }

  @Test
  public void account() {
    Account account = new Account();
    account.setUsername("han");
    account.setPassword("pass");

    Account savedAccount = accountRepository.save(account);

    assertThat(savedAccount).isNotNull();

    Optional<Account> existingAccount = accountRepository.findByUsername(savedAccount.getUsername());
    assertThat(existingAccount).isNotEmpty();

    Optional<Account> nonExistingAccount = accountRepository.findByUsername("안녕?");
    assertThat(nonExistingAccount).isEmpty();
  }

}
