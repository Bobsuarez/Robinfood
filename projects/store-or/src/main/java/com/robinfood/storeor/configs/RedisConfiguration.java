package com.robinfood.storeor.configs;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Configuration
@EnableRedisRepositories("com.robinfood.storeor.repositories.redis")
@Slf4j
public class RedisConfiguration {

    private final RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        var config = new RedisStandaloneConfiguration(
            redisProperties.getHost(),
            redisProperties.getPort()
        );

        return new JedisConnectionFactory(config);
    }

}
