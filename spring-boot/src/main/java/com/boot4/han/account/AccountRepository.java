package com.boot4.han.account;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

  Optional<Account> findByEmail(String email);

}
