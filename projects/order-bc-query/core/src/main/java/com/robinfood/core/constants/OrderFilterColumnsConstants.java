package com.robinfood.core.constants;

public final class OrderFilterColumnsConstants {

    public static final String ORDERS_BRAND = "brandId";

    public static final String ORDERS_COMPANY = "companyId";

    public static final String ORDERS_INVOICE_NUMBER = "orderInvoiceNumber";

    public static final String ORDERS_LOCAL_DATE = "localDate";

    public static final String ORDERS_LOCAL_TIME = "localTime";

    public static final String ORDERS_ORDER_NUMBER = "orderNumber";

    public static final String ORDERS_ORIGIN = "originId";

    public static final String ORDERS_PAID = "paid";

    public static final String ORDERS_STATUS = "statusId";

    public static final String ORDERS_STORE = "storeId";

    public static final String ORDERS_UUID = "uuid";

    public static final String ORDER_INTEGRATION = "integrationId";

    public static final String ORDER_PAYMENT_METHODS = "paymentMethodId";

    public static final String ORDER_USER_DATA_FIRST_NAME = "firstName";

    public static final String ORDER_USER_DATA_MOBILE = "mobile";

    private OrderFilterColumnsConstants() {
        throw new IllegalStateException("Utility class");
    }
}
