package com.robinfood.orderorlocalserver.enums;

public enum MessageLogEnum {

    MESSAGE_RECEIVED_FROM_CREATED_ORDERS("001", "Message received from created orders queue in ActiveMQ."),

    MESSAGE_SENT_TO_BE_PRINTED_SUCESSFULLY("002", "Message was sent to orders to be printed queue in SQS sucessfully."),

    MESSAGE_FAILED_WHILE_SENDING_TO_BE_PRINTED("003", "Error, message could not be sent."),

    ORDER_TO_SEND("004", "This is the order to process and send to print."),

    ORDER_NOT_PAID("005", "The order received is not paid.");

    private String code;

    private String message;

    MessageLogEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage(){
        return this.code + " " + this.message;
    }
}
