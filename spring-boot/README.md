# 2부 : 인메모리 데이터베이스

- `h2database`

```java
import java.sql.Connection;
import java.sql.Statement;
import javax.sql.DataSource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class H2Runner implements ApplicationRunner {

  private final DataSource dataSource;

  private final JdbcTemplate jdbcTemplate;

  public H2Runner(DataSource dataSource, JdbcTemplate jdbcTemplate) {
    this.dataSource = dataSource;
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Connection connection = dataSource.getConnection();
    System.out.println(connection.getMetaData().getURL());
    System.out.println(connection.getMetaData().getUserName());

    Statement statement = connection.createStatement();
    //language=sql
    String sql = "CREATE TABLE USER (ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id)); ";
    statement.execute(sql);

    jdbcTemplate.execute("INSERT INTO USER VALUES (1, 'han')");

  }
}
;

```

