package com.robinfood.enums;

public enum ErrorLogsEnum {

    ERROR_APP_EXCEPTION("0502", "%s, %s, %s");

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
