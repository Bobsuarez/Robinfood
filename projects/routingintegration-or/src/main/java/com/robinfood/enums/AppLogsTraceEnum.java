package com.robinfood.enums;

import lombok.Getter;

@Getter
public enum AppLogsTraceEnum {

    PROGRAM_START("0001", "Execute routing integration handler %s"),

    ROUTING_INSERT_DATA("0002", "Entered path parameters uuid: %s and %s %s "),

    RESPONSE_DATA_OK("0003", "Data invoke useCase %s , %s:%s, %s:%s"),

    RESPONSE_DATA_ROUTING_INTEGRATION_OK("0004", "Data response RoutingIntegration-bc OK %s"),

    CHANGE_ORDER_STATUS_START("0005", "Sending the message to the following http starts : %s"),

    RESPONSE_CLIENT_ASYNC_BODY_NULL("0006", "Message sent but the body of the request is null"),

    SEND_MESSAGE_CLIENT_ASYNC("0007", "Message is sent async verify in the receiver: %s"),

    RESPONSE_CLIENT_ASYNC("0008", "Message sent successfully with body : %s"),

    WAIT_FOR_HTTP_CALL("0009", "Waiting for the request for the lambda starts : %s millis"),

    RESPONSE_DATA_CHANGE_ORDER_STATUS_OK("0010", "Message was sent with status OK");

    private final String code;
    private final String message;

    AppLogsTraceEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessageWithCode() {
        return code + " " + message;
    }

}
