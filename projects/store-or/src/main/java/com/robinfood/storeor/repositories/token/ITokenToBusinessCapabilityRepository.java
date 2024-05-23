package com.robinfood.storeor.repositories.token;


import com.robinfood.storeor.entities.token.TokenModel;

/**
 * Contract that obtains the service token from the SSO
 */
public interface ITokenToBusinessCapabilityRepository {

    /**
     * Method that gets the service token from SSO
     *
     * @return Token
     */
    TokenModel get();

}
