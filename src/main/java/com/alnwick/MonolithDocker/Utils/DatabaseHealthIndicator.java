package com.alnwick.MonolithDocker.Utils;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseHealthIndicator implements HealthIndicator {

    private final DataSource dataSource;

    public DatabaseHealthIndicator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Health health() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(2)) {
                return Health.up()
                        .withDetail("database", connection.getMetaData().getDatabaseProductName())
                        .build();
            }
            return Health.down()
                    .withDetail("error", "Connection.isValid() return false")
                    .build();
        } catch (SQLException ex) {
            return Health.down(ex)
                    .withDetail("error", ex.getMessage())
                    .build();
        }
    }
}
