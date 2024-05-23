package com.robinfood.changestatusor.entities.redis;

import com.robinfood.changestatusor.models.domain.Token;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Data
@Builder
@RedisHash("token-business-capability")
public class TokenBusinessCapabilityRedis {

    @TimeToLive(unit = TimeUnit.MINUTES)
    private Long timeToLive;

    @Id
    private String tokenId;

    private Token token;
}
