package com.robinfood.paymentmethodsbc.constants;

import java.util.List;

public final class TransactionConstants {
    public static final String ATTEMPT_TO_CHANGE_STATUS_AFTER_ACCEPT = "Attempt to change current status";
    public static final String ACCEPTED = "accepted";
    public static final String REJECTED = "rejected";
    public static final long STATUS_ACCEPTED = 1;
    public static final long STATUS_PENDING = 2;
    public static final long STATUS_REJECTED = 3;
    public static final long STATUS_CANCELED = 8;
    public static final long STATUS_ERROR = 9;
    public static final long STATUS_FAILED = 0;
    public static final String RETRY_STATUS_FLAG = "retryStatusFlag";
    public static final String RETRY_FLAG_ = "RETRY_FLAG_";
    public static final String STATUS_RESULT_ = "STATUS_RESULT_";
    public static final String LIST_STATUS_TO_NOTIFICATION = "listStatusToNotification";
    public static final String COMMA = ",";
    public static final String DEFAULT_LIST_STATUS_TO_NOTIFICATION = "1,3";
    public static final String TRANSACTION_VALUE_NOT_EQ_TO_TOTAL = "TRANSACTION_VALUE_NOT_EQ_TO_TOTAL";
    public static final String TRANSACTION_VALUE_NOT_EQ_TO_TOTAL_TEMPLATE =
        "The payment value %s is not equal to transaction total value %s";

    public static final List<Long> ATTEMPT_STATUS_ALLOWED = List.of(
        STATUS_ACCEPTED,
        STATUS_REJECTED
    );

    private TransactionConstants() {}
}
