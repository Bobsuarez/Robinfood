package com.robinfood.paymentmethodsbc.constants;


public final class LoggerPaymentConstants {
    public static final String PAYMENT_CREATION_EVENT = "PAYMENT-CREATION";
    public static final String PAYMENT_CREATION_SUB_EVENT = "PAYMENT-TRANSACTION-CREATION";
    public static final String PAYMENT_CREATION_ALERT_SUB_EVENT = "PAYMENT-CREATION-ALERT";
    public static final String PAYMENT_UPDATE_STATUS_EVENT = "PAYMENT-CREATION";
    public static final String PAYMENT_UPDATE_STATUS_SUB_EVENT = "PAYMENT-UPDATE-STATUS";
    public static final String PAYMENT_MESSAGE_EVENT = "PAYMENT-CREATION";
    public static final String PAYMENT_MESSAGE_SUB_EVENT = "PAYMENT-MESSAGE";
    public static final String EVENT_KEY = "eventLog";
    public static final String SUB_EVENT_KEY = "subEventLog";
    public static final String STATUS_KEY = "status";
    public static final String PAYMENT_METHOD_IDS_DEFAULT_LIST = "1";
    public static final String PAYMENT_IS_REQUESTED = "PAYMENT REQUESTED";
    public static final String PAYMENT_IS_PENDING = "PAYMENT PENDING FOR UPDATE STATUS";
    public static final String PAYMENT_STATUS_PENDING = "PENDING";
    public static final String PAYMENT_STATUS_REQUESTED = "REQUESTED";
    public static final String PAYMENT_STATUS_ERROR = "ERROR";
    public static final String BCI_PAYMENT_RESPONSE = "Response from BCI";
    public static final String DASH = "-";
    public static final String COMMA = ",";
    public static final String PAYMENT_STATUS_LOG = "LOG";
    public static final String CANCEL_ORDERS_PENDING = "Canceled orders with Pending status";
    public static final String BCI_VALIDATE_STATUS = "BCI status validation ";
    public static final String BCI_VALIDATE_STATUS_ERROR = "Error when process BCI status validation ";
    public static final String VALIDATE_STATUS = "Validate status";
    public static final String UPDATE_TRANSACTION_STATUS = "Update Transaction Status";
    public static final String UPDATE_TRANSACTION_STATUS_TO_CANCEL = "Update Transaction Status to CANCEL";
    public static final String ERROR_UPDATE_TRANSACTION_STATUS = "Update Transaction Status";
    public static final String ERROR_JSON_PROCESSING_EXCEPTION = "JsonErrorProcessingException";
    public static final String ERROR_ENTITY_NOT_FOUND_OR_BASE_EXCEPTION =
        "Error of BaseException or EntityNotFoundException";
    public static final String SEND_REPORT = "Send Report => {},";
    public static final String SEND_ALERT = "Send Alert => {},";

    public static final String PAYMENT_MESSAGE_SENT = "MESSAGE-CREATION-SENT";
    public static final String PAYMENT_MESSAGE_CHANGE_SENT = "MESSAGE-CHANGE-SENT";

    public static final String PAYMENT_MESSAGE_NOT_SENT = "MESSAGE-CREATION-NOT-SENT";
    public static final String PAYMENT_MESSAGE_CHANGE_NOT_SENT = "MESSAGE-CHANGE-NOT-SENT";

    public static final String PAYMENT_MESSAGE_SENT_DESCRIPTION = "MESSAGE SENT SUCCESSFULLY";
    public static final String PAYMENT_MESSAGE_NOT_SENT_DESCRIPTION = "MESSAGE FAILED: ";

    public static final String PAYMENT_MESSAGE_CREATION_RECEIVED = "MESSAGE-CREATION-RECEIVED";
    public static final String PAYMENT_MESSAGE_CREATION_RECEIVED_DESCRIPTION = "MESSAGE CREATION RECEIVED SUCCESSFULLY";

    public static final String PAYMENT_MESSAGE_UPDATE_RECEIVED = "MESSAGE-UPDATE-RECEIVED";
    public static final String PAYMENT_MESSAGE_UPDATE_RECEIVED_DESCRIPTION = "MESSAGE UPDATE RECEIVED SUCCESSFULLY";

    public static final String PAYMENT_MESSAGE_REFUND_RECEIVED = "MESSAGE-REFUND-RECEIVED";
    public static final String PAYMENT_MESSAGE_REFUND_RECEIVED_DESCRIPTION = "MESSAGE REFUND RECEIVED SUCCESSFULLY";

    public static final String PAYMENT_LIST_STATUS_TO_REPORT_KEY = "listStatusToReport";
    public static final String PAYMENT_LIST_STATUS_TO_REPORT_DEFAUL_VALUE = "1,2,3,8";


    private LoggerPaymentConstants() {}
}
