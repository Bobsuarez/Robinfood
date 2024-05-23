package com.robinfood.localorderbc.repositories.redis;

import com.robinfood.localorderbc.entities.redis.TokenOrchestratorRedisEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenOrchestratorRedisRepository extends
        CrudRepository<TokenOrchestratorRedisEntity, String> {

}
