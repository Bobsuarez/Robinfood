package com.robinfood.enums;

import static com.robinfood.constants.GeneralConstants.DEFAULT_SPACE_STRING_EMPTY;

public enum ErrorLogsEnum {

    ERROR_CONNECTION("5001", "Failed to connect to the database"),

    ERROR_APP_EXCEPTION("5002", "{} {} {}"),

    ERROR_DATA_NOT_FOUND("5003", "Data not found"),

    ERROR_INSERT_DATA("5004", "Error inserting the data check values"),

    ERROR_INSERT_NOT_COMPLETED("5005", "The insert operation could not be completed"),

    ERROR_CLOSED_CONNECTION("5005", "Failed to closed connection database");

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
        return code + DEFAULT_SPACE_STRING_EMPTY + message;
    }
}
