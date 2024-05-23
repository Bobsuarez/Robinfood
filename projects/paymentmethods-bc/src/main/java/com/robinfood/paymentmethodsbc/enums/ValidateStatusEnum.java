package com.robinfood.paymentmethodsbc.enums;

import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.STATUS_ACCEPTED;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.STATUS_PENDING;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.STATUS_REJECTED;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.STATUS_CANCELED;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.STATUS_ERROR;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum ValidateStatusEnum {

    VALUE_ACCEPTED_ACCEPTED(STATUS_ACCEPTED, STATUS_ACCEPTED, false),
    VALUE_ACCEPTED_PENDING(STATUS_ACCEPTED, STATUS_PENDING, false),
    VALUE_ACCEPTED_REJECTED(STATUS_ACCEPTED, STATUS_REJECTED, false),
    VALUE_PENDING_ACCEPTED(STATUS_PENDING, STATUS_ACCEPTED, true),
    VALUE_PENDING_PENDING(STATUS_PENDING, STATUS_PENDING, true),
    VALUE_PENDING_REJECTED(STATUS_PENDING, STATUS_REJECTED, true),
    VALUE_REJECTED_ACCEPTED(STATUS_REJECTED, STATUS_ACCEPTED, false),
    VALUE_REJECTED_PENDING(STATUS_REJECTED, STATUS_PENDING, false),
    VALUE_REJECTED_REJECTED(STATUS_REJECTED, STATUS_REJECTED, false),
    VALUE_PENDING_CANCELED(STATUS_PENDING, STATUS_CANCELED, true),
    VALUE_REJECTED_CANCELED(STATUS_REJECTED, STATUS_CANCELED, false),
    VALUE_CANCELED_ACCEPTED(STATUS_CANCELED, STATUS_ACCEPTED, false),
    VALUE_ACCEPTED_CANCELED(STATUS_ACCEPTED, STATUS_CANCELED, false),
    VALUE_CANCELED_REJECTED(STATUS_CANCELED, STATUS_REJECTED, false),
    VALUE_PENDING_ERROR(STATUS_PENDING, STATUS_ERROR, true),
    VALUE_REJECTED_ERROR(STATUS_REJECTED, STATUS_ERROR, false),
    VALUE_ERROR_ACCEPTED(STATUS_ERROR, STATUS_ACCEPTED, false),
    VALUE_ACCEPTED_ERROR(STATUS_ACCEPTED, STATUS_ERROR, true),
    VALUE_ERROR_REJECTED(STATUS_ERROR, STATUS_REJECTED, false);

    private final long statusLog;
    private final long statusRedeban;
    private boolean result;

    ValidateStatusEnum(long statusLog, long statusRedeban, boolean result) {
        this.statusLog = statusLog;
        this.statusRedeban = statusRedeban;

        this.result = result;
    }

    public static boolean isGetStatusResult(long statusLog, long statusRedeban) {
        return Arrays.stream(ValidateStatusEnum.values())
            .filter(e -> e.getStatusLog() == statusLog && e.getStatusRedeban() == statusRedeban)
            .findFirst()
            .map(ValidateStatusEnum::isResult)
            .orElse(false);
    }
}
