package com.robinfood.repository.token;

import com.robinfood.core.models.domain.Token;

/**
 * Contract that obtains the service token from the SSO
 */
public interface ITokenToBusinessCapabilityRepository {

    /**
     * Method that gets the service token from SSO
     *
     * @return Token
     */
    Token get();

}
