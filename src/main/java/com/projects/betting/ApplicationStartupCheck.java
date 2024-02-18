package com.projects.betting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupCheck implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ConfigurableApplicationContext context;

    private final DatabaseHealthIndicator databaseHealthIndicator;

    public ApplicationStartupCheck(DatabaseHealthIndicator databaseHealthIndicator) {
        this.databaseHealthIndicator = databaseHealthIndicator;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (databaseHealthIndicator.health().getStatus() != Status.UP) {
            // Trigger application restart
            // This method needs to be implemented to restart the application
            restartApplication();
        }
    }

    private void restartApplication() {
        // Implementation to restart the application
        // This could be a call to a script or using Spring Boot's Admin client to restart
        Thread restartThread = new Thread(() -> {
            try {
                // Delay to ensure that the response is sent before restarting
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            //Shutdown current context
            context.close();

            //Restart
            ApplicationArguments args = context.getBean(ApplicationArguments.class);
            SpringApplication.run(BettingApplication.class, args.getSourceArgs());
        });

        restartThread.setDaemon(false);
        restartThread.start();
    }
}

