package com.robinfood.ordereports.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderDetailLogEnum {

    RECEIVING_REQUEST("0001", "Receiving request to get order detail by transactionUuid: {}"),
    GETTING_ORDER_DETAIL_REQUEST("0002", "getting a response from the order detail with transactionUuid: {}, orderId: {}"),
    GETTING_PAYMENT_METHOD_REQUEST("0003", "getting a response from the payment method with transactionUuid: {}, paymentMethodId: {}"),
    GETTING_SERVICES_REQUEST("0004", "getting a response from the services  with transactionUuid: {}, servicesId: {}"),
    ERROR_GET_ORDER_DETAIL_REQUEST("0005", "Exception occurred while fetching OrderDetail method data from API. transactionUuid: {}, exception: {}"),
    ERROR_GET_PAYMENT_METHOD_REQUEST("0006", "Exception occurred while fetching payment method data from API. transactionUuid: {}, exception: {}"),
    ERROR_GET_SERVICES_REQUEST("0007", "Exception occurred while fetching services method data from API. transactionUuid: {}, exception: {}"),
    DATA_RESPONSE_REQUEST("0008", "Response Order Detail: {}");

    private final String code;

    private final String message;

    public String getMessage() {
        return code +" "+ message;
    }
}
