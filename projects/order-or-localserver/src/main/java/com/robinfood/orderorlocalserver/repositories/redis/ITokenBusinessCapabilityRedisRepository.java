package com.robinfood.orderorlocalserver.repositories.redis;

import com.robinfood.orderorlocalserver.entities.TokenBusinessCapabilityRedisEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenBusinessCapabilityRedisRepository extends
        CrudRepository<TokenBusinessCapabilityRedisEntity, String> {

}
