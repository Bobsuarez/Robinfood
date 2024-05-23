package com.robinfood.core.enums.logs;

public enum OrderErrorLogEnum {

    ERROR_USER_IS_NOT_AN_EMPLOYEE("5000", "The user is not an employee, so consumption discount cannot be applied"),
    ERROR_PRICE_PRODUCTS_DIFFERENT_TOTAL_ORDER("5001", "The total price of the products %s " +
            "does not match the total of the order %s"),
    ERROR_METHOD_PAYMENT_NOT_CONTAIN_DETAIL("5002", "Payment method with id %s does not contain detail"),
    ERROR_PRICE_TRANSACTION_DIFFERENT_TOTAL_PAYMENTS_ORDER("5003", "The total price of the transaction %s " +
            "does not match the total of the payments %s"),
    ERROR_FAILED_VALIDATE_ORDER_TRANSACTION_EXIST_UUID("5004", "Failed to validate if transaction" +
            " uuid or orders uuids exits: [{}]"),
    ERROR_ORDER_TRANSACTION_CREATION_STEPS_EMPTY("5005", "Transaction creation steps are empty"),
    ERROR_ORDER_TRANSACTION_DELIVERY_IS_REQUIRED("5006", "For orders with delivery type %s delivery is required"),
    ERROR_ORDER_TRANSACTION_COULD_NOT_BE_CREATED("5007", "Transaction could not be created"),
    ERROR_ORDER_TRANSACTION_EXIST_UUID("5008", "{}"),
    ERROR_ORDER_TRANSACTION_SEND_ACTIVEMQ_ORDER_TO_CREATE("5009",
            "Error while sending OrderToCreateDTO {} to ActiveMQ queue {}."),
    ERROR_ORDER_TRANSACTION_SEND_ACTIVEMQ_CHANGE_STATUS("5010",
            "Error while sending change status message {} to ActiveMQ queue {}."),
    ERROR_ORDER_TRANSACTION_SEND_ACTIVEMQ_PAYMENT_METHOD("5011", "Error while sending payment method message to " +
            "ActiveMQ queue {}. {}"),
    ERROR_VALIDATING_GIFT_CARD_PAYMENT_METHODS_WITH_COUPONS("5013", "{}"),
    ERROR_VALIDATING_GIFT_CARD_PAYMENT_METHODS_WITH_PRODUCT_CATEGORY_GIFT_CARD("5014", "Error validating menu"),
    ERROR_VALIDATING_GIFT_CARD_PAYMENT_METHODS_BY_COMPANY_ID("5015",
            "There are no payment methods gift card related to the company with the following identifier {}"),
    ERROR_SERVICES_UNAVAILABLE_FE("5016", "Service Unavailable FE: {}"),
    ERROR_GENERIC_FE("5017", "Generic Error FE: {}"),
    ERROR_TRANSACTION_CREATION_EXCEPTION("{}", "Error {} in: {} , status: {}, cause : {}, data : {}");

    private final String code;
    private final String message;

    OrderErrorLogEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.code + " " + this.message;
    }

    public String getMessageWithOutCode() {
        return this.message;
    }

}
