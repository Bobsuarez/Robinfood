package org.example.enums;

public enum AppLogsEnum {

    PROGRAM_START("001", "Starting Lambda function {}"),

    INSERT_DATA("002", "Entered path parameters uuid: {} and {} {}"),

    RESPONSE_DATA_OK("005", "Data response OK {} , Response : {}"),

    CHANNEL_FOUND("006", "Channel destination found"),

    EVENT_CREATE("007", "Event created"),

    EVENT_HISTORY_CREATE("008", "Event history created"),

    START_INFORMATION_FLOWS("009", "Start with the following uuid: {} request: EventRequestDTO {}"),

    SEND_INFORMATION_FLOWS("010", "To send the following entity, FlowEventLogsEntity {}"),

    RESPONSE_OBTAINED_SAVING("011", "Response obtained by saving the information: FlowEventLogsEntity {}"),

    START_INFORMATION_EVENT_FLOWS("012", "Start with the following request: SubscriberEventHistoryLogsDTO {}"),

    SEND_INFORMATION_EVENT_FLOWS("013", "Response obtained by saving the information: " +
            "SubscriberEventHistoryLogsEntity {}"),

    SAVE_INFORMATION_EVENT("009", "Save the information of an event, uuid {} Request: {}"),

    SAVE_INFORMATION_EVENT_HISTORY("010", "Save the information of an subscriber event history, Uuid: {} Request: {}"),

    SUBSCRIBERS_FOUND("011", "Subscribers configuration found"),

    DATA_RESPONSE_REPOSITORY("012", "Data obtained from queries {}");


    private final String code;
    private final String message;

    AppLogsEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageWithCode() {
        return code + " " + message;
    }
}
