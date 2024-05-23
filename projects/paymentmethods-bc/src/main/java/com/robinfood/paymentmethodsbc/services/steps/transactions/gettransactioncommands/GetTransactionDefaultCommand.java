package com.robinfood.paymentmethodsbc.services.steps.transactions.gettransactioncommands;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;

public class GetTransactionDefaultCommand implements GetTransactionCommandHandler {

    @Override
    public void invoke(
        TransactionsCommonOperations transactionsCommonOperations,
        TransactionsConfig transactionsConfig,
        Object pipe
    ) throws PaymentStepException, EntityNotFoundException {
        throw new PaymentStepException(
            StepType.GET_TRANSACTION,
            String.format(
                "No action has been taken at %s", getClass().getSimpleName()
            )
        );
    }
}
