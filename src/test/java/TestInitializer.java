import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.MySQLContainer;

/**
 * ユニットテスト時の起動設定.
 */
public class TestInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  /* コンテナ起動時に実行するsqlファイル。src/test/resourcesからの相対パス */
  private static final String INIT_D_PATH = "../../main/resources/init";

  /* テスト実施時に起動するコンテナ設定 */
  private static final MySQLContainer MYSQL = (MySQLContainer) new MySQLContainer( "mysql:5.7" )
      .withEnv( "MYSQL_USER", "test" )
      .withEnv( "MYSQL_PASSWORD", "pass" )
      .withEnv( "MYSQL_DATABASE", "test_db" )
      .withClasspathResourceMapping( INIT_D_PATH, "/docker-entrypoint-initdb.d", BindMode.READ_ONLY )
      .withExposedPorts( 3306 );

  static {
    MYSQL.start();
  }

  /**
   * コンテキストを上書き.
   */
  @Override
  public void initialize(ConfigurableApplicationContext context) {
    TestPropertyValues.of(
        "spring.datasource.url=" + MYSQL.getJdbcUrl(),
        "spring.datasource.username=" + MYSQL.getUsername(),
        "spring.datasource.password=" + MYSQL.getPassword(),
        "spring.datasource.driverClassName=" + "com.mysql.jdbc.Driver"
    ).applyTo( context.getEnvironment() );
  }
}
