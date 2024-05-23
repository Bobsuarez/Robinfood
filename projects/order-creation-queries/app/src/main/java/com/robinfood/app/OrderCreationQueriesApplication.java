package com.robinfood.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.robinfood.app.*",
                "com.robinfood.repository.*",
                "com.robinfood.core.*",
                "com.robinfood.network.*",
        }
)
public class OrderCreationQueriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderCreationQueriesApplication.class);
    }

}
