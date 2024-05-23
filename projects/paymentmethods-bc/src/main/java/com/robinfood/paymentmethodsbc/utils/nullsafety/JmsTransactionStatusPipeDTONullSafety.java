package com.robinfood.paymentmethodsbc.utils.nullsafety;

import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;
import com.robinfood.paymentmethodsbc.dto.steps.JmsTransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.model.PaymentGateway;
import com.robinfood.paymentmethodsbc.model.Terminal;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Objects.requireNonNullElse;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateCountry;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateGeneralPaymentMethod;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validatePaymentGateway;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateTerminal;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateTransaction;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateTransactionDetail;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateTransactionUser;

public final class JmsTransactionStatusPipeDTONullSafety {

    private JmsTransactionStatusPipeDTONullSafety() {}

    public static JmsTransactionStatusPipeDTO validateJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return requireNonNullElse(jmsTransactionStatusPipeDTO, JmsTransactionStatusPipeDTO.builder().build());
    }

    public static JmsUpdateTransactionStatusDTO validateJmsUpdateTransactionStatusDTO(
        JmsUpdateTransactionStatusDTO jmsUpdateTransactionStatusDTO
    ) {
        return requireNonNullElse(jmsUpdateTransactionStatusDTO, JmsUpdateTransactionStatusDTO.builder().build());
    }

    public static JmsUpdateTransactionStatusDTO validateJmsUpdateTransactionStatusDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return requireNonNullElse(
            jmsTransactionStatusPipeDTO.getJmsUpdateTransactionStatusDTO(), 
            JmsUpdateTransactionStatusDTO.builder().build()
        );
    }


    public static String getUUidFromJmsUpdateTransactionStatusDTO(
        JmsUpdateTransactionStatusDTO jmsUpdateTransactionStatusDTO
    ) {
        return validateJmsUpdateTransactionStatusDTO(jmsUpdateTransactionStatusDTO).getUuid();
    }

    public static String getUuidFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateJmsUpdateTransactionStatusDTO(
                validateJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getJmsUpdateTransactionStatusDTO()
            ).getUuid()
        ).orElse(EMPTY);
    }

    public static Long getTransactionStatusFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateJmsUpdateTransactionStatusDTO(
                validateJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getJmsUpdateTransactionStatusDTO()
            ).getTransactionStatus()
        ).orElse(0L);
    }

    public static String getPaymentGatewayStatusFromJmsUpdateTransactionStatusDTO(
        JmsUpdateTransactionStatusDTO response
    ) {
        return Optional.ofNullable(
            validateJmsUpdateTransactionStatusDTO(response).getPaymentGatewayStatus()
        ).orElse(EMPTY);
    }

    public static String getPaymentGatewayLevelCategoryFromJmsUpdateTransactionStatusDTO(
        JmsUpdateTransactionStatusDTO response
    ) {
        return Optional.ofNullable(
            validateJmsUpdateTransactionStatusDTO(response).getPaymentGatewayLevelCategory()
        ).orElse(EMPTY);
    }

    public static String getUuidFromTransactionStatusPipeDTOTransaction(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransaction(
                validateJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getTransaction()
            ).getUuid()
        ).orElse(EMPTY);
    }

    public static Long getUserIdFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransaction(
                validateJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getTransaction()
            ).getUserId()
        ).orElse(0L);
    }

    public static BigDecimal getTotalFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransaction(
                validateJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getTransaction()
            ).getTotal()
        ).orElse(BigDecimal.ZERO);
    }

    public static Long getPaymentMethodIdFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO paymentPipeDTO
    ) {
        return Optional.ofNullable(
            validateGeneralPaymentMethod(
                validateTransaction(
                    validateJmsTransactionStatusPipeDTO(paymentPipeDTO).getTransaction()
                ).getPaymentMethod()
            ).getId()
        ).orElse(0L);
    }

    public static String getPaymentMethodNameFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO paymentPipeDTO
    ) {
        return Optional.ofNullable(
            validateGeneralPaymentMethod(
                validateTransaction(
                    validateJmsTransactionStatusPipeDTO(paymentPipeDTO).getTransaction()
                ).getPaymentMethod()
            ).getName()
        ).orElse(EMPTY);
    }

    public static Long getPlatformIdFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransactionDetail(
                validateJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getTransactionDetail()
            ).getOriginPlatformId()
        ).orElse(0L);
    }

    public static PaymentGateway getPaymentGatewayFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO paymentPipeDTO
    ) {
        return validatePaymentGateway(
            validateTransaction(
                validateJmsTransactionStatusPipeDTO(paymentPipeDTO).getTransaction()
            ).getPaymentGateway()
        );
    }

    public static Long getPaymentGatewayIdFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            getPaymentGatewayFromJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getId()
        ).orElse(0L);
    }

    public static Long getTransactionIdFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransaction(
                validateJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getTransaction()
            ).getId()
        ).orElse(0L);
    }

    public static String getDataphoneCodeFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransactionDetail(
                validateJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getTransactionDetail()
            ).getDataphoneCode()
        ).orElse(EMPTY);
    }

    public static Terminal getTerminalFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return validateTerminal(
            validateTransactionDetail(
                validateJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getTransactionDetail()
            ).getTerminal()
        );
    }

    public static String getTerminalUuidFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            getTerminalFromJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getUuid()
        ).orElse(EMPTY);
    }

    public static String getTerminalNameFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            getTerminalFromJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getName()
        ).orElse(EMPTY);
    }

    public static String getPaymentBciFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.of(
            getPaymentGatewayFromJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getName()
        ).orElse(EMPTY);
    }

    public static String getCountryNameFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateCountry(
                validateTransaction(
                    validateJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getTransaction()
                ).getCountry()
            ).getName()
        ).orElse(EMPTY);
    }

    public static Long getCountryIdFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateCountry(
                validateTransaction(
                    validateJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getTransaction()
                ).getCountry()
            ).getId()
        ).orElse(0L);
    }

    public static Long getStoreIdFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransactionDetail(
                validateTransaction(
                    validateJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getTransaction()
                ).getTransactionDetail()
            ).getStoreId()
        ).orElse(0L);
    }

    public static String getPhoneNumberFromJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) {
        return Optional.ofNullable(
            validateTransactionUser(
                validateTransaction(
                    validateJmsTransactionStatusPipeDTO(jmsTransactionStatusPipeDTO).getTransaction()
                ).getTransactionUser()
            ).getPhoneNumber()
        ).orElse(EMPTY);
    }


}
