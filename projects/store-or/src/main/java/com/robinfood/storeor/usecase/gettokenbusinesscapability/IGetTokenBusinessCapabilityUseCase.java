package com.robinfood.storeor.usecase.gettokenbusinesscapability;

import com.robinfood.storeor.entities.token.TokenModel;

/**
 * Contract that gets the service token
 */
public interface IGetTokenBusinessCapabilityUseCase {

    /**
     * Method that gets the service token
     *
     * @return Token
     */
    TokenModel invoke();

}
