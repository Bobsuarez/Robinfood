package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ActiveMQEvent;
import com.robinfood.usecases.processhandler.IProcessHandlerUseCase;
import com.robinfood.usecases.processhandler.ProcessHandlerUseCase;
import com.robinfood.utils.LogsUtil;


public class SubscriberChangeStatusPosHandler implements RequestHandler<ActiveMQEvent, Void> {

    private final IProcessHandlerUseCase iProcessHandlerUseCase;

    public SubscriberChangeStatusPosHandler(IProcessHandlerUseCase iProcessHandlerUseCase) {
        this.iProcessHandlerUseCase = iProcessHandlerUseCase;
    }

    public SubscriberChangeStatusPosHandler() {
        this.iProcessHandlerUseCase = new ProcessHandlerUseCase();
    }

    @Override
    public Void handleRequest(ActiveMQEvent activeMQEvent, Context context) {

        LogsUtil.getInstance(context);

        iProcessHandlerUseCase.invoke(activeMQEvent);

        return null;
    }

}
