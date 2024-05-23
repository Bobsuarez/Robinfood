package com.robinfood.changestatusor.repository.redis;

import com.robinfood.changestatusor.entities.redis.TokenBusinessCapabilityRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenBusinessCapabilityRedisRepository extends
        CrudRepository<TokenBusinessCapabilityRedis, String> {
}
