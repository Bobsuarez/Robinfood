package com.robinfood.changestatusor.repository.token;

import com.robinfood.changestatusor.models.domain.Token;

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
