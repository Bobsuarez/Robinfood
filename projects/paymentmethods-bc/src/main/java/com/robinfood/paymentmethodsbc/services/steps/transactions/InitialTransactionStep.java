package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;

import com.robinfood.paymentmethodsbc.services.steps.transactions.initialtransactioncommands.InitialTransactionDefaultCommand;
import com.robinfood.paymentmethodsbc.services.steps.transactions.initialtransactioncommands.InitialTransactionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.robinfood.paymentmethodsbc.utils.JsonUtils.convertToJson;

/**
 * Implementación de TransactionStep. Genera la transacción haciendo uso del payment gateway respectivo
 */
@Slf4j
@Component
public class InitialTransactionStep implements StepActions {
    private final TransactionsCommonOperations transactionsCommonOperations;
    private final TransactionsConfig transactionsConfig;

    public InitialTransactionStep(
        TransactionsCommonOperations transactionsCommonOperations,
        TransactionsConfig transactionsConfig
    ) {
        this.transactionsCommonOperations = transactionsCommonOperations;
        this.transactionsConfig = transactionsConfig;
    }

    @BasicLog
    @Override
    public void invoke(Object pipe) throws PaymentStepException, EntityNotFoundException {

        InitialTransactionFactory.getInitialTransactionCommand(pipe.getClass().getSimpleName())
            .orElse(new InitialTransactionDefaultCommand())
            .invoke(transactionsCommonOperations, transactionsConfig, pipe);
        log.info("Pipe after processing ==> {}", convertToJson(pipe));
    }
}
