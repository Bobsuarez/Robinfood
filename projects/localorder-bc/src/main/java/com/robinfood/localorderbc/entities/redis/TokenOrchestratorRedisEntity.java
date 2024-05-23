package com.robinfood.localorderbc.entities.redis;

import com.robinfood.localorderbc.entities.token.TokenModel;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Data
@Builder
@RedisHash("token-tokenuser")
public class TokenOrchestratorRedisEntity {

    @TimeToLive(unit = TimeUnit.MINUTES)
    private Long timeToLive;

    @Id
    private String tokenId;

    private TokenModel token;

}
