package com.robinfood.paymentmethodsbc.services.steps.transactions.initialtransactioncommands;

import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.RefundPipeDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class InitialTransactionFactory {
    private static final Map<String, InitialTransactionCommandHandler> commandMap = new HashMap<>();
    static {
        commandMap.put(PaymentPipeDTO.class.getSimpleName(), new InitialPaymentCommand());
        commandMap.put(RefundPipeDTO.class.getSimpleName(), new InitialRefundCommand());
    }

    private InitialTransactionFactory() {}

    public static Optional<InitialTransactionCommandHandler> getInitialTransactionCommand(String className) {
        return Optional.ofNullable(commandMap.get(className));
    }
}
