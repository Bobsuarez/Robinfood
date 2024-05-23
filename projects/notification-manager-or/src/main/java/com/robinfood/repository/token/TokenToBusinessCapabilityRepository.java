package com.robinfood.repository.token;

import com.robinfood.entities.TokenEntity;
import com.robinfood.entities.request.TokenRequestEntity;
import com.robinfood.network.http.api.SsoAPI;

import static com.robinfood.constants.Constants.AUTH_KEY;
import static com.robinfood.constants.Constants.TOKEN_AUTH_SECRET;
import static com.robinfood.constants.Constants.TOKEN_ISSUER;

public class TokenToBusinessCapabilityRepository implements ITokenToBusinessCapabilityRepository {

    private final SsoAPI ssoAPI;

    public TokenToBusinessCapabilityRepository() {
        this.ssoAPI = new SsoAPI();
    }

    public TokenToBusinessCapabilityRepository(SsoAPI ssoAPI) {
        this.ssoAPI = ssoAPI;
    }

    @Override
    public TokenEntity get() {

        final TokenRequestEntity tokenRequestEntity = TokenRequestEntity.builder()
                .authKey(AUTH_KEY)
                .authSecret(TOKEN_AUTH_SECRET)
                .issuer(TOKEN_ISSUER)
                .build();

        return ssoAPI.getToken(tokenRequestEntity);
    }
}
