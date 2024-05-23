package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import com.robinfood.paymentmethodsbc.services.steps.transactions.gettransactioncommands.GetTransactionDefaultCommand;
import com.robinfood.paymentmethodsbc.services.steps.transactions.gettransactioncommands.GetTransactionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.robinfood.paymentmethodsbc.utils.JsonUtils.convertToJson;

@Slf4j
@Component
public class GetTransactionStep implements StepActions {
    private final TransactionsCommonOperations transactionsCommonOperations;
    private final TransactionsConfig transactionsConfig;

    public GetTransactionStep(
        TransactionsConfig transactionsConfig,
        TransactionsCommonOperations transactionsCommonOperations
    ) {
        this.transactionsConfig = transactionsConfig;
        this.transactionsCommonOperations = transactionsCommonOperations;
    }

    @BasicLog
    @Override
    public void invoke(Object pipe) throws PaymentStepException, EntityNotFoundException {

        GetTransactionFactory.getTransactionCommand(pipe.getClass().getSimpleName())
            .orElse(new GetTransactionDefaultCommand())
            .invoke(transactionsCommonOperations, transactionsConfig, pipe);
        log.info("Pipe after processing ==> {}", convertToJson(pipe));
    }
}
