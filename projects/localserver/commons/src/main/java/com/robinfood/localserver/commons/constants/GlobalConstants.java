package com.robinfood.localserver.commons.constants;

import org.springframework.stereotype.Component;

@Component
public final class GlobalConstants {
    public static final String PING_V1 = "/api/v1/ping";
    public static final String ORDER_V1 = "/api/v1/order";
    public static final String CHANGE_STATUS = "/status";
    public static final String PRINT_V1 = "/api/v1/print";
    public static final String KITCHEN_TICKET = "/kitchen-ticket";
    public static final String INVOICE = "/invoice";
    public static final String GET_ALL_ORDERS_BY_STATUS_ID = "/getAllOrdersByStatusId";
    public static final String DEFAULT_STRING_VALUE = "";
    public static final String SUCCESS_RESPONSE_FROM_SAT = "06000";
    public static final String SUCCESS_RESPONSE_CANCEL_FROM_SAT = "07000";
    public static final String UNKNOWN_ERROR_RESPONSE_FROM_SAT = "06099";
    public static final String UNKNOWN_ERROR_RESPONSE_CANCEL_FROM_SAT = "07099";
    public static final int DEFAULT_INTEGER_VALUE = 0;
    public static final Double DEFAULT_DOUBLE_VALUE = 0.0;
    public static final Long DEFAULT_LONG_VALUE = 1L;
    public static final Long POS_ORIGIN_ID = 10L;
    public static final String BR_LANGUAGE = "pt";
    public static final String BR_COUNTRY = "br";
    public static final String DEVELOPMENT_ENVIRONMENT = "development";
    public static final String PRODUCTION_ENVIRONMENT = "production";
    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    public static final String AUTHORIZATION_HEADER_BEARER = "Bearer ";

    //Printer name
    public static final String KITCHEN_PRINTER_NAME = "Cocina 1";

    //WebSocket
    public static final String MESSAGE_FUNCTION_DEFAULT = "getOfflineOrders";
    //Token
    public static final String STR_LOGIN_SCOPE = "login";
    public static final String STR_POSV2_ACCESS_TOKEN_LOGIN = "str_posv2_access_token_login";
    public static final String STR_POSV2_REFRESH_TOKEN = "str_posv2_refresh_token";
    public static final String STR_POSV2_ACCESS_TOKEN = "str_posv2_access_token";
    public static final int CODE_UNAUTHORIZED = 401;
    public static final int CODE_OK = 200;
    public static final int CODE_NOT_FOUND = 404;
    //
    public static final String EVENT_SUCCESS = "SuccessFully";
    public static final String EVENT_WITH_ERROR = "With Error";
    //http status codes
    public static final String SUCCESS_RESPONSE = "200";
    public static final String INTERNAL_SERVER_ERROR = "500";
    public static final String MESSAGE_SUCCESS_RESPONSE_PRINT_KITCHEN_TICKET = "operation_success";
    public static final String MESSAGE_INTERNAL_SERVER_ERROR_PRINT_KITCHEN_TICKET = "internal_server_error";

    //headers
    public static final String ACCEPT_LANGUAGE_HEADER_KEY = "Accept-Language";

    public static final String FORMAT_DATE_WITH_HOUR_AND_PM_AM = "yyyy-MM-dd HH:mm:ss";

    // Log Print
    public static final String REPRINT = "Reprint ";
    public static final String PRINT = "Print ";

    private GlobalConstants() {
    }
}
