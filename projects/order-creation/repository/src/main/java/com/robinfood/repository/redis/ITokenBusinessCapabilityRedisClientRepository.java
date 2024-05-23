package com.robinfood.repository.redis;

import com.robinfood.core.models.domain.Token;
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
    Optional<Token> getToken();

    /**
     * Method for update the token in Redis
     *
     * @param  token to save in redis
     */
    void setToken(Token token);

}
