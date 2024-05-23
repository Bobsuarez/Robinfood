package com.robinfood.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
    basePackages = "com.robinfood.repository.*"
)

@EntityScan(
    basePackages = "com.robinfood.core.entities"
)
@SpringBootApplication(
    scanBasePackages = {
        "com.robinfood.app.*",
        "com.robinfood.repository.*",
        "com.robinfood.core.*"
    }
)
public class OrderBcApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderBcApplication.class);
    }

}
