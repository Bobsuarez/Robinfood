package com.robinfood.enums;

public enum ErrorLogsEnum {

    ERROR_APP_EXCEPTION("502", "{} {} {}"),

    ERROR_DATA_NOT_FOUND("503", "Data not found");

    private final String code;
    private final String message;

    ErrorLogsEnum(String code, String message) {
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
