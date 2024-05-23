package com.robinfood.paymentmethodsbc.services.steps.transactions;

import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.ACCEPTED;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.ATTEMPT_TO_CHANGE_STATUS_AFTER_ACCEPT;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.REJECTED;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.RETRY_FLAG_;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.RETRY_STATUS_FLAG;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.STATUS_ACCEPTED;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.STATUS_RESULT_;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.TRANSACTION_VALUE_NOT_EQ_TO_TOTAL;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.TRANSACTION_VALUE_NOT_EQ_TO_TOTAL_TEMPLATE;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.commands.transaction.SaveTransactionDefault;
import com.robinfood.paymentmethodsbc.commands.transaction.SaveTransactionFactory;
import com.robinfood.paymentmethodsbc.components.providers.BCIProvider;
import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO.TransactionStatusResponse;
import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;
import com.robinfood.paymentmethodsbc.dto.steps.JmsTransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.SettingsDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.enums.ValidateStatusEnum;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.mappers.JmsTransactionStatusChangeMapper;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.model.TransactionStatus;
import com.robinfood.paymentmethodsbc.services.FailedTransactionsOperations;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import com.robinfood.paymentmethodsbc.utils.FilterStatusNotificationUtils;
import com.robinfood.paymentmethodsbc.utils.LoggerReportUtils;
import com.robinfood.paymentmethodsbc.utils.TransactionLogger;

import com.robinfood.paymentmethodsbc.utils.nullsafety.BCITransactionStatusResponseDTONullSafety;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import static com.robinfood.paymentmethodsbc.constants.AppConstants.TRANSACTION_TYPE_PURCHASE;
import static com.robinfood.paymentmethodsbc.constants.AppConstants.TRANSACTION_TYPE_UNKNOWN;
import static com.robinfood.paymentmethodsbc.utils.JsonUtils.convertToJson;
import static com.robinfood.paymentmethodsbc.utils.LoggerReportUtils.reportJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.LoggerReportUtils.reportTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.BCITransactionStatusResponseDTONullSafety.validateTransactionStatusResponse;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.BCITransactionStatusResponseDTONullSafety.validateBCITransactionStatusResponseDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.validateJmsUpdateTransactionStatusDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateTransactionDetail;

/**
 * Implementación de TransactionStep. Actualiza el estado de la transacción
 */
@Slf4j
@Component
public class UpdateTransactionStatusStep implements StepActions {

    private final TransactionsCommonOperations transactionsCommonOperations;
    private final TransactionsConfig transactionsConfig;
    private final BCIProvider paymentGatewayProvider;
    private final FailedTransactionsOperations failedTransactionsOperations;


    public UpdateTransactionStatusStep(
        TransactionsCommonOperations transactionsCommonOperations,
        TransactionsConfig transactionsConfig, BCIProvider paymentGatewayProvider,
        FailedTransactionsOperations failedTransactionsOperations) {

        this.transactionsCommonOperations = transactionsCommonOperations;
        this.paymentGatewayProvider = paymentGatewayProvider;
        this.transactionsConfig = transactionsConfig;
        this.failedTransactionsOperations = failedTransactionsOperations;
    }

    @BasicLog
    @Override
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof TransactionStatusPipeDTO) {
            invokeForTransactionStatusPipeDTO((TransactionStatusPipeDTO) pipe);
            return;
        }

        if (pipe instanceof JmsTransactionStatusPipeDTO) {
            invokeForJmsTransactionStatusPipeDTO((JmsTransactionStatusPipeDTO) pipe);
            return;
        }

        throw new PaymentStepException(
            StepType.UPDATE_STATUS_TRANSACTION,
            String.format(
                "No se ha realizado ninguna acción en %s", getClass().getSimpleName()
            )
        );
    }

    private void invokeForTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) throws PaymentStepException, EntityNotFoundException {
        Transaction transaction = transactionStatusPipeDTO.getTransaction();
        TransactionLogger.invoke(transaction);
        BCITransactionStatusResponseDTO resultBCI;
        try {
            resultBCI = paymentGatewayProvider.doValidateStatus(
                transactionStatusPipeDTO.getSettings(), transactionStatusPipeDTO
            );
        } catch (PaymentMethodsException e) {

            processUpdateTransactionFailed(
                transactionStatusPipeDTO,
                transaction,
                e
            );

            throw new PaymentStepException(
                StepType.UPDATE_STATUS_TRANSACTION,
                e.getMessage()
            );
        }


        
        transaction = processUpdateTransactionResponse(transactionStatusPipeDTO, resultBCI);
        TransactionDetail transactionDetail =
            validateTransactionDetail(transactionStatusPipeDTO.getTransactionDetail());
        transactionDetail = processUpdateTransactionDetail(resultBCI, transactionDetail);

        transactionStatusPipeDTO.setTransaction(transaction);
        transactionStatusPipeDTO.setTransactionDetail(transactionDetail);
        transactionStatusPipeDTO.setTransactionStatusResultDTO(resultBCI);

        TransactionStatusResponse transactionStatus = BCITransactionStatusResponseDTONullSafety
            .validateTransactionStatusResponse(
                resultBCI.getTransaction()
            );
        BigDecimal paymentValue = transactionStatus.getTransactionValue();
        BigDecimal transactionTotal = transaction.getTotal();

        isTransactionValueNotEqToTotal(
            transactionTotal,
            paymentValue
        ).ifPresent(
            (Boolean trxValueNotEqToTotal) -> 
                alertTransactionValueNotEqToTotal(
                    trxValueNotEqToTotal,
                    buildAlertTransactionValueNotEqToTotalMessage(
                        transactionTotal,
                        paymentValue
                    ),
                    transactionStatusPipeDTO
            )
        );

        TransactionLogger.clear();
    }

    private void processUpdateTransactionFailed(
        TransactionStatusPipeDTO transactionStatusPipeDTO,
        Transaction transaction,
        PaymentMethodsException e
    ) {
        log.error(
            String.format(
                "Error en validación de estados BCI. Referencia %s",
                transactionStatusPipeDTO
                    .getTransactionStatusUpdateRequestDTO()
                    .getIdentificator()
            ), e
        );

        transactionsCommonOperations.saveTransactionLog(
            transaction,
            "Webhook notification request",
            transactionStatusPipeDTO
                .getTransactionStatusUpdateRequestDTO()
                .getNotification()
        );

        transactionsCommonOperations.saveTransactionLog(
            transaction,
            "Webhook notification failed",
            e.getMessage()
        );
    }

    private void invokeForJmsTransactionStatusPipeDTO(
        @NotNull JmsTransactionStatusPipeDTO pipe
    ) throws EntityNotFoundException {
        Transaction transaction = pipe.getTransaction();
        JmsUpdateTransactionStatusDTO jmsData = pipe.getJmsUpdateTransactionStatusDTO();
        TransactionLogger.invoke(transaction);
        Long statusId = jmsData.getTransactionStatus();

        TransactionStatus status = transactionsCommonOperations.getTransactionStatusById(statusId);

        transaction.setTransactionStatus(status);
        transaction.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));

        Long idStatusTransaction = transactionsCommonOperations.getTransactionById(transaction.getId())
            .getTransactionStatus().getId();

        transaction.setSucceeded(
            transactionsConfig.isStatusAccepted(statusId)
        );
        if (StringUtils.isBlank(transaction.getAuthorizationCode())) {
            transaction.setAuthorizationCode(jmsData.getAuthorizationCode());
        }
        saveTransaction(
            pipe.getSettings(),
            statusId,
            idStatusTransaction,
            transaction
        );

        TransactionDetail transactionDetail = pipe.getTransactionDetail();

        transactionsCommonOperations.saveTransactionDetail(
            processUpdateTransactionDetailFromJmsMessage(jmsData, transactionDetail)
        );
        pipe.setTransactionDetail(transactionDetail);

        transactionsCommonOperations.saveTransactionLog(
            transaction,
            "JMS Update Transaction Status",
            pipe.getJmsUpdateTransactionStatusJsonMessage()
        );

        pipe.setNotifyStatus(
            FilterStatusNotificationUtils.isNotify(
                pipe.getSettings(),
                transaction,
                idStatusTransaction,
                statusId
            )
        );
        saveTransactionStatusLog(
            idStatusTransaction,
            transaction,
            JmsTransactionStatusChangeMapper.bciTransactionStatusResponseDTO(jmsData),
            jmsData.getMessage()
            );

        if (transactionsConfig.isStatusRejected(transaction.getTransactionStatus().getId())) {
            failedTransactionsOperations.saveFailedTransaction(
                transaction,
                jmsData.getErrorCode(),
                jmsData.getErrorDescription(),
                TransactionsConfig.FAILED_GATEWAY_ID
            );
        }

        BigDecimal paymentValue = jmsData.getTransactionValue();
        BigDecimal transactionTotal = transaction.getTotal();

        isTransactionValueNotEqToTotal(
            transactionTotal,
            paymentValue
        ).ifPresent(
            (Boolean trxValueNotEqToTotal) ->
                alertJmsTransactionValueNotEqToTotal(
                    trxValueNotEqToTotal,
                    buildAlertTransactionValueNotEqToTotalMessage(
                        transactionTotal,
                        paymentValue
                    ),
                    pipe
                )
        );

        reportJmsTransactionStatusPipeDTO(idStatusTransaction, pipe);
        TransactionLogger.clear();
    }


    /**
     * Método encargado de procesar respuesta de BCI y actualizar datos respectivos.
     *
     * @param pipe      {@linkplain Transaction} datos de transacción
     * @param resultBCI {@linkplain BCITransactionStatusResponseDTO} respues de BCI
     * @return {@linkplain Transaction} datos de transacción
     * @throws EntityNotFoundException
     */
    private Transaction processUpdateTransactionResponse(
        final TransactionStatusPipeDTO pipe,
        final BCITransactionStatusResponseDTO resultBCI
    ) throws EntityNotFoundException {
        Transaction transaction = pipe.getTransaction();
        String notificationType = pipe.getTransactionStatusUpdateRequestDTO().getType();

        if (Objects.nonNull(resultBCI.getTransaction().getTransactionStatus()) &&
            !TRANSACTION_TYPE_UNKNOWN.equals(notificationType)
        ) {
            TransactionStatus transactionStatus = transactionsCommonOperations.getTransactionStatusById(
                resultBCI.getTransaction().getTransactionStatus()
            );

            String statusLogComment = "Webhook: " + notificationType + " ";

            boolean isAccepted;
            if (TRANSACTION_TYPE_PURCHASE.equals(notificationType)) {
                isAccepted = transactionsConfig.getStatusId(TransactionsConfig.STATUS_ACCEPTED_ID)
                    .equals(transactionStatus.getId());
                transaction.setSucceeded(isAccepted);

                if (Objects.isNull(transaction.getAuthorizationCode())) {
                    transaction.setAuthorizationCode(resultBCI.getTransaction().getAuthorizationCode());
                }
            } else {
                isAccepted = transactionsConfig.getStatusId(TransactionsConfig.STATUS_REFUND_ACCEPTED_ID)
                    .equals(transactionStatus.getId());
            }
            transaction.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
            transaction.setTransactionStatus(transactionStatus);

            Long idStatusTransaction = transactionsCommonOperations.getTransactionById(transaction.getId())
                .getTransactionStatus().getId();
            log.info("Status Transaction with id : {}", idStatusTransaction);

            saveTransaction(pipe.getSettings(),
                resultBCI.getTransaction().getTransactionStatus(),
                idStatusTransaction,
                transaction
            );

            pipe.setNotifyStatus(
                FilterStatusNotificationUtils.isNotify(
                    pipe.getSettings(),
                    transaction,
                    idStatusTransaction,
                    resultBCI.getTransaction().getTransactionStatus()
                )
            );

            if (!pipe.isNotifyStatus()) {
                log.info("Webhook will not notify status via activemq");
            }

            if (isAccepted) {
                statusLogComment += ACCEPTED;
            } else {
                statusLogComment += REJECTED;
            }

            saveTransactionStatusLog(
                idStatusTransaction, transaction, resultBCI, statusLogComment
            );
            pipe.setTransactionStatusResultDTO(resultBCI);
            reportTransactionStatusPipeDTO(idStatusTransaction, pipe);
        }

        transactionsCommonOperations.saveTransactionLog(
            transaction,
            "Webhook notification request",
            pipe.getTransactionStatusUpdateRequestDTO().getNotification()
        );

        transactionsCommonOperations.saveTransactionLog(
            transaction, "BCI notification response", convertToJson(resultBCI)
        );
        return transaction;
    }

    private Transaction saveTransaction(
        SettingsDTO settingsDTO,
        Long statusResultBCI,
        Long idStatusTransaction,
        Transaction transaction
    ) {
        boolean retryFlag = !retryStatusFlag(settingsDTO);
        String retryOption = RETRY_FLAG_
            .concat(String.valueOf(retryFlag).toUpperCase(Locale.getDefault()));
        boolean statusResult = ValidateStatusEnum.isGetStatusResult(idStatusTransaction,
            statusResultBCI);
        String statusResultOption = STATUS_RESULT_
            .concat(String.valueOf(statusResult).toUpperCase(Locale.getDefault()));

        return SaveTransactionFactory.getOperation(retryOption)
            .orElse(
                SaveTransactionFactory.getOperation(statusResultOption)
                    .orElse(new SaveTransactionDefault())
            )
            .saveTransaction(transactionsCommonOperations, transaction);
    }

    private void saveTransactionStatusLog(
        Long idStatusTransaction,
        Transaction transaction,
        BCITransactionStatusResponseDTO resultBCI,
        String statusLogComment
    ) throws EntityNotFoundException {

        TransactionStatusResponse transactionStatusResponse =
            BCITransactionStatusResponseDTONullSafety.validateTransactionStatusResponse(
                BCITransactionStatusResponseDTONullSafety.validateBCITransactionStatusResponseDTO(
                    resultBCI
                ).getTransaction()
            );

        if (idStatusTransaction == STATUS_ACCEPTED) {
            log.info("Change status after accept with transaction : {} and statusLogComment: "
                    + "ATTEMPT_TO_CHANGE_STATUS_AFTER_ACCEPT",
                transaction);
            Transaction saveTransaction = transactionsCommonOperations.getTransactionById(transaction.getId());
            transactionsCommonOperations.saveTransactionStatusLog(
                saveTransaction,
                ATTEMPT_TO_CHANGE_STATUS_AFTER_ACCEPT,
                transactionStatusResponse.getTransactionCode(),
                transactionStatusResponse.getTransactionReference(),
                transactionStatusResponse.getAuthorizationCode()
            );
        } else {
            log.info(
                "Save info in transaction_status_logs with transaction {} and statusLogComment {}",
                transaction, statusLogComment);
            transactionsCommonOperations.saveTransactionStatusLog(
                transaction,
                statusLogComment,
                transactionStatusResponse.getTransactionCode(),
                transactionStatusResponse.getTransactionReference(),
                transactionStatusResponse.getAuthorizationCode()
            );
        }
    }

    private boolean retryStatusFlag (SettingsDTO settingsDTO) {
        return Boolean.valueOf(
            Optional.ofNullable(
                    Optional.ofNullable(settingsDTO)
                        .orElse(SettingsDTO.builder()
                            .gatewayConfig(Map.of(RETRY_STATUS_FLAG,"false"))
                            .build()).getGatewayConfig())
                .filter(gatewayConfig -> gatewayConfig.containsKey(RETRY_STATUS_FLAG))
                .orElse(Map.of(RETRY_STATUS_FLAG,"false")).get(RETRY_STATUS_FLAG)
        );
    }

    private static TransactionDetail processUpdateTransactionDetailFromJmsMessage(
        JmsUpdateTransactionStatusDTO jmsUpdateTransactionStatusDTO,
        TransactionDetail transactionDetail
    ) {
        if (StringUtils.isBlank(transactionDetail.getTransactionCode())) {
            transactionDetail.setTransactionCode(
                validateJmsUpdateTransactionStatusDTO(jmsUpdateTransactionStatusDTO).getTransactionCode()
            );
        }
        if (StringUtils.isBlank(transactionDetail.getTransactionReference())) {
            transactionDetail.setTransactionReference(
                validateJmsUpdateTransactionStatusDTO(jmsUpdateTransactionStatusDTO).getTransactionReference()
            );
        }
        if (StringUtils.isBlank(transactionDetail.getDataphoneCode())) {
            transactionDetail.setDataphoneCode(
                validateJmsUpdateTransactionStatusDTO(jmsUpdateTransactionStatusDTO).getDataphoneCode()
            );
        }
        if (StringUtils.isBlank(transactionDetail.getCreditCardMaskedNumber())) {
            transactionDetail.setCreditCardMaskedNumber(
                validateJmsUpdateTransactionStatusDTO(jmsUpdateTransactionStatusDTO).getCreditCardMaskedNumber()
            );
        }
        if (StringUtils.isBlank(transactionDetail.getAccountType())) {
            transactionDetail.setAccountType(
                validateJmsUpdateTransactionStatusDTO(jmsUpdateTransactionStatusDTO).getAccountType()
            );
        }
        if (StringUtils.isBlank(transactionDetail.getFranchise())) {
            transactionDetail.setFranchise(
                validateJmsUpdateTransactionStatusDTO(jmsUpdateTransactionStatusDTO).getFranchise()
            );
        }
        if (StringUtils.isBlank(transactionDetail.getDataphoneReceiptNumber())) {
            transactionDetail.setDataphoneReceiptNumber(
                validateJmsUpdateTransactionStatusDTO(jmsUpdateTransactionStatusDTO).getDataphoneReceiptNumber()
            );
        }
        if (Objects.isNull(transactionDetail.getInstallments())) {
            transactionDetail.setInstallments(
                validateJmsUpdateTransactionStatusDTO(jmsUpdateTransactionStatusDTO).getInstallments()
            );
        }
        if (StringUtils.isBlank(transactionDetail.getEstablishmentCode())) {
            transactionDetail.setEstablishmentCode(
                validateJmsUpdateTransactionStatusDTO(jmsUpdateTransactionStatusDTO).getEstablishmentCode()
            );
        }
        return transactionDetail;
    }

    private TransactionDetail processUpdateTransactionDetail(
        BCITransactionStatusResponseDTO resultBCI,
        TransactionDetail transactionDetail
    ) {
        transactionDetail.setTransactionCode(
            validateTransactionStatusResponse(
                validateBCITransactionStatusResponseDTO(resultBCI).getTransaction()
            ).getTransactionCode()
        );
        transactionDetail.setDataphoneCode(
            validateTransactionStatusResponse(
                validateBCITransactionStatusResponseDTO(resultBCI).getTransaction()
            ).getDataphoneCode()
        );
        transactionDetail.setCreditCardMaskedNumber(
            validateTransactionStatusResponse(
                validateBCITransactionStatusResponseDTO(resultBCI).getTransaction()
            ).getCreditCardMaskedNumber()
        );
        transactionDetail.setAccountType(
            validateTransactionStatusResponse(
                validateBCITransactionStatusResponseDTO(resultBCI).getTransaction()
            ).getAccountType()
        );
        transactionDetail.setFranchise(
            validateTransactionStatusResponse(
                validateBCITransactionStatusResponseDTO(resultBCI).getTransaction()
            ).getFranchise()
        );
        transactionDetail.setDataphoneReceiptNumber(
            validateTransactionStatusResponse(
                validateBCITransactionStatusResponseDTO(resultBCI).getTransaction()
            ).getDataphoneReceiptNumber()
        );
        transactionDetail.setInstallments(
            validateTransactionStatusResponse(
                validateBCITransactionStatusResponseDTO(resultBCI).getTransaction()
            ).getInstallments()
        );
        transactionDetail.setEstablishmentCode(
            validateTransactionStatusResponse(
                validateBCITransactionStatusResponseDTO(resultBCI).getTransaction()
            ).getEstablishmentCode()
        );

        return transactionsCommonOperations.saveTransactionDetail(
            transactionDetail
        );
    }

    private static Optional<Boolean> isTransactionValueNotEqToTotal(
        BigDecimal transactionValue, 
        BigDecimal paymentValue
    ){
        log.info(
            "Validate TransactionValue {} vs PaymentValue {}", 
            transactionValue, paymentValue
        );

        return Optional.ofNullable(paymentValue)
                .map((BigDecimal value) -> value.compareTo(transactionValue)!=0);
            
    }

    private void alertTransactionValueNotEqToTotal(
        Boolean isTransactionValueNotEqToTotal, 
        String transactionValueNotEqToTotalMessage,
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ){
        Optional.of(isTransactionValueNotEqToTotal)
        .filter(Boolean::booleanValue)
        .ifPresent((Boolean alert) ->
            LoggerReportUtils.doValidateStatusAlertLogger(
                transactionValueNotEqToTotalMessage,
                TRANSACTION_VALUE_NOT_EQ_TO_TOTAL,
                transactionStatusPipeDTO 
            )
        );
    }

    private void alertJmsTransactionValueNotEqToTotal(
        Boolean isTransactionValueNotEqToTotal,
        String transactionValueNotEqToTotalMessage,
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ){
        Optional.of(isTransactionValueNotEqToTotal)
        .filter(Boolean::booleanValue)
        .ifPresent((Boolean alert) ->
            LoggerReportUtils.doValidateStatusAlertLogger(
                transactionValueNotEqToTotalMessage,
                TRANSACTION_VALUE_NOT_EQ_TO_TOTAL,
                jmsTransactionStatusPipeDTO 
            )
        );
    }

    private String buildAlertTransactionValueNotEqToTotalMessage(
        BigDecimal transactionTotal, 
        BigDecimal paymentValue
    ){
        return String.format(
            TRANSACTION_VALUE_NOT_EQ_TO_TOTAL_TEMPLATE,
            paymentValue,
            transactionTotal
        );        
    }
}
