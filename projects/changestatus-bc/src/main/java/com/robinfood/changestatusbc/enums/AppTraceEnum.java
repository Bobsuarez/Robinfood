package com.robinfood.changestatusbc.enums;

import org.apache.commons.lang3.StringUtils;

public enum AppTraceEnum {

    RECEIVING_MESSAGE_CHANGE_STATUS_HTTPS("001", "HTTPS - Receiving message for order status change with data: {}"),
    SEARCH_FINAL_PRODUCTS("214", "Entered the search for final portion products"),
    NO_SEARCH_FOR_FINAL_PRODUCTS("215", "No search for final portion products"),
    STARTING_PROCESS_VALIDATE("209", "Starting process to validate update order status with status code: {}"),
    STATES_LIST_FACTORY("210", "Total of states : {}"),
    SENDING_CHANGE_STATUS_MESSAGE("205", "Sending change status message {} to the ActiveMQ topic {}."),
    ERROR_JMS_EXCEPTION("5000", "Error while sending message {} to ActiveMQ queue {}."),
    WRITE_CHANGE_STATUS_QUEUE("204", "WriteChangeStatusQueueUseCase invocation with nextState {}"),
    ORDER_DISCARDED_STATUS("207", "The order is discarded by status code : {}"),
    GET_STATES_ALL("202", "Getting all states :  {}"),
    ORDER_PAID_SELF_MANAGEMENT("208", "Is send status ORDER_PAID from self-management {}"),
    IS_MACHINE_STATES("211", "the value for the machine state is : {}"),
    ORDER_STATUS_CHANGE_RESPONSE("212", "Order status change response: {} to order id: [{}] from order-bc"),
    IS_STATUS_PAID_AND_ORIGIN_ID("203", "Send status ORDER_PAID when the origin id from self-management originId : {}" +
            " And isPaid : {}"),
    SUCCESSFULLY_CHANGE_STATUS_MESSAGE("206", "Message {} sent successfully to ActiveMQ queue {}."),
    VALUE_ORDER_ID_AND_STATUS("213", "The value of orderId : {} status : {} ");

    private final String code;
    private final String message;

    AppTraceEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageWithCode() {
        return code + StringUtils.SPACE + message;
    }
}
