package com.robinfood.paymentmethodsbc.utils.nullsafety;

import com.robinfood.paymentmethodsbc.dto.StatusResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO;

import java.util.Optional;

import static com.robinfood.paymentmethodsbc.utils.nullsafety.BCITransactionStatusResponseDTONullSafety.validateTransactionStatusResponse;
import static java.util.Objects.requireNonNullElse;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public final class StatusResponseDTONullSafety {

    private StatusResponseDTONullSafety() {}

    public static StatusResponseDTO validateStatusResponseDTO(StatusResponseDTO statusResponseDTO) {
        return requireNonNullElse(statusResponseDTO, StatusResponseDTO.builder().build());
    }

    public static BCITransactionStatusResponseDTO validateBCITransactionStatusResponseDTO(
        BCITransactionStatusResponseDTO bciTransactionStatusResponseDTO
    ) {
        return requireNonNullElse(bciTransactionStatusResponseDTO, BCITransactionStatusResponseDTO.builder().build());
    }

    public static String getPaymentGatewayStatusFromStatusResponseDTO(
        StatusResponseDTO response
    ) {
        return Optional.ofNullable(
            validateTransactionStatusResponse(
                validateBCITransactionStatusResponseDTO(
                    validateStatusResponseDTO(response).getStatusResult()
                ).getTransaction()
            ).getPaymentGatewayStatus()
        ).orElse(EMPTY);
    }

    public static String getPaymentGatewayLevelCategoryFromStatusResponseDTO(
        StatusResponseDTO response
    ) {
        return Optional.ofNullable(
            validateTransactionStatusResponse(
                validateBCITransactionStatusResponseDTO(
                    validateStatusResponseDTO(response).getStatusResult()
                ).getTransaction()
            ).getPaymentGatewayLevelCategory()
        ).orElse(EMPTY);
    }
}
