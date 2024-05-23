package com.robinfood.ordereports.entities.redis;

import com.robinfood.ordereports.models.domain.TokenModel;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;


@Data
@Builder
@RedisHash("token-business-capability")
public class TokenBusinessCapabilityRedisEntity {

    @TimeToLive(unit = TimeUnit.MINUTES)
    private Long timeToLive;

    @Id
    private String tokenId;

    private TokenModel token;

}
