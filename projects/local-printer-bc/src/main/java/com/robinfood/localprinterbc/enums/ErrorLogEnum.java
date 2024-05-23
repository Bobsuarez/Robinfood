package com.robinfood.localprinterbc.enums;

public enum ErrorLogEnum {

    MESSAGE_HOST_CONNECTION_REFUSED("300", "Connection refused for kitchen"),
    MESSAGE_PING_FAILED("301", "Ping refused"),
    MESSAGE_ORDER_EMPTY("302", "The kitchen order is obligatory"),
    MESSAGE_PRINTER_EMPTY("303", "The kitchen printer's ip is mandatory"),
    MESSAGE_TEMPLATE_EMPTY("304", "Template kitchen does not exist"),
    MESSAGE_INVOICE_HOST_CONNECTION_REFUSED("305", "Connection refused for invoice"),
    MESSAGE_ORDER_INVOICE_EMPTY("306", "The invoice order is obligatory"),
    MESSAGE_PRINTER_INVOICE_EMPTY("307", "The invoice printer's ip is mandatory"),
    MESSAGE_TEMPLATE_INVOICE_EMPTY("308", "Template invoice does not exist");

    private String code;
    private String message;

    ErrorLogEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.code + " " + this.message;
    }

}
