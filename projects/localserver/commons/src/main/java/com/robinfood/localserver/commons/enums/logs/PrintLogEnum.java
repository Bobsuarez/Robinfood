package com.robinfood.localserver.commons.enums.logs;

public enum PrintLogEnum {

    PRINTED_SUCCESSFULLY("050", "Printed Successfully"),
    PRINT_FAILED("075", "Printed Failed"),
    PRINT_INVOICE_TICKET_SUCCESSFULLY("051", "printed invoice ticket Successfully"),
    PRINT_KITCHEN_TICKET_SUCCESSFULLY("052", "printed kitchen ticket Successfully"),
    CHANGED_ORDER_STATUS_IN_PROGRESS_SUCCESSFULLY("053", "change order status to in progress successfully"),
    REPRINT_INVOICE_TICKET_SUCCESSFULLY("054", "reprinted invoice ticket Successfully"),
    REPRINT_KITCHEN_TICKET_SUCCESSFULLY("055", "reprinted kitchen ticket Successfully"),
    PRINT_INVOICE_TICKET_FAILED("076", "printed invoice ticket failed"),
    PRINT_KITCHEN_TICKET_FAILED("077", "printed kitchen ticket failed"),
    CHANGED_ORDER_STATUS_IN_PROGRESS_FAILED("078", "change order status failed"),
    REPRINT_KITCHEN_TICKET_FAILED("079", "reprinted kitchen ticket failed"),
    REPRINT_INVOICE_TICKET_FAILED("080", "reprint invoice ticket failed"),
    CHANGED_ORDER_STATUS_IN_PROGRESS_LOCALLY_SUCCESSFULLY("081", "change order status locally " +
            "to in progress successfully"),
    CHANGED_ORDER_STATUS_IN_PROGRESS_LOCALLY_FAILED("082", "change order status locally to in progress successfully"),
    CHANGED_ORDER_STATUS_LOCALLY_SUCCESSFULLY("083", "change order status locally " +
            "to status %s successfully "),
    CHANGED_ORDER_STATUS_LOCALLY_FAILED("084", "change order status locally successfully with statusId {}"),
    CHANGED_ORDER_STATUS_CLOUD_SUCCESSFULLY("085", "change order status cloud " +
            "to status %s successfully "),
    CHANGED_ORDER_STATUS_CLOUD_FAILED("086", "change order status in cloud successfully with statusId {}");

    private String code;
    private String message;

    PrintLogEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.code + " " + this.message;
    }

}
