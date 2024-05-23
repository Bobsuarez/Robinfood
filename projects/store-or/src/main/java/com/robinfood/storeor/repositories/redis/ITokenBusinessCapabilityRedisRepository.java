package com.robinfood.storeor.repositories.redis;


import com.robinfood.storeor.entities.redis.TokenBusinessCapabilityRedisEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenBusinessCapabilityRedisRepository extends
    CrudRepository<TokenBusinessCapabilityRedisEntity, String> {

}
