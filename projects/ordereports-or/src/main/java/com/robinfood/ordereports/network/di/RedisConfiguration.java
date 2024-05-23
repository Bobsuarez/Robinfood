package com.robinfood.ordereports.network.di;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * Configuration to start redis
 */
@Configuration
@EnableRedisRepositories("com.robinfood.ordereports.repositories.redis")
@Slf4j
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private  String host;

    @Value("${spring.redis.port}")
    private int port;


    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        var config = new RedisStandaloneConfiguration(
                host,
                port
        );

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(config);

        log.info("Redis configured successfully {} {}", config.getHostName(), config.getPort());

        return jedisConnectionFactory;
    }

}

