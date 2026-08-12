package com.alnwick.MonolithDocker.Utils;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        boolean isDatabaseUp = checkDatabaseConnection();

        if (!isDatabaseUp) {
            return Health.down()
                    .withDetail("error", "Can't connect to database")
                    .build();
        }

        return Health.up()
                .withDetail("version", "1.0.0")
                .build();
    }

    private boolean checkDatabaseConnection() {
        return true;
    }
}
