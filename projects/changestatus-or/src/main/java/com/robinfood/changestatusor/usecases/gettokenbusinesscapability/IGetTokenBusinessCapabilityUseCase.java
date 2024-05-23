package com.robinfood.changestatusor.usecases.gettokenbusinesscapability;

import com.robinfood.changestatusor.models.domain.Token;

/**
 * Contract that gets the service token
 */
public interface IGetTokenBusinessCapabilityUseCase {

    /**
     * Method that gets the service token
     *
     * @return Token
     */
    Token invoke();

}
