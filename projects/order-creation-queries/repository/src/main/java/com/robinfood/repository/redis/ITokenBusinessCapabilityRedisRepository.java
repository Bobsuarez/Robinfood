package com.robinfood.repository.redis;

import com.robinfood.core.entities.redis.TokenBusinessCapabilityRedisEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenBusinessCapabilityRedisRepository extends
    CrudRepository<TokenBusinessCapabilityRedisEntity, String> {

}
