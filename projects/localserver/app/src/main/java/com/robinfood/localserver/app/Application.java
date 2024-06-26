package com.robinfood.localserver.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(
        scanBasePackages = {
                "com.robinfood.localserver.*"
        }
)
@EnableFeignClients(basePackages = {"com.robinfood.localserver.*"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
