package com.robinfood.paymentmethodsbc.services.steps.transactions.gettransactioncommands;

import com.robinfood.paymentmethodsbc.dto.steps.JmsTransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.RefundPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class GetTransactionFactory {
    private static final Map<String, GetTransactionCommandHandler> commandMap = new HashMap<>();
    static {
        commandMap.put(PaymentPipeDTO.class.getSimpleName(), new GetTransactionPaymentCommand());
        commandMap.put(RefundPipeDTO.class.getSimpleName(), new GetTransactionRefundCommand());
        commandMap.put(TransactionStatusPipeDTO.class.getSimpleName(), new GetTransactionTransactionStatusCommand());
        commandMap.put(
            JmsTransactionStatusPipeDTO.class.getSimpleName(), new GetTransactionJmsTransactionStatusCommand()
        );
    }

    private GetTransactionFactory() {}

    public static Optional<GetTransactionCommandHandler> getTransactionCommand(String className) {
        return Optional.ofNullable(commandMap.get(className));
    }
}
