package com.robinfood.orderorlocalserver.repositories.redis;


import com.robinfood.orderorlocalserver.entities.token.TokenModelEntity;

import java.util.Optional;

public interface ITokenBusinessCapabilityRedisClientRepository {

    Optional<TokenModelEntity> getToken();

    void setToken(TokenModelEntity token);

}
