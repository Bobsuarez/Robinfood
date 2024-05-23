package org.example.enums;

public enum ErrorLogsEnum {

    ERROR_CONNECTION("501", "Failed to connect to the database"),

    ERROR_APP_EXCEPTION("502", "{} {} {}"),

    ERROR_DATA_NOT_FOUND("503", "Data not found"),

    ERROR_INSERT_DATA("504", "Error inserting the data check values"),

    ERROR_INSERT_NOT_COMPLETED("505", "The insert operation could not be completed");

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
