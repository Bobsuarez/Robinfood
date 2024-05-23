package com.robinfood.localorderbc.configs.constants;

public final class APIConstants {

    // API Headers
    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    public static final String AUTHORIZATION_HEADER_BEARER = "Bearer ";

    public static final String ORDER_V1 = "/api/v1/order";

    public static final String CHANGE_STATE = "/api/v1/state";

    public static final String TIMEZONE_UTC_DEFAULT = "Z";

    public static final String DEFAULT_LOCALE = "CO";

    public static final String COMMAND_EXECUTION = "/api/v1/command";

    public static final Integer DEFAULT_INTEGER_ONE = 1;

    public static final String CHAR_COMMA = ",";

    //Token
    public static final String STR_LOGIN_SCOPE = "login";
    public static final String STR_POSV2_ACCESS_TOKEN_LOGIN = "str_posv2_access_token_login";
    public static final String STR_POSV2_REFRESH_TOKEN = "str_posv2_refresh_token";
    public static final String STR_POSV2_ACCESS_TOKEN = "str_posv2_access_token";
    public static final int CODE_UNAUTHORIZED = 401;
    public static final String GET_ORDERS_BY_STATUS_ID = "/getOrdersByStatusId";
    public static final String UPDATE_ORDER ="/update-order";

    public static final Long ORIGIN_POS =10L;

    public static final String DEFAULT_STRING = "";

    public static final Long PRINT_INVOICE_TICKET_V1 = 2L;
    public static final Long REPRINT_INVOICE_TICKET_V1 = 4L;

    private APIConstants() {
        throw new IllegalStateException("Utility class");
    }

}
