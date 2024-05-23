package com.robinfood.paymentmethodsbc.constants;

public final class AppConstants {
    public static final double SECONDS_DIVISOR = 1000;
    public static final String SPRING_SECURITY_PASSWORD = "1234";
    public static final String CRITICAL_DAY_REGEX = "[\\p{InCombiningDiacriticalMarks}]";

    public static final String ORCHESTRATOR_ID_SETTINGS_NAME = "orchestratorId";
    public static final String BCI_COMPONENT_SETTINGS_NAME = "bciComponent";
    public static final String PROCESS_REFUND_SETTINGS_NAME = "bciProcessRefund";

    public static final String GW_TRANSACTION_REFERENCE = "transactionReference";
    public static final String GW_TRANSACTION_CODE = "transactionCode";
    public static final String GW_ERROR_CODE = "errorCode";
    public static final String GW_ERROR_DESCRIPTION = "errorDescription";
    public static final String GW_MESSAGE = "message";

    public static final String TRANSACTION_TYPE_PURCHASE = "Purchase";
    public static final String TRANSACTION_TYPE_REFUND = "Refund";
    public static final String TRANSACTION_TYPE_UNKNOWN = "Unknown";

    public static final String STATUS_KEY_REFERENCE = "reference";
    public static final String STATUS_KEY_CODE = "code";
    public static final String STATUS_KEY_UUID = "uuid";
    public static final String STATUS_KEY_ID = "id";
    public static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 5.1; rv:18.0) Gecko/20100101 Firefox/18.0";
    public static final int CREDIT_CARD_LAST_NUMBER_SIZE = 4;
    public static final int DEFAULT_CURRENCY_DECIMALS = 2;
    public static final String CURRENCY_DECIMALS_SETTINGS_NAME = "currencyDecimals";
    public static final long COUNTRY_ID_BR = 5L;

    private AppConstants() {}
}
