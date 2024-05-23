package com.robinfood.app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

import static com.robinfood.core.constants.AsyncConfigConstants.SalesReport.CORE_POOL_SIZE;
import static com.robinfood.core.constants.AsyncConfigConstants.SalesReport.MAX_POOL_SIZE;
import static com.robinfood.core.constants.AsyncConfigConstants.SalesReport.QUEUE_CAPACITY;
import static com.robinfood.core.constants.AsyncConfigConstants.SalesReport.THREAD_NAME;
import static com.robinfood.core.constants.AsyncConfigConstants.SalesReport.THREAD_NAME_PREFIX;

@Configuration
public class AsyncConfig {

    @Bean(name = THREAD_NAME)
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        executor.initialize();
        return executor;
    }
}
