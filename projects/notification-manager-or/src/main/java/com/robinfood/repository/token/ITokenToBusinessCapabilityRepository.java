package com.robinfood.repository.token;

import com.robinfood.entities.TokenEntity;

/**
 * Contract that obtains the service token from the SSO
 */
public interface ITokenToBusinessCapabilityRepository {
    TokenEntity get();
}
