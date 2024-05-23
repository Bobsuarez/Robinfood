package com.robinfood.core.constants;

import java.math.BigDecimal;

public final class GlobalConstants {

    // Size
    public static final int SIZE_ONE = 1;

    // Coupons
    public static final long DEFAULT_COUPON_ORDER_ID = 1L;
    public static final long VALIDATION_PAYMENT_METHOD = 1L;

    // Default Values
    public static final Long DEFAULT_LONG_VALUE = 0L;
    public static final int DEFAULT_INTEGER_VALUE = 0;
    public static final Integer DEFAULT_INTEGER_CLASS_VALUE = 0;
    public static final Integer NUMBER_DECIMAL_PLACES = 4;
    public static final Integer NUMBER_DECIMAL_PLACE_MAX = 10;
    public static final int NUMERIC_DECIMAL_DIVISION = 5;
    public static final BigDecimal DEFAULT_BIG_DECIMAL_VALUE = BigDecimal.ZERO;
    public static final Double DEFAULT_DOUBLE_VALUE = 0.0;
    public static final String DEFAULT_STRING_VALUE = "";
    public static final String TOTAL_TAXES = "total_taxes";
    public static final String TOTAL_DISCOUNT = "total_discount";
    public static final String TOTAL_ORDER = "total_order";

    public static final BigDecimal SERVICES_MEX_VALUE = BigDecimal.valueOf(1.16);

    public static final BigDecimal SERVICES_COL_VALUE = BigDecimal.valueOf(1.08);

    public static final BigDecimal PERCENTAGE_SERVICES_MEX_VALUE = BigDecimal.valueOf(16);

    public static final BigDecimal PERCENTAGE_SERVICES_COL_VALUE = BigDecimal.valueOf(8);

    // Portion Types
    public static final int ADDED_PORTION = 0;
    public static final int INCLUDED_PORTION = 1;
    public static final int REMOVED_PORTION = 2;

    // Redeem FoodCoins
    public static final int FOOD_COINS_OPERATION_TYPE_REDEEM = 1;
    public static final int FOOD_COINS_OPERATION_TYPE_REDEEM_ROLLBACK = 3;
    public static final long FOOD_COINS_ENTITY_ORDER = 1;

    // Taxes
    public static final long TAX_PERCENTAGE_ID = 1L;
    public static final int TAX_TYPE = 1;
    public static final int TAX_POSITION_MULTIPLIER = 1;
    public static final int TAX_ROUNDING = 4;

    // WorkShifts
    public static final long WORKSHIFT_ID = 1L;

    // Payment Method Business Capability
    public static final int PLATFORM_TYPE_ID = 1;
    public static final Long PAYMENT_METHOD_ID_PAY_U = 1L;
    public static final long ENTITY_ID = 1;

    // Brand order
    public static final Long MULTI_BRAND_ID = 9L;
    public static final String MULTI_BRAND_NAME = "Robinfood";

    // Timezone default by device
    public static final String TIMEZONE_BY_DEVICE_DEFAULT = "America/Bogota";

    // Taxes
    public static final String TAXES_BRASIL = "5";
    public static final String TAXES_COLOMBIA = "1";
    public static final String TAXES_COLOMBIABOWLO = "3";
    public static final String TAXES_MEXICOBOWLO = "4";
    public static final String TAXES_MEXICO = "2";

    // Discount ids
    public static final Long PRODUCT_DISCOUNT_ID = 1782L;
    public static final Long DISCOUNT_PRODUCT = 3L;
    public static final Long DISCOUNT_ORDER = 2L;

    public static final Long DISCOUNT_SERVICE = 4L;

    // Coupon type
    public static final Long COUPON_TYPE_DEFAULT = 1L;

    // Deduction
    public static final Long DEDUCTION_DEFAULT = 1L;

    //Criteria
    public static final String CRITERIA_LOCATION = "location_id";
    public static final String CRITERIA_FLOW = "flow_id";
    public static final BigDecimal INITIAL_MARGIN_ERROR = BigDecimal.valueOf(0.0001);
    public static final BigDecimal END_MARGIN_ERROR = BigDecimal.valueOf(0.0005);

    // Pos type
    public static final Long POS_TYPE_OFFLINE = 1L;
    public static final Long POS_TYPE_ONLINE = 2L;

    // APM
    public static final int APM_MAP_PROPERTIES = 6;

    // Status order
    public static final String STATUS_CODE_ORDER_CREATED = "ORDER_CREATED";
    public static final String STATUS_CODE_ORDER_PAID = "ORDER_PAID";

    // Payment methods refund
    public static final String REASON_REFUND_MESSAGE = "Refund generated from OU";

    // Companies
    public static final long COMPANY_COL = 1;
    public static final long COMPANY_BRA = 5;

    //TTL
    public static final long EXPIRED_TIME_REGISTER = 86400;

    //symbols
    //symbols
    public static final String RIGHT_ARROW = "--->";
    public static final String LEFT_ARROW = "<---";
    public static final String NO_APPLIED = "N/A";
    public static final String ENTER = "\n";

    public static final String RESPONSE_PAYMENT_METHOD = "Payment Method Response Receive Successfully";

    public static final String NAME_SQS_PAYMENT_METHOD = "Rf.All.Order.OrdersPaymentMethods.Queue";
    public static final String AUDIENCE_INTERNAL_JWT = "internal";

    //Logs
    public static final String BRAND_AUDIT = "brand";
    public static final String COMPANY_AUDIT = "company";
    public static final String ORIGIN_AUDIT = "origin";
    public static final String PAID_AUDIT = "paid";
    public static final String STORE_AUDIT = "store";
    public static final String UUID_AUDIT = "uuid";

    private GlobalConstants() {
        throw new IllegalStateException("Utility class");
    }
}
