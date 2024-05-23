package com.robinfood.paymentmethodsbc.repositories.impl;

import com.robinfood.paymentmethodsbc.repositories.RedisRepository;
import java.time.Duration;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class RedisRepositoryImpl implements RedisRepository {
    private final ValueOperations<String, String> redisClient;

    public RedisRepositoryImpl(ValueOperations<String, String> redisClient) {
        this.redisClient = redisClient;
    }

    @Override
    public void set(String key, String value) {
        try {
            redisClient.set(key, value);
        } catch (Exception e) {
            log.error(
                "Error ocurred when storing data to redis",
                e
            );
        }
    }

    @Override
    public void set(String key, String value, Duration timeout) {
        try {
            redisClient.set(key, value, timeout);
        } catch (Exception e) {
            log.error(
                "Error ocurred when storing data to redis using timeout",
                e
            );
        }
    }

    @Override
    public Optional<String> get(String key) {
        try {
            return Optional.ofNullable(redisClient.get(key));
        } catch (Exception e) {
            log.error(
                "Error ocurred when getting data from redis",
                e
            );
            return Optional.empty();
        }
    }

    @Override
    public Set<String> getKey(String pattern) {
        return redisClient.getOperations().keys(pattern);
    }

    @Override
    public boolean deleteKey(String key) {
        if(!key.contains("*")){
            log.info("key to be deleted from cache: {}", key);
            return Boolean.TRUE.equals(redisClient.getOperations().delete(key));
        }
        return false;
    }
}
