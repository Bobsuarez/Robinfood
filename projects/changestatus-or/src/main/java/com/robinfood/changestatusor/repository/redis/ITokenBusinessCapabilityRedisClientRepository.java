package com.robinfood.changestatusor.repository.redis;

import com.robinfood.changestatusor.models.domain.Token;

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
     * Metodo que setea el token en redis
     *
     * @param  token to save in redis
     */
    void setToken(Token token);
}
