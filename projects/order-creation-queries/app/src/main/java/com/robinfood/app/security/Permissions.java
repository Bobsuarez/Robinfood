package com.robinfood.app.security;

public final class Permissions {

    public static final String ORDER_HISTORY_STATUS_VIEW = "hasRole('OR_ORDER_HISTORY_STATUS_VIEW')";
    // Orders Permissions
    public static final String ORDER_REPORT_DAILY_SALE_SUMMARY_DETAIL = "hasRole('OR_ORDER_DAILY_SALES_SUMMARY')";
    public static final String ORDER_REPORT_TOTAL_DAILY_SALES_DETAIL
            = "hasRole('OR_ORDER_DAILY_SALES_PAYMENT_METHODS') || " +
            "hasRole('OR_ORDER_DAILY_SALES_PAYMENT_METHOD_GRAPHICS')";

    public static final String OR_OC_QUERIES_USER_ORDER_LIST = "hasRole('OR_OCQUERIES_USERORDER_LIST')";

    public static final String OR_VIEW_HISTORY_CANCELED = "hasRole('OR_VIEW_HISTORY_CANCELED')";

    public static final String ORDER_REPORT_SALES = "hasRole('OR_ORDER_REPORT_SALES')";

    private Permissions() {}
}
