package com.robinfood.localserver.commons.enums.logs;

public enum OrderLogEnum {

    ORDER_RECEIVED_SUCCESSFULLY("001", "Order received successfully"),
    ORDER_CANCEL_RECEIVED_SUCCESSFULLY("002", "Order cancel received successfully");

    private String code;
    private String message;

    OrderLogEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage(){
        return this.code + " " + this.message;
    }
}
