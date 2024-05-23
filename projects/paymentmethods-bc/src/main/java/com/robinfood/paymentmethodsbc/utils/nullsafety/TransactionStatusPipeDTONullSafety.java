package com.robinfood.paymentmethodsbc.utils.nullsafety;

import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.model.PaymentGateway;
import com.robinfood.paymentmethodsbc.model.Terminal;

import java.math.BigDecimal;
import java.util.Optional;

import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateCountry;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateGeneralPaymentMethod;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validatePaymentGateway;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateTerminal;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateTransaction;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateTransactionDetail;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateTransactionUser;
import static java.util.Objects.requireNonNullElse;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public final class TransactionStatusPipeDTONullSafety {

    private TransactionStatusPipeDTONullSafety() {}

    public static TransactionStatusPipeDTO validateTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return requireNonNullElse(transactionStatusPipeDTO, TransactionStatusPipeDTO.builder().build());
    }

    public static String getUuidFromTransactionStatusPipeDTOTransaction(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransaction(
                validateTransactionStatusPipeDTO(transactionStatusPipeDTO).getTransaction()
            ).getUuid()
        ).orElse(EMPTY);
    }

    public static Long getUserIdFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransaction(
                validateTransactionStatusPipeDTO(transactionStatusPipeDTO).getTransaction()
            ).getUserId()
        ).orElse(0L);
    }

    public static BigDecimal getTotalFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransaction(
                validateTransactionStatusPipeDTO(transactionStatusPipeDTO).getTransaction()
            ).getTotal()
        ).orElse(BigDecimal.ZERO);
    }

    public static Long getPaymentMethodIdFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO paymentPipeDTO
    ) {
        return Optional.ofNullable(
            validateGeneralPaymentMethod(
                validateTransaction(
                    validateTransactionStatusPipeDTO(paymentPipeDTO).getTransaction()
                ).getPaymentMethod()
            ).getId()
        ).orElse(0L);
    }

    public static String getPaymentMethodNameFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO paymentPipeDTO
    ) {
        return Optional.ofNullable(
            validateGeneralPaymentMethod(
                validateTransaction(
                    validateTransactionStatusPipeDTO(paymentPipeDTO).getTransaction()
                ).getPaymentMethod()
            ).getName()
        ).orElse(EMPTY);
    }

    public static Long getPlatformIdFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransactionDetail(
                validateTransactionStatusPipeDTO(transactionStatusPipeDTO).getTransactionDetail()
            ).getOriginPlatformId()
        ).orElse(0L);
    }

    public static PaymentGateway getPaymentGatewayFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO paymentPipeDTO
    ) {
        return validatePaymentGateway(
            validateTransaction(
                validateTransactionStatusPipeDTO(paymentPipeDTO).getTransaction()
            ).getPaymentGateway()
        );
    }

    public static Long getPaymentGatewayIdFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            getPaymentGatewayFromTransactionStatusPipeDTO(transactionStatusPipeDTO).getId()
        ).orElse(0L);
    }

    public static Long getTransactionIdFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransaction(
                validateTransactionStatusPipeDTO(transactionStatusPipeDTO).getTransaction()
            ).getId()
        ).orElse(0L);
    }

    public static String getDataphoneCodeFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransactionDetail(
                validateTransactionStatusPipeDTO(transactionStatusPipeDTO).getTransactionDetail()
            ).getDataphoneCode()
        ).orElse(EMPTY);
    }

    public static Terminal getTerminalFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return validateTerminal(
            validateTransactionDetail(
                validateTransactionStatusPipeDTO(transactionStatusPipeDTO).getTransactionDetail()
            ).getTerminal()
        );
    }

    public static String getTerminalUuidFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            getTerminalFromTransactionStatusPipeDTO(transactionStatusPipeDTO).getUuid()
        ).orElse(EMPTY);
    }

    public static String getTerminalNameFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            getTerminalFromTransactionStatusPipeDTO(transactionStatusPipeDTO).getName()
        ).orElse(EMPTY);
    }

    public static String getPaymentBciFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return Optional.of(
            getPaymentGatewayFromTransactionStatusPipeDTO(transactionStatusPipeDTO).getName()
        ).orElse(EMPTY);
    }

    public static String getCountryNameFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateCountry(
                validateTransaction(
                    validateTransactionStatusPipeDTO(transactionStatusPipeDTO).getTransaction()
                ).getCountry()
            ).getName()
        ).orElse(EMPTY);
    }

    public static Long getCountryIdFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateCountry(
                validateTransaction(
                    validateTransactionStatusPipeDTO(transactionStatusPipeDTO).getTransaction()
                ).getCountry()
            ).getId()
        ).orElse(0L);
    }

    public static Long getStoreIdFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransactionDetail(
                validateTransaction(
                    validateTransactionStatusPipeDTO(transactionStatusPipeDTO).getTransaction()
                ).getTransactionDetail()
            ).getStoreId()
        ).orElse(0L);
    }

    public static String getPhoneNumberFromTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransactionUser(
                validateTransaction(
                    validateTransactionStatusPipeDTO(transactionStatusPipeDTO).getTransaction()
                ).getTransactionUser()
            ).getPhoneNumber()
        ).orElse(EMPTY);
    }

}
