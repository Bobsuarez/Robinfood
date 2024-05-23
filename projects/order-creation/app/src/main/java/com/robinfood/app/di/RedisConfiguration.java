package com.robinfood.app.di;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
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
@EnableRedisRepositories("com.robinfood.repository.redis")
@Slf4j
public class RedisConfiguration {

    private final RedisProperties redisProperties;

    public RedisConfiguration(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        var config = new RedisStandaloneConfiguration(
                redisProperties.getHost(),
                redisProperties.getPort()
        );

        log.info("Redis configured successfully");

        return new JedisConnectionFactory(config);
    }

}
