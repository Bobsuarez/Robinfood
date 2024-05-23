package com.robinfood.paymentmethodsbc.exceptions;

import com.robinfood.paymentmethodsbc.enums.StepType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PaymentStepException extends Exception {
    private StepType step;

    public PaymentStepException(String message) {
        super(message);
    }

    public PaymentStepException(Throwable cause, String message) {
        super(message, cause);
    }

    public PaymentStepException(StepType step, String message) {
        super(message);
        this.step = step;
    }

    public PaymentStepException(
        Throwable cause,
        StepType step,
        String message
    ) {
        super(message, cause);
        this.step = step;
    }
}
