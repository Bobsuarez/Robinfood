package com.robinfood.paymentmethodsbc.utils;

import com.robinfood.paymentmethodsbc.dto.PaymentResponseDTO;
import com.robinfood.paymentmethodsbc.dto.StatusResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentInitResponseDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;
import com.robinfood.paymentmethodsbc.dto.steps.JmsTransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StatusEnum;

import com.robinfood.paymentmethodsbc.utils.nullsafety.BCITransactionStatusResponseDTONullSafety;

import java.util.Optional;

import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.DASH;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.BCITransactionStatusResponseDTONullSafety.validateTransactionStatusResponse;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.getUUidFromJmsUpdateTransactionStatusDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.validateJmsTransactionStatusPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.JmsTransactionStatusPipeDTONullSafety.validateJmsUpdateTransactionStatusDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentInitResponseDTONullSafety.validatePaymentInitResponseDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentInitResponseDTONullSafety.validateStatusDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getUuidFromPaymentPipeDTOTransaction;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.StatusResponseDTONullSafety.validateStatusResponseDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.TransactionStatusPipeDTONullSafety.getUuidFromTransactionStatusPipeDTOTransaction;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public final class LoggerNullSafeUtils {

    private LoggerNullSafeUtils() {}

    public static String getPaymentReportDTOId(
        PaymentPipeDTO paymentPipeDTO,
        PaymentResponseDTO response
    ) {
        return getUuidFromPaymentPipeDTOTransaction(paymentPipeDTO)
            .concat(DASH)
            .concat(StatusEnum.getStatusCode(response.getTransactionStatus()).getStatusCode());
    }

    public static String getPaymentReportDTOId(
        PaymentPipeDTO paymentPipeDTO,
        PaymentInitResponseDTO response
    ) {
        return getUuidFromPaymentPipeDTOTransaction(paymentPipeDTO)
            .concat(DASH)
            .concat(StatusEnum.getStatusCode(response.getStatus().getId()).getStatusCode());
    }

    public static String getPaymentReportDTOId(
        TransactionStatusPipeDTO transactionStatusPipeDTO,
        StatusResponseDTO response
    ) {
        return getUuidFromTransactionStatusPipeDTOTransaction(transactionStatusPipeDTO)
            .concat(DASH)
            .concat(
                StatusEnum.getStatusCode(
                    validateTransactionStatusResponse(
                        BCITransactionStatusResponseDTONullSafety.validateBCITransactionStatusResponseDTO(
                            validateStatusResponseDTO(response).getStatusResult()
                        ).getTransaction()
                    ).getTransactionStatus()
                ).getStatusCode()
            );
    }

    public static String getPaymentReportDTOId(
        JmsUpdateTransactionStatusDTO jmsUpdateTransactionStatusDTO
    ) {
        return getUUidFromJmsUpdateTransactionStatusDTO(jmsUpdateTransactionStatusDTO)
                .concat(DASH)
                .concat(StatusEnum.getStatusCode(
                    jmsUpdateTransactionStatusDTO.getTransactionStatus()
                ).getStatusCode());
    }

    public static String getPaymentReportDTOId(
        JmsTransactionStatusPipeDTO pipeDTO
    ) {
        pipeDTO = validateJmsTransactionStatusPipeDTO(pipeDTO);
        return getUUidFromJmsUpdateTransactionStatusDTO(pipeDTO.getJmsUpdateTransactionStatusDTO())
            .concat(DASH)
            .concat(StatusEnum.getStatusCode(
                validateJmsUpdateTransactionStatusDTO(pipeDTO.getJmsUpdateTransactionStatusDTO())
                    .getTransactionStatus()
            ).getStatusCode());
    }

    public static String getStatusDTOName(PaymentInitResponseDTO paymentInitResponseDTO) {
        return Optional.ofNullable(
            validateStatusDTO(
                validatePaymentInitResponseDTO(paymentInitResponseDTO).getStatus()
            ).getName()
        ).orElse(EMPTY);
    }
}
