package common;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * This starts a Postgresql container for all tests.
 * Restarting the container for each class is not working properly with this container.
 */
public class PostgresqlDockerContainerExtension implements BeforeAllCallback, AfterAllCallback {

    private static PostgreSQLContainer postgresContainer = new PostgreSQLContainer();

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        public Initializer() {
        }


        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {

            postgresContainer.start();

            TestPropertyValues.of(
                    "spring.datasource.url=" + postgresContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgresContainer.getUsername(),
                    "spring.datasource.password=" + postgresContainer.getPassword(),
                    "spring.flyway.enabled=true"
            ).applyTo(applicationContext.getEnvironment());

            Runtime.getRuntime().addShutdownHook(
                    new Thread(() -> postgresContainer.stop())
            );
        }
    }
}
