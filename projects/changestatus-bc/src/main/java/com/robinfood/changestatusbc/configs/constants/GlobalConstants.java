package com.robinfood.changestatusbc.configs.constants;

public final class GlobalConstants {

    private GlobalConstants() {
        throw new IllegalStateException("Utility class");
    }

    // State Codes
    public static final String ORDER_APPROVED_PAYMENT = "ORDER_PAID";
    public static final String ORDER_IN_PROGRESS = "ORDER_IN_PROGESS";
    public static final String ORDER_DISCARDED = "ORDER_DISCARDED";
    public static final String STATUS_PAID = "ORDER_APPROVED_PAYMENT";
    public static final String STATUS_CANCEL = "ORDER_CANCEL";

    // Api Response Values
    public static final String ERROR_STATE = "State not possible to change";
    public static final String UNAUTHORIZED_REQUEST = "Unauthorized request.";

    // Default Values
    public static final String DEFAULT_STRING_VALUE = "";
    public static final int DEFAULT_BOOLEAN_FALSE_VALUE_AS_INT = 0;
    public static final int DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT = 1;
    public static final int DEFAULT_INTEGER_VALUE = 0;

    // Standard values
    public static final String FOUR_ZEROS_STRING = "0000";

    // JMS
    public static final String JMS_LISTENER_CONCURRENCY = "2-4";
}
