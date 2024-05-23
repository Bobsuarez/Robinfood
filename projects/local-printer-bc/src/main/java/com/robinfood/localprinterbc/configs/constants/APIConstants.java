package com.robinfood.localprinterbc.configs.constants;

public final class APIConstants {

    // API Headers
    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    public static final String AUTHORIZATION_HEADER_BEARER = "Bearer ";

    public static final String TEMPLATE = "/api/v1/template-configurations";
    public static final String FIND_TEMPLATE_BY_STORE_ID = "/v1/printing-templates/templates?storeId";

    public static final String PRINT = "/api/v1/print";
    public static final String PRINT_ORDERS = "/print-orders";
    public static final String PRINT_INVOICES = "/print-invoices";
    public static final String TIMEZONE_UTC_DEFAULT = "Z";
    public static final int DEFAULT_INTEGER_VALUE = 0;

    public static final String INVOICE = "/api/v1/invoice";
    public static final String DEFAULT_LOCALE = "CO";

    public static final String COMMAND_EXECUTION = "/api/v1/command";

    public static final String CHAR_COMMA = ",";

    public static final int CODE_UNAUTHORIZED = 401;

    public static final int CODE_OK = 200;
    public static final String MESSAGE_SUCCESS = "success";
    public static final String RESULT_CODE = "ok";

    public static final String CONSUMER = "CONSUMIDOR";
    public static final String UNIDENTIFIED_CONSUMER = "CONSUMIDOR NAO IDENTIFICADO";

    private APIConstants() {
        throw new IllegalStateException("Utility class");
    }

}
