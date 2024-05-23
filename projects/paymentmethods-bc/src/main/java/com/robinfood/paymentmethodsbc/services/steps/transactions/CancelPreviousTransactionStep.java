package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.components.providers.BCIProvider;
import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.SettingsDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.model.TransactionStatus;
import com.robinfood.paymentmethodsbc.repositories.TransactionDetailsRepository;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CancelPreviousTransactionStep implements StepActions {
    private static final String ENABLE_STEP_KEY = "cancelPreviousTransaction";
    private final TransactionsCommonOperations transactionsCommonOperations;
    private final TransactionDetailsRepository transactionDetailsRepository;
    private final TransactionsConfig transactionsConfig;
    private final BCIProvider bciProvider;
    private TransactionStatus transactionStatusCancel;
    private final JmsSendStatusTransactionStep jmsSendStatusTransactionStep;

    public CancelPreviousTransactionStep(
        TransactionsCommonOperations transactionsCommonOperations,
        TransactionDetailsRepository transactionDetailsRepository,
        TransactionsConfig transactionsConfig,
        BCIProvider bciProvider,
        JmsSendStatusTransactionStep jmsSendStatusTransactionStep
    ) {
        this.transactionsCommonOperations = transactionsCommonOperations;
        this.transactionDetailsRepository = transactionDetailsRepository;
        this.transactionsConfig = transactionsConfig;
        this.bciProvider = bciProvider;
        this.jmsSendStatusTransactionStep = jmsSendStatusTransactionStep;
    }
    
    @BasicLog
    @Override
    public void invoke(Object pipe) throws PaymentStepException, EntityNotFoundException {

        if (pipe instanceof PaymentPipeDTO) {
            invokeForTransactionPipeDTO((PaymentPipeDTO) pipe);
            return;
        }

        throw new PaymentStepException(
            StepType.CANCEL_PREVIOUS_TRANSACTION,
            String.format(
                "No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }

    private void invokeForTransactionPipeDTO(
        final PaymentPipeDTO paymentPipeDTO
    ) {
        this.transactionStatusCancel = getTransactionStatusCancel();

        if(omitCancellation(paymentPipeDTO.getSettings())) {
            log.info("Previous transaction cancellation step disabled");
            return;
        }

        List<TransactionDetail> listTransactions;
        listTransactions =
            transactionDetailsRepository
                .findByTransactionTransactionStatusIdAndTerminalIdAndTransactionCodeIsNotNull(
                    transactionsConfig.getStatusId(TransactionsConfig.STATUS_PENDING_ID),
                    paymentPipeDTO.getTerminal().getId()
                );

        listTransactions.forEach(x ->
                executeCancellation(
                    paymentPipeDTO,
                    x
                )
        );
    }

    private void executeCancellation(
        final PaymentPipeDTO pipe,
        final TransactionDetail transactionToCancel
    ){
        try {
            if(!pipe.getTransaction().getId().equals(transactionToCancel.getTransaction().getId())) {
                log.info(
                    "Canceling previous transaction with reference: {}",
                    transactionToCancel.getTransactionCode()
                );
                bciProvider.doCancelTransaction(pipe.getSettings(), transactionToCancel.getTransactionCode());
                updateTransaction(transactionToCancel);
            }
        } catch (PaymentMethodsException e) {
            log.error(
                String.format(
                    "Error canceling previous transaction %s",
                    transactionToCancel.getTransactionCode()
                ),
                e
            );
        }
    }

    private boolean omitCancellation(SettingsDTO settings){
        final Optional<SettingsDTO> settingsOpt = Optional.ofNullable(settings);

        boolean isSettingsNull = transactionStatusCancel == null
            || settingsOpt.isEmpty()
            || settingsOpt.map(SettingsDTO::getGatewayConfig).isEmpty()
            || settingsOpt.map(SettingsDTO::getGatewayConfig).map(x -> x.get(ENABLE_STEP_KEY)).isEmpty();

        return isSettingsNull ||
            settingsOpt
            .map(SettingsDTO::getGatewayConfig)
            .map(x -> x.get(ENABLE_STEP_KEY))
            .map(x -> !"true".equalsIgnoreCase(x))
            .filter(x -> x)
            .isPresent();
    }

    private void updateTransaction(TransactionDetail transactionDetail) {
        Transaction transaction = transactionDetail.getTransaction();
        transaction.setTransactionStatus(this.transactionStatusCancel);
        transaction.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));

        transactionsCommonOperations.saveTransaction(transaction);
        transactionsCommonOperations.saveTransactionStatusLog(
            transaction,
            "Internal cancellation, queued transaction",
            "",
            "",
            ""
        );
        notifyCancellation(transactionDetail);
    }

    private void notifyCancellation(TransactionDetail transactionDetail) {
        PaymentPipeDTO pipeTmp = PaymentPipeDTO
            .builder()
            .transaction(transactionDetail.getTransaction())
            .transactionDetail(transactionDetail)
            .notifyStatus(true)
            .build();
        try {
            jmsSendStatusTransactionStep.invoke(pipeTmp);
        } catch (PaymentStepException | EntityNotFoundException e) {
            log.error("Error notifying cancellation in previous transaction", e);
        }
    }

    private TransactionStatus getTransactionStatusCancel(){
        try {
            return transactionsCommonOperations.getTransactionStatusById(
                transactionsConfig.getStatusId(TransactionsConfig.STATUS_CANCELED_ID)
            );
        } catch (EntityNotFoundException e) {
            log.error("Error getting transaction status", e);
            return null;
        }
    }
}
