package com.robinfood.changestatusor.enums;

import lombok.Getter;

public enum TransactionCreationErrors {

    // Transaction data errors
    TRANSACTION_PRICES_ERROR(1001),
    INCONSISTENT_TRANSACTION_ERROR(1002),
    TRANSACTION_NOT_PAID(1003),
    TRANSACTION_HAS_OTHER_DISCOUNTS(1004),
    TOTAL_PAID_PAYMENTS_NOT_MATCH(1005),
    USER_IS_NOT_EMPLOYEE(1006),
    CONSUMPTION_DISCOUNT_ALREADY_APPLIED(1007),
    PAYMENT_METHOD_WITH_NO_DETAIL(1008),
    DELIVERY_TYPE_NOT_FOUND(1009),
    ORDERS_REQUIRED(1010),
    TRANSACTION_ORDERS_UUID_ALL_REQUIRED(1011),
    TRANSACTION_ORDERS_UUID_EXITS(1012),
    TEMPORARY_TRANSACTION_NOT_FOUND(1013),
    UNKNOWN_ERROR(1099),

    // Menu BC errors
    MENU_BC_ERROR(2000),
    INVALID_MENU(2001),
    INVALID_MENU_DISCOUNT(2003),
    GET_BRANDS_ERROR(2002),
    NOT_FOUND_MENU_HALL_PRODUCT_ID(2004),
    MENU_HALL_PRODUCT_ID(2005),

    // Coupons API errors
    COUPON_SYSTEM_API_ERROR(3000),

    // Cubano errors
    CUBANO_API_ERROR(4000),
    PRODUCT_FINANCE_CATEGORY_NOT_FOUND_ERROR(4001),
    SYNC_ERROR(4002),

    // Discount BC errors
    INVALID_PRODUCT_DISCOUNT(5001),

    INVALID_ORDER_SERVICE(5002),

    // Loyalty BC errors
    FOODCOINS_VALIDATION_ERROR(6001),
    FOODCOINS_REDEEM_OR_ROLLBACK_ERROR(6001),

    // Order BC errors
    ORDER_BC_ERROR(7000),

    // Taxes BC
    TAXES_BC_ERROR(8000),

    // Pickup time - store configurations bc
    STORE_CONFIGURATION_PICKUP_TIME_BC_ERROR(9000),

    // SSO errors
    SSO_ERROR(10000),

    // CO2 BC
    CO2_BC_ERROR(11000),
    CO2_VALIDATION_ERROR(11001),

    // Configurations BC errors
    CONFIGURATIONS_BC_ERROR(11000),
    GET_POS_ID_ERROR(11001),

    // Users BC
    USER_DETAILS_ERROR(12000),

    // Order BC Sync Data errors
    GET_CATEGORY_ORDER_BC_SYNC_DATA_ERROR(13000),

    HTTP_MESSAGE_NOT_READABLE(14000),

    METHOD_ARGUMENT_NOT_VALID(15000);

    @Getter
    private final int errorCode;

    TransactionCreationErrors(int errorCode) {
        this.errorCode = errorCode;
    }
}
