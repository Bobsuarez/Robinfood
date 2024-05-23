package com.robinfood.core.constants;

public final class APIConstants {

    // API Headers
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
    public static final String ORDER_HISTORY = "/{storeId}/history";
    public static final String ORDER_DETAIL = "/detail";
    public static final String REPORT_V1 = "/api/v1/report";
    public static final String ORDER_DETAIL_PRINT = "/detail/print";
    public static final String ORDER_GET_DAILY_SALE_SUMMARY = "/stores/{storeId}/report/daily-sale-summary";
    public static final String ORDER_REPORT_TOTAL_DAILY_SALES = "/stores/{storeId}/report/total-daily-sales";
    public static final String ORDER_STORE = "/store/{storeId}/orders";
    public static final String ORDER_REPORT_CATEGORIES = "/{posId}/categories";
    public static final String ORDER_PAYMENT = "/orders-payment/{posId}/detail";

    public static final String PAYMENT_METHODS_V1 = "/api/v1";
    public static final String PAYMENT_METHODS = "/payment-methods";

    public static final String USERS_V1 = "/api/v1/users";
    public static final String USERS_ORDERS = "/{id}/orders";
    public static final String USERS_ACTIVE_ORDERS = "/{id}/orders/active";
    public static final String USERS_ORDER_DETAIL = "/{id}/orders/{orderUId}";

    public static final String ORDER_FILTER = "/filter";

    public static final String POS_RESOLUTION_SEQUENCE_BY_POS_ID = "/pos-resolutions/{posId}/sequence";
    public static final String POS_RESOLUTION_SEQUENCE_BY_STORE_ID = "/{storeId}/resolutions-sequence";

    public static  final String REPORTS_V1 ="/api/v1/report";
    public static final String SALES_STORE_PAYMENT_METHODS = "/sales/store/{storeId}/payment-methods";
    public static final String SALES_COMPANIES_ORDERS = "/sales/companies/orders";

    public static final String COMPANIES_V1 = "/api/v1";

    public static final String CONFIG_COMPANIES = "/companies";

    public static final String CHANNELS_V1 = "/api/v1";
    public static final String CHANNELS = "/channels";

    public static final String BRANDS_V1 = "/api/v1";
    public static final String BRANDS = "/brands";

    public static final String STORES_V1 = "/api/v1";
    public static final String STORES = "/stores";

    public static final String STATUS_V1 = "/api/v1";
    public static final String STATUS_CUSTOM_FILTER = "/status-custom-filter";

    public static final String SEARCH_CRITERIA_V1 = "/api/v1";
    public static final String SEARCH_CRITERIA = "/orders/custom-filters";

    public static final String STATUS_ORDER_HISTORY = "/{uuid}/status-history";

    public static final String ORDER_PAID_V1 = "/api/v1";
    public static final String ORDER_PAID = "/paid-orders";

    public static final String ORDER_PAID_DETAILS = "/paid-orders/{uuid}/detail";

    public static final String REPORT_SALES_BY_SEGMENT = "/sales";

    private APIConstants() {
        throw new IllegalStateException("Utility class");
    }
}
