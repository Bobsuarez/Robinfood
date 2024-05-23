package com.robinfood.usecases.processhandler;

import com.amazonaws.services.lambda.runtime.events.ActiveMQEvent;

/**
 * Contract that gets the service token
 */
public interface IProcessHandlerUseCase {

    void invoke(ActiveMQEvent activeMQEvent);
}
