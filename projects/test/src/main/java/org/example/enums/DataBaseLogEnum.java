package org.example.enums;

public enum DataBaseLogEnum {

    EXECUTE_QUERY("2500", "Execute query to database : {} "),

    EXECUTE_PARAMETERS("2501", "Parameters to database : {} "),

    CONNECTION_CLOSED("2502", "the connection has been closed"),

    INSERTED_ROWS("2503", "Inserted rows: {}"),

    INSERTED_ID_RETURN("2504", "Get the id of the inserted object {}");

    private final String code;
    private final String message;

    DataBaseLogEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessageWithCode() {
        return code + " " + message;
    }
}
