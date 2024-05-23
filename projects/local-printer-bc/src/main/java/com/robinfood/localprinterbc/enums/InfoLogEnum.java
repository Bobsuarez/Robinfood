package com.robinfood.localprinterbc.enums;

public enum InfoLogEnum {

    MESSAGE_RECEIVED_FOR_PRINT("400", "Message to print kitchen successfully received"),
    MESSAGE_PRINTING_COMPLETED("401", "Message kitchen printing process completed successfully"),
    MESSAGE_ORDER_TO_PRINT("402", "Order to print kitchen"),
    MESSAGE_RECEIVED_FOR_PRINT_INVOICE("403", "Message to print invoice successfully received"),
    MESSAGE_PRINTING_INVOICE_COMPLETED("404", "Message invoice printing process completed successfully");

    private String code;
    private String message;

    InfoLogEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.code + " " + this.message;
    }

}
