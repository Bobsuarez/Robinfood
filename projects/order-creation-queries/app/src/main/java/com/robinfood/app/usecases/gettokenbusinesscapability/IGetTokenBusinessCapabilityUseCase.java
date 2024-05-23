package com.robinfood.app.usecases.gettokenbusinesscapability;

import com.robinfood.core.models.domain.TokenModel;

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
