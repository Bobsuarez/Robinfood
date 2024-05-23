package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.components.providers.BCIProvider;
import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.PaymentResponseDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.model.TransactionStatus;
import com.robinfood.paymentmethodsbc.services.FailedTransactionsOperations;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.robinfood.paymentmethodsbc.constants.MessageConstants.BCI_PAYMENT_RESPONSE;
import static org.apache.commons.lang3.StringUtils.isNotBlank;


@Slf4j
@Component
public class PaymentStep implements StepActions {
    private final BCIProvider paymentGatewayProvider;
    private final TransactionsCommonOperations transactionsCommonOperations;
    private final FailedTransactionsOperations failedTransactionsOperations;
    private final TransactionsConfig transactionsConfig;

    public PaymentStep(
        BCIProvider paymentGatewayProvider,
        TransactionsCommonOperations transactionsCommonOperations,
        FailedTransactionsOperations failedTransactionsOperations,
        TransactionsConfig transactionsConfig
    ) {
        this.paymentGatewayProvider = paymentGatewayProvider;
        this.transactionsCommonOperations = transactionsCommonOperations;
        this.failedTransactionsOperations = failedTransactionsOperations;
        this.transactionsConfig = transactionsConfig;
    }

    @BasicLog
    @Override
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof PaymentPipeDTO) {
            invokeForTransactionPipeDTO((PaymentPipeDTO) pipe);
            return;
        }

        throw new PaymentStepException(
            StepType.GENERATE_PAYMENT,
            String.format(
                "No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }

    private void invokeForTransactionPipeDTO(PaymentPipeDTO paymentPipeDTO)
        throws EntityNotFoundException {
        Transaction transaction = paymentPipeDTO.getTransaction();
        TransactionDetail transactionDetail = paymentPipeDTO.getTransactionDetail();

        try {
            PaymentResponseDTO paymentResponse = paymentGatewayProvider.doPayment(
                paymentPipeDTO.getSettings(),
                paymentPipeDTO
            );

            updateTransaction(transaction, transactionDetail, paymentResponse, paymentPipeDTO);

            transactionsCommonOperations.saveTransactionLog(
                transaction,
                BCI_PAYMENT_RESPONSE,
                paymentResponse.getBciResponseBody()
            );
            log.info("Save info to Failed Transaction with error: {}",
                paymentResponse.getPaymentGatewayLevelCategory());

            if (isNotBlank(paymentResponse.getPaymentGatewayStatus())
                || isNotBlank(paymentResponse.getPaymentGatewayLevelCategory())){
                failedTransactionsOperations.saveFailedTransaction(
                    transaction,
                    paymentResponse.getPaymentGatewayLevelCategory(),
                    paymentResponse.getPaymentGatewayStatus(),
                    TransactionsConfig.FAILED_INTERNAL_ID
                );
            }
        } catch (PaymentMethodsException e) {
            log.error("Error generating transaction", e);

            TransactionStatus status = transactionsCommonOperations.getTransactionStatusById(
                transactionsConfig.getStatusId(
                    TransactionsConfig.STATUS_ERROR_ID
                )
            );

            transaction.setTransactionStatus(status);

            transactionsCommonOperations.saveTransaction(transaction);

            transactionsCommonOperations.saveTransactionStatusLog(
                transaction,
                e.getMessage(),
                transactionDetail.getTransactionCode(),
                transactionDetail.getTransactionReference(),
                transaction.getAuthorizationCode()
            );

            transactionsCommonOperations.saveTransactionLog(
                transaction,
                "Payment Exception",
                e.getMessage()
            );

            failedTransactionsOperations.saveFailedTransaction(
                transaction,
                "",
                e.getMessage(),
                TransactionsConfig.FAILED_INTERNAL_ID
            );
        }
    }

    public void updateTransaction(
        Transaction transaction,
        TransactionDetail transactionDetail,
        PaymentResponseDTO paymentResponse,
        PaymentPipeDTO paymentPipeDTO
    )
        throws EntityNotFoundException {
        TransactionStatus transactionStatus = transactionsCommonOperations.getTransactionStatusById(
            paymentResponse.getTransactionStatus()
        );

        transaction.setTransactionStatus(transactionStatus);
        transaction.setAuthorizationCode(
            paymentResponse.getAuthorizationCode()
        );
        transaction.setSucceeded(
            transactionsConfig.isStatusAccepted(
                paymentResponse.getTransactionStatus()
            )
        );
        transaction.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));

        transactionDetail.setTransactionCode(
            paymentResponse.getTransactionCode()
        );
        transactionDetail.setTransactionReference(
            paymentResponse.getTransactionReference()
        );
        transactionDetail.setDataphoneCode(paymentResponse.getDataphoneCode());
        transactionDetail.setCreditCardMaskedNumber(paymentResponse.getCreditCardMaskedNumber());
        transactionDetail.setAccountType(paymentResponse.getAccountType());
        transactionDetail.setFranchise(paymentResponse.getFranchise());
        transactionDetail.setDataphoneReceiptNumber(paymentResponse.getDataphoneReceiptNumber());
        transactionDetail.setInstallments(paymentResponse.getInstallments());
        transactionDetail.setEstablishmentCode(paymentResponse.getEstablishmentCode());

        transactionsCommonOperations.saveTransactionDetail(transactionDetail);
        transactionsCommonOperations.saveTransaction(transaction);

        paymentPipeDTO.setNotifyStatus(
            transactionsCommonOperations.canNotifyStatus(transaction)
        );

        if (!paymentPipeDTO.isNotifyStatus()) {
            log.info("Payment gateway will not notify status via activemq");
        }

        transactionsCommonOperations.saveTransactionStatusLog(
            transaction,
            paymentResponse.getMessage(),
            transactionDetail.getTransactionCode(),
            transactionDetail.getTransactionReference(),
            transaction.getAuthorizationCode()
        );

        if (transactionsConfig.isStatusRejected(transaction.getTransactionStatus().getId())) {
            failedTransactionsOperations.saveFailedTransaction(
                transaction,
                paymentResponse.getErrorCode(),
                paymentResponse.getErrorDescription(),
                TransactionsConfig.FAILED_GATEWAY_ID
            );
        }
    }
}
