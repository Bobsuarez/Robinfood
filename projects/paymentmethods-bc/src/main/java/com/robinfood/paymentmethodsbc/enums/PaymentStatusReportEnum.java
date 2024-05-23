package com.robinfood.paymentmethodsbc.enums;

import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.STATUS_ACCEPTED;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.STATUS_CANCELED;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.STATUS_FAILED;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.STATUS_PENDING;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.STATUS_REJECTED;
import lombok.Getter;

/**
 * This enum allow to handle validation of current status transaccion vs incoming for determinate
 * if can report the log.
 */
@Getter
public enum PaymentStatusReportEnum {

    REPORT_ACCEPTED_ACCEPTED(STATUS_ACCEPTED, STATUS_ACCEPTED, false),
    REPORT_ACCEPTED_PENDING(STATUS_ACCEPTED, STATUS_PENDING, false),
    REPORT_ACCEPTED_REJECTED(STATUS_ACCEPTED, STATUS_REJECTED, false),
    REPORT_PENDING_ACCEPTED(STATUS_PENDING, STATUS_ACCEPTED, true),
    REPORT_PENDING_PENDING(STATUS_PENDING, STATUS_PENDING, false),
    REPORT_PENDING_REJECTED(STATUS_PENDING, STATUS_REJECTED, true),
    REPORT_REJECTED_ACCEPTED(STATUS_REJECTED, STATUS_ACCEPTED, false),
    REPORT_REJECTED_PENDING(STATUS_REJECTED, STATUS_PENDING, false),
    REPORT_REJECTED_REJECTED(STATUS_REJECTED, STATUS_REJECTED, false),
    REPORT_PENDING_CANCELED(STATUS_PENDING, STATUS_CANCELED, true),
    REPORT_REJECTED_CANCELED(STATUS_REJECTED, STATUS_CANCELED, false),
    REPORT_CANCELED_ACCEPTED(STATUS_CANCELED, STATUS_ACCEPTED, false),
    REPORT_ACCEPTED_CANCELED(STATUS_ACCEPTED, STATUS_CANCELED, false),
    REPORT_CANCELED_REJECTED(STATUS_CANCELED, STATUS_REJECTED, false),
    REPORT_PENDING_FAILED(STATUS_PENDING, STATUS_FAILED, true);

    private final Long currentStatusId;
    private final Long newStatusId;
    private boolean reported;

    PaymentStatusReportEnum(
        Long currentStatusId,
        Long newStatusId,
        boolean reported
    ) {
        this.currentStatusId = currentStatusId;
        this.newStatusId = newStatusId;
        this.reported = reported;
    }
}
