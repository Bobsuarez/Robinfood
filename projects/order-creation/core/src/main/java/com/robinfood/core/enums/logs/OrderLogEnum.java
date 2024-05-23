package com.robinfood.core.enums.logs;

public enum OrderLogEnum {

    ORDER_RECEIVED_SUCCESSFULLY_HTTP("0001", "Order received successfully http"),
    ORDER_RECEIVED_SUCCESSFULLY_QUEUE("0002", "Order received successfully queue"),
    ORDER_TRANSACTION_CREATED_HTTP("0003", "Transaction created http with data"),
    ORDER_TRANSACTION_CREATED_QUEUE("0004", "Transaction created queue data: {}"),
    ORDER_TRANSACTION_ACCEPTED("0005", "Order transaction accepted"),
    ORDER_SENDING_AMQ_CHANGE_STATUS_DISCARDED_ORDER("0006",
            "Sending message order status changed to discarded order {} to the ActiveMQ queue {} "),
    ORDER_SENDING_AMQ_CHANGE_STATUS_DISCARDED_ORDER_SUCCESSFULLY("0007",
            "Sending message order status changed to discarded order {} sent successfully to ActiveMQ queue {}"),
    ORDER_SENDING_AMQ_QUEUE_ORDER_TO_CREATE_PLATFORMS("0008",
            "Sending order created from platforms {} to the ActiveMQ queue {}."),
    ORDER_SUCCESSFULLY_AMQ_QUEUE_ORDER_TO_CREATE_PLATFORMS("0009",
            "Sending confirmation order created from platforms {} sent successfully to ActiveMQ queue {}."),
    ORDER_RECEIVING_SQS_QUEUE_PAYMENT_METHOD("0010", "Message received from SQS queue: {}, message: {}"),
    ORDER_SUCCESSFULLY_SQS_QUEUE_PAYMENT_METHOD("0011", "Message processed successfully in service. {}"),
    ORDER_SENDING_AMQ_CHANGE_STATUS_PAID_ORDER("0012",
            "Sending message order status changed to paid order {} to the ActiveMQ queue {} "),
    ORDER_SENDING_AMQ_CHANGE_STATUS_PAID_ORDER_SUCCESSFULLY("0013",
            "Sending message order status changed to paid order {} sent successfully to ActiveMQ queue {}"),
    ORDER_VALIDATE_PERFORM_COUPON_REQUEST("0014", "Validate PerformCouponRequestDTO: {}");


    private final String code;
    private final String message;

    OrderLogEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.code + " " + this.message;
    }
}
