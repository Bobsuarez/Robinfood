package com.robinfood.enums;

public enum AppLogsTraceEnum {

    PROGRAM_START("001", "Execute routing integration handler %s"),

    ROUTING_INTEGRATION_START_POS("002", "Utility that allows us to connect and queue the order status change" +
            " message in channel POS"),

    ROUTING_INTEGRATION_START_ECS("002", "Utility that allows us to connect and queue the order status change" +
            " message in ECS"),

    SEND_MESSAGE_AMQ("003", "Sending change status message %s to the ActiveMQ queue %s."),

    MESSAGE_SENT_SUCCESSFULLY("004", "Message %s sent successfully to ActiveMQ queue %s."),

    RESPONSE_HANDLER("005", "Data response Handler Ok %s");

    private final String code;
    private final String message;

    AppLogsTraceEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return code + " " + message;
    }
}
