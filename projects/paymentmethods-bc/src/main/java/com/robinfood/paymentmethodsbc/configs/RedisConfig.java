package com.robinfood.paymentmethodsbc.configs;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
public class RedisConfig {

    private final String redisHost;
    private final Integer redisPort;
    private final Integer database;

    public RedisConfig(
        @Value("${redis.host}") String redisHost,
        @Value("${redis.port}") Integer redisPort,
        @Value("${redis.database}") Integer database
    ) {
        this.redisHost = redisHost;
        this.redisPort = redisPort;
        this.database = database;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        log.info("Redis server connection");
        final RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(
            redisHost,
            redisPort
        );
        configuration.setDatabase(database);
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        log.info("redis template configuration");
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public ValueOperations<String, String> valueOperations() {
        return redisTemplate().opsForValue();
    }
}
