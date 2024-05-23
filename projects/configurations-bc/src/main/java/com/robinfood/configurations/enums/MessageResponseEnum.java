package com.robinfood.configurations.enums;

public enum MessageResponseEnum {

    UPDATE_MESSAGE("Updated resolution");

    private final String message;

    MessageResponseEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
