package com.robinfood.core.entities.redis;

import com.robinfood.core.models.domain.Token;
import java.util.concurrent.TimeUnit;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

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
