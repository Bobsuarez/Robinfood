package com.robinfood.enums;

public enum AppLogsTraceEnum {

    PROGRAM_START("001", "Execute routing integration handler %s"),

    SEND_MESSAGE_AMQ("002", "Sending change status message %s to the ActiveMQ queue %s."),

    MESSAGE_SENT_SUCCESSFULLY("003", "Message %s sent successfully to ActiveMQ queue %s."),

    RESPONSE_HANDLER("004", "Data response Handler Ok %s");

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
