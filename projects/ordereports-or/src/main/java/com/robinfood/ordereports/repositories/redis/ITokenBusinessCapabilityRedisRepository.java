package com.robinfood.ordereports.repositories.redis;

import com.robinfood.ordereports.entities.redis.TokenBusinessCapabilityRedisEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenBusinessCapabilityRedisRepository extends
        CrudRepository<TokenBusinessCapabilityRedisEntity, String> {

}
