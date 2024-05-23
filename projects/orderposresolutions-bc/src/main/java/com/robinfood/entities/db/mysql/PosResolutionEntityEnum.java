package com.robinfood.entities.db.mysql;

public enum PosResolutionEntityEnum {

    COLUMN_CURRENT("current"),
    COLUMN_DIC_STATUS_ID("dic_status_id"),
    COLUMN_END_DATE("end_date"),
    COLUMN_ID("id"),
    COLUMN_INVOICE_NUMBER_INITIAL("invoice_number_initial"),
    COLUMN_INVOICE_NUMBER_RESOLUTIONS("invoice_number_resolutions"),
    COLUMN_INVOICE_NUMBER_END("invoice_number_end"),
    COLUMN_INVOICE_TEXT("invoice_text"),
    COLUMN_INITIAL_DATE("initial_date"),
    COLUMN_NAME("name"),
    COLUMN_RESOLUTION_ID("resolution_id"),
    COLUMN_STORE_ID("store_id"),
    COLUMN_ORDER_NUMBER_INITIAL("order_number_initial"),
    COLUMN_PREFIX("prefix"),
    COLUMN_POS_ID("pos_id"),
    COLUMN_TYPE_DOCUMENT("type_document");

    public final String message;

    PosResolutionEntityEnum(String message) {
        this.message = message;
    }
}
