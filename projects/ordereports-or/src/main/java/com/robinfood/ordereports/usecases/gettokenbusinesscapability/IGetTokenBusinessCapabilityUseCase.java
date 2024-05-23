package com.robinfood.ordereports.usecases.gettokenbusinesscapability;

import com.robinfood.ordereports.models.domain.TokenModel;

public interface IGetTokenBusinessCapabilityUseCase {

    /**
     * Method that gets the service token
     *
     * @return Token
     */
    TokenModel invoke();
}
