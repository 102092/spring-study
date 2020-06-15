package com.boot4.han;

import java.sql.Connection;
import javax.sql.DataSource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class H2Runner implements ApplicationRunner {

  private final DataSource dataSource;

  public H2Runner(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Connection connection = dataSource.getConnection();
    System.out.println(connection.getMetaData().getURL());
    System.out.println(connection.getMetaData().getUserName());
  }
}
