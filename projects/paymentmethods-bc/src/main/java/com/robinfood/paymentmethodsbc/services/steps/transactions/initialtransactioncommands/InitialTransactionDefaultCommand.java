package com.robinfood.paymentmethodsbc.services.steps.transactions.initialtransactioncommands;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;

public class InitialTransactionDefaultCommand implements InitialTransactionCommandHandler {

    @Override
    public void invoke(
        TransactionsCommonOperations transactionsCommonOperations,
        TransactionsConfig transactionsConfig,
        Object pipe
    ) throws EntityNotFoundException, PaymentStepException {
        throw new PaymentStepException(
            StepType.INITIAL_TRANSACTION,
            String.format(
                "No action has been taken at %s", getClass().getSimpleName()
            )
        );
    }
}
