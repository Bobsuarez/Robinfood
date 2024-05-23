package com.robinfood.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Slf4j
@SpringBootApplication(
        scanBasePackages = {
                "com.robinfood.app.*",
                "com.robinfood.repository.*",
                "com.robinfood.repositories.*",
                "com.robinfood.network.*",
                "com.robinfood.core.*",
        }
)
public class OrderCreationApplication {

    @Value("${thread.core-pool-size}")
    private Integer corePoolSize;

    @Value("${thread.max-pool-size}")
    private Integer maxPoolSize;

    @Value("${thread.queue-capacity}")
    private Integer queueCapacity;

    public OrderCreationApplication() {
        // this constructor is empty because it is a configuration class
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderCreationApplication.class);
    }

    @Bean
    public Executor taskExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("OrderCreation-");
        executor.afterPropertiesSet();
        executor.initialize();
        log.info("ThreadPoolTaskExecutor set");
        return executor;
    }

}
