package com.projects.betting;

import java.sql.Connection;

import javax.sql.DataSource;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


@Component
public class DatabaseHealthIndicator implements HealthIndicator {

    private final DataSource dataSource;

    public DatabaseHealthIndicator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Health health() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(8)) {
                return Health.up().build();
            }
        } catch (Exception e) {
            return Health.down(e).build();
        }
        return Health.down().withDetail("Error", "Database not available").build();
    }
}
