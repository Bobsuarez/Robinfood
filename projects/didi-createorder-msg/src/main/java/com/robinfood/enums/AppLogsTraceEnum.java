package com.robinfood.enums;

public enum AppLogsTraceEnum {

    PROGRAM_START("0001", "Execute routing integration handler %s"),

    SEND_MESSAGE_AMQ("0002", "Sending message id %s"),

    MESSAGE_SENT_SUCCESSFULLY("0003", "Message %s sent successfully to SQS queue."),
    RESPONSE_HANDLER("0004", "Data response Handler Ok %s"),
    MESSAGE_BODY_SQS("0005", "Message entry : %s , Attributes : %s"),
    GETTING_THE_SERVICE_TOKEN("0006", "Getting the service token from SSO"),
    SEND_MESSAGE_SUCCESSFULLY_OU("0007", "Message send to OU : %s"),
    FINAL_PROCESS_SUBSCRIBER("008", "Final process subscriber and messages Async");


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
