package com.robinfood.app;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaRepositories(
    basePackages = "com.robinfood.repository.*"
)

@EnableDynamoDBRepositories(
    basePackages = "com.robinfood.repositories.dynamodb.*"
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
@EnableAsync
public class OrderBcQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderBcQueryApplication.class);
    }

}
