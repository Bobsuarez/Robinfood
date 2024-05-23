package com.robinfood.repository.redis;

import com.robinfood.core.models.domain.TokenModel;

import java.util.Optional;

/**
 * Contract to obtain and set token in redis
 */
public interface ITokenBusinessCapabilityRedisClientRepository {

    /**
     * Method that gets the redis token
     *
     * @return Token
     */
    Optional<TokenModel> getToken();

    /**
     * Method that sets the redis token
     *
     * @param  token to save in redis
     */
    void setToken(TokenModel token);

}
