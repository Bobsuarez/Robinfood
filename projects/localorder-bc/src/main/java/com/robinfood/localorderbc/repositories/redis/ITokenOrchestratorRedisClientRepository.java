package com.robinfood.localorderbc.repositories.redis;


import com.robinfood.localorderbc.entities.token.TokenModel;

import java.util.Optional;

/**
 * Contract to obtain and set token in redis
 */
public interface ITokenOrchestratorRedisClientRepository {

    /**
     * Method that gets the redis token
     *
     * @return Token
     */
    Optional<TokenModel> getToken(String redisId);

    /**
     * Method that sets the redis token
     *
     * @param  token to save in redis
     */
    void setToken(TokenModel token, String redisId);

    /**
     * Method that sets the redis token
     */
    void deleteAllKeyRedis();

}
