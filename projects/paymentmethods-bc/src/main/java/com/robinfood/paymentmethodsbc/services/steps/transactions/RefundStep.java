package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.components.providers.BCIProvider;
import com.robinfood.paymentmethodsbc.dto.PaymentResponseDTO;
import com.robinfood.paymentmethodsbc.dto.steps.RefundPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.model.TransactionStatus;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * Implementación de TransactionStep. Genera la transacción haciendo uso del payment gateway respectivo
 */
@Slf4j
@Component
public class RefundStep implements StepActions {
    private final BCIProvider paymentGatewayProvider;
    private final TransactionsCommonOperations transactionsCommonOperations;


    public RefundStep(
        BCIProvider paymentGatewayProvider,
        TransactionsCommonOperations transactionsCommonOperations

    ) {
        this.paymentGatewayProvider = paymentGatewayProvider;
        this.transactionsCommonOperations = transactionsCommonOperations;
    }

    @Override
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof RefundPipeDTO) {
            invokeForRefundPipeDTO((RefundPipeDTO) pipe);

            return;
        }

        throw new PaymentStepException(
            StepType.GENERATE_REFUND,
            String.format(
                "No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }

    private void invokeForRefundPipeDTO(RefundPipeDTO refundPipeDTO)
        throws EntityNotFoundException {
        if (!refundPipeDTO.isBciProcessRefund()) {
            return;
        }

        Transaction transaction = refundPipeDTO.getOriginalTransaction();
        TransactionDetail transactionDetail = refundPipeDTO.getOriginalTransactionDetail();

        try {
            PaymentResponseDTO paymentResponse = paymentGatewayProvider.doRefund(
                refundPipeDTO.getSettings().getGatewayConfig(),
                refundPipeDTO.getSettings().getOrchConfig(),
                transactionDetail.getTransactionReference(),
                transactionDetail.getTransactionCode(),
                refundPipeDTO.getRefundReason()
            );

            updateRefundTransaction(transaction, paymentResponse);

            transactionsCommonOperations.saveTransactionLog(
                transaction,
                "BCI Refund Response",
                paymentResponse.getBciResponseBody()
            );
        } catch (PaymentMethodsException e) {
            log.error("Error generating refund with provider", e);

            transactionsCommonOperations.saveTransactionStatusLog(
                transaction,
                "BCI: Refund failed (ex)",
                null,
                null,
                null
            );

            transactionsCommonOperations.saveTransactionLog(
                transaction,
                "BCI Refund Response (ex)",
                e.getMessage()
            );
        }
    }

    public void updateRefundTransaction(
        Transaction transaction,
        PaymentResponseDTO paymentResponse
    )
        throws EntityNotFoundException {
        TransactionStatus transactionStatus = transactionsCommonOperations.getTransactionStatusById(
            paymentResponse.getTransactionStatus()
        );

        transaction.setTransactionStatus(transactionStatus);
        transaction.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));

        transactionsCommonOperations.saveTransaction(transaction);

        String comment = transactionStatus.getName();
        if (Objects.nonNull(paymentResponse.getErrorCode())) {
            comment = transactionStatus.getName();
        }

        transactionsCommonOperations.saveTransactionStatusLog(
            transaction,
            "BCI: " + comment,
            paymentResponse.getTransactionCode(),
            paymentResponse.getTransactionReference(),
            paymentResponse.getAuthorizationCode()
        );
    }
}
