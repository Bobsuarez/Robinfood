package com.robinfood.enums;

public enum AppLogsTraceEnum {
    EXECUTE_SUBSCRIBER_CHANGE_STATUS_HANDLER("0001", "Execute subscriber change status handler %s"),
    SUBSCRIBER_HANDLER_CHANGE_STATUS_MAPPER("0002", "Subscriber handler change status mapper %s"),
    GETTING_THE_SERVICE_TOKEN("0003", "Getting the service token from SSO"),
    INITIAL_PROCESS_GET_SUBSCRIBER("0004", "Init process get subscriber by channel id %s and flow %s"),
    FINAL_PROCESS_GET_SUBSCRIBER("0005", "Final process get subscriber data %s"),
    INITIAL_SAVE_EVENT("0006", "The process of saving the event begins"),
    FINAL_SAVE_EVENT("0007", "Event saving process completed id: %s, eventID: %s "),
    INITIAL_PROCESS_SUBSCRIBER("0008", "The process begins for the subscriber: %s"),
    INITIAL_PROCESS_EVENT_HISTORY("0009", "The process begins for the event history: %s"),
    FINAL_PROCESS_SUBSCRIBER("0010", "Final process subscriber and messages Async");
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
