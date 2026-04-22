package com.it.ecommerce;

import static org.assertj.core.api.Assertions.assertThat;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FlywaySmokeTest {
  private static EmbeddedPostgres pg;

  @DynamicPropertySource
  static void registerDataSourceProperties(DynamicPropertyRegistry registry) throws IOException {
    if (pg == null) {
      pg = EmbeddedPostgres.start();
    }
    registry.add("spring.datasource.url", () -> pg.getJdbcUrl("postgres", "postgres"));
    registry.add("spring.datasource.username", () -> "postgres");
    registry.add("spring.datasource.password", () -> "postgres");
    registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
  }

  @AfterAll
  void stopPg() throws IOException {
    if (pg != null) {
      pg.close();
      pg = null;
    }
  }

  @Test
  void contextLoads_andFlywayApplied() {
    // If Flyway fails, the Spring context won't start.
    assertThat(true).isTrue();
  }
}

