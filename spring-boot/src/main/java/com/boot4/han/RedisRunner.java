package com.boot4.han;

import com.boot4.han.account.Account;
import com.boot4.han.account.AccountRepository;
import java.util.Optional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisRunner implements ApplicationRunner {

  final StringRedisTemplate redisTemplate;

  final AccountRepository accountRepository;

  public RedisRunner(StringRedisTemplate redisTemplate,
      AccountRepository accountRepository) {
    this.redisTemplate = redisTemplate;
    this.accountRepository = accountRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    ValueOperations<String, String> values = redisTemplate.opsForValue();
    values.set("han", "kim");
    values.set("springboot", "2.3");
    values.set("hello", "world");

    Account account = new Account();
    account.setEmail("example@gmail.com");
    account.setUsername("han");

    accountRepository.save(account);

    Optional<Account> byId = accountRepository.findById(account.getId());
    System.out.println(byId.get().getUsername());
    System.out.println(byId.get().getEmail());

  }
}
