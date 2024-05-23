package com.robinfood.core.constants;

public final class APIConstants {

    // API Headers
    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    public static final String AUTHORIZATION_HEADER_BEARER = "Bearer ";

    // API Response Codes
    public static final int BAD_REQUEST = 400;
    public static final int CREATED_CODE = 201;
    public static final int NOT_FOUND_CODE = 404;
    public static final int PRECONDITION_FAILED_CODE = 412;
    public static final int SUCCESS_CODE = 200;
    public static final int UNAUTHORIZED_CODE = 401;

    // API Response Values
    public static final String BAD_REQUEST_MESSAGE = "Bad Request";
    public static final String DEFAULT_LOCALE = "CO";
    public static final String DEFAULT_MESSAGE = "OK";
    public static final String NOT_FOUND = "NOT FOUND";
    public static final String PRECONDITION_FAILED = "PRECONDITION FAILED";
    public static final String UNAUTHORIZED_MESSAGE = "Unauthorized";

    // Controller Endpoints
    public static final String ORDERS_V1 = "/api/v1/orders";

    public static final String DEDUCTIONS_V1 = "/api/v1/deductions";

    public static final String STATE_V1 = "/api/v1/states";
    public static final String ORDER_DETAIL_PRINT = "/detail/print";
    public static final String ORDER_HISTORY = "/history";
    public static final String ORDER_PICKUP_TIME = "/pickup-time";
    public static final String ORDER_GET_STATE = "/state/{idOrder}";
    public static final String ORDER_GET_CODE_STATE = "/state/code/{code}";
    public static final String ORDER_GET_DAILY_SALE_SUMMARY = "/stores/{storeId}/report/daily-sale-summary";
    public static final String ORDER_POST_CHANGE = "/state/change";
    public static final String ORDER_BY_TRANSACTION_ID = "/{id}/order-by-transaction-id";
    public static final String TEMP_TRANSACTION = "/{id}/temp-transaction";

    public static final String ORDER_FILTER = "/filter";

    public static final String ORDER_TOTAL_DAILY_SALES_BY_PARAMS =
            "/stores/{storeId}/report/total-daily-sales";

    public static final String USERS_V1 = "/api/v1/users";
    public static final String USERS_HAS_APPLIED_CONSUMPTION_TODAY = "/{id}/has-applied-consumption-today";
    public static final String USERS_ORDERS = "/{id}/orders";
    public static final String USERS_ACTIVE_ORDERS = "/{id}/orders/active";
    public static final String USERS_ORDER_DETAIL = "/{id}/orders/{orderUId}";

    public static final String ZONE_ID_UTC = "UTC";

    public static final Long TRANSACTION_FLOW_DEFAULT = 1L;
    public static final Long PLATFORM_ID_DEFAULT = 2L;

    public static final String EXIST_TRANSACTION_UUID_ORDER_UID = "/exits";

    private APIConstants() {
        throw new IllegalStateException("Utility class");
    }
}
