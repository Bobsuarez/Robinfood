package com.robinfood.paymentmethodsbc.utils.nullsafety;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.PaymentMethodDTO.PaymentReferencePaymentMethod;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.model.PaymentGateway;
import com.robinfood.paymentmethodsbc.model.Platform;
import com.robinfood.paymentmethodsbc.model.Terminal;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.COMMA;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_METHOD_IDS_DEFAULT_LIST;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateCountry;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validatePaymentGateway;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validatePlatform;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateTerminal;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateTransaction;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateTransactionDetail;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentRequestDTONullSafety.validateOriginDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentRequestDTONullSafety.validatePaymentMethodDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentRequestDTONullSafety.validatePaymentReferencePaymentMethod;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentRequestDTONullSafety.validatePaymentReferencePaymentMethodList;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentRequestDTONullSafety.validateUserDTO;
import static java.util.Objects.requireNonNullElse;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public final class PaymentPipeDTONullSafe {

    private PaymentPipeDTONullSafe() {}

    public static PaymentPipeDTO validatePaymentPipeDTO(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(paymentPipeDTO).orElse(PaymentPipeDTO.builder().build());
    }

    public static PaymentRequestDTO validatePaymentRequestDTO(PaymentRequestDTO paymentRequestDTO) {
        return requireNonNullElse(paymentRequestDTO, PaymentRequestDTO.builder().build());
    }

    public static Long getPaymentMethodId(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(
            validatePaymentMethodDTO(
                validatePaymentRequestDTO(
                    validatePaymentPipeDTO(paymentPipeDTO).getTransactionRequestDTO()
                ).getPaymentMethod()
            ).getId()
        ).orElse(0L);
    }

    public static String getPaymentMethodIdList(PaymentPipeDTO transactionCreatePipeDTO) {
        List<PaymentReferencePaymentMethod> pmList =
            validatePaymentReferencePaymentMethodList(
            validatePaymentMethodDTO(
                validatePaymentRequestDTO(
                    validatePaymentPipeDTO(transactionCreatePipeDTO).getTransactionRequestDTO()
                ).getPaymentMethod()
            ).getReferencePaymentMethods()
        );
        if (pmList.isEmpty()) {
            return PAYMENT_METHOD_IDS_DEFAULT_LIST;
        }
        return pmList.stream()
            .map((PaymentReferencePaymentMethod pm) ->
                Optional.ofNullable(validatePaymentReferencePaymentMethod(pm).getId())
                    .orElse(0).toString())
            .collect(Collectors.joining(COMMA));
    }

    public static String getUuidFromPaymentPipeDTOTransaction(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(
            validateTransaction(
                validatePaymentPipeDTO(paymentPipeDTO).getTransaction()
            ).getUuid()
        ).orElse(EMPTY);
    }

    public static String getCountryName(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(
            validateCountry(validatePaymentPipeDTO(paymentPipeDTO).getCountry()).getName()
        ).orElse(EMPTY);
    }

    public static Long getCountryId(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(
            validateCountry(validatePaymentPipeDTO(paymentPipeDTO).getCountry()).getId()
        ).orElse(0L);
    }

    public static Long getStoreId(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(
            validateOriginDTO(
                validatePaymentRequestDTO(
                    validatePaymentPipeDTO(paymentPipeDTO).getTransactionRequestDTO()
                ).getOrigin()
            ).getStoreId()
        ).orElse(0L);
    }

    public static String getUserPhone(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(
            validateUserDTO(
                validatePaymentRequestDTO(
                    validatePaymentPipeDTO(paymentPipeDTO).getTransactionRequestDTO()
                ).getUser()
            ).getPhoneNumber()
        ).orElse(EMPTY);
    }

    public static Long getUserId(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(
            validateUserDTO(
                validatePaymentRequestDTO(
                    validatePaymentPipeDTO(paymentPipeDTO).getTransactionRequestDTO()
                ).getUser()
            ).getUserId()
        ).orElse(0L);
    }

    public static BigDecimal getTotal(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(
            validateTransaction(
                validatePaymentPipeDTO(paymentPipeDTO).getTransaction()
            ).getTotal()
        ).orElse(BigDecimal.ZERO);
    }

    public static Platform getPlatformFromPaymentPipeDTO(PaymentPipeDTO paymentPipeDTO) {
        return validatePlatform(
            validatePaymentGateway(
                validatePaymentPipeDTO(paymentPipeDTO).getPaymentGateway()
            ).getPlatform()
        );
    }

    public static Long getPlatformId(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(
            getPlatformFromPaymentPipeDTO(paymentPipeDTO).getId()
        ).orElse(0L);
    }

    public static String getPlatformName(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(
            getPlatformFromPaymentPipeDTO(paymentPipeDTO).getName()
        ).orElse(EMPTY);
    }

    public static PaymentGateway getPaymentGatewayFromPaymentPipeDTO(PaymentPipeDTO paymentPipeDTO) {
        return validatePaymentGateway(validatePaymentPipeDTO(paymentPipeDTO).getPaymentGateway());
    }

    public static Long getPaymentGatewayId(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(
            getPaymentGatewayFromPaymentPipeDTO(paymentPipeDTO).getId()
        ).orElse(0L);
    }

    public static Long getTransactionId(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(
            validateTransaction(
                validatePaymentPipeDTO(paymentPipeDTO).getTransaction()
            ).getId()
        ).orElse(0L);
    }

    public static Terminal getTerminalFromPaymentPipeDTO(PaymentPipeDTO paymentPipeDTO) {
        return validateTerminal(
            validateTransactionDetail(
                validatePaymentPipeDTO(paymentPipeDTO).getTransactionDetail()
            ).getTerminal()
        );
    }

    public static String getTerminalUuid(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(
            getTerminalFromPaymentPipeDTO(paymentPipeDTO).getUuid()
        ).orElse(EMPTY);
    }

    public static String getTerminalName(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(
            getTerminalFromPaymentPipeDTO(paymentPipeDTO).getName()
        ).orElse(EMPTY);
    }

    public static String getPaymentBci(PaymentPipeDTO paymentPipeDTO) {
        return Optional.of(
            getPaymentGatewayFromPaymentPipeDTO(paymentPipeDTO).getName()
        ).orElse(EMPTY);
    }

    public static String getDataphoneCode(PaymentPipeDTO paymentPipeDTO) {
        return Optional.ofNullable(
            validateTransactionDetail(
                validatePaymentPipeDTO(paymentPipeDTO).getTransactionDetail()
            ).getDataphoneCode()
        ).orElse(EMPTY);
    }
}
