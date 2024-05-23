package com.robinfood.app.enums.paymentmethod;

import com.robinfood.core.constants.StatusCodeConstants;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Getter
public enum PaymentMethodStatusEnum {
    ACCEPTED(1L,StatusCodeConstants.ORDER_PAID),
    PENDING(2L,null),
    REJECTED(3L,StatusCodeConstants.ORDER_CANCELED),
    REFUNDED(4L,null);

    private final Long paymentMethodStatus;
    private final StatusCodeConstants code;

    PaymentMethodStatusEnum(@NotNull Long paymentMethodStatus, StatusCodeConstants code) {
        this.paymentMethodStatus = paymentMethodStatus;
        this.code = code;
    }

    @Nullable
    public static PaymentMethodStatusEnum paymentMethodStatusEnum(Long paymentMethodStatus) {
        return Arrays.stream(values())
            .filter(item -> item.paymentMethodStatus.equals(paymentMethodStatus))
            .findFirst()
            .orElseThrow(() -> new TransactionCreationException(
                HttpStatus.BAD_REQUEST,
                "this state does not exist",
                TransactionCreationErrors.INCONSISTENT_TRANSACTION_ERROR
            ));
    }

}
