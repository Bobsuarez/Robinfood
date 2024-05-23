package com.robinfood.localserver.commons.enums.logs;

public enum DigitalSignLogEnum {

    ERROR_GENERATING_DIGITAL_SIGNATURE("101", "Error generating digital signature"),
    DIGITAL_SIGN_UNAVAILABLE("100", "Digital Sign Unavailable");

    private String code;
    private String message;

    DigitalSignLogEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage(){
        return this.code + " " + this.message;
    }

}
