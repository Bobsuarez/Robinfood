package com.robinfood.enums;

import lombok.Getter;

@Getter
public enum AppLogsTraceEnum {

    PROGRAM_START("0001", "Execute simba connector handler %s"),

    ENTERED_DATA("0002", "Entered path parameters uuid: %s name_class: %s and order: %s"),

    INIT_CONNECTOR_SIMBA("0003", "The process of sending to SISMBA begins"),

    DATA_TO_SEND_TO_SIMBA("0004", "Data to be sent to simba %s"),

    RESPONSE_CONNECTOR_SIMBA("0005", "this is the SISMBA connector response: %s"),

    SEND_DATA_SIMBA("0006", "Message is sent to simba");

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
