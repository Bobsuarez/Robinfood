package com.robinfood.core.constants

enum class ReportInvoiceConstants(val value: String, val code: String) {

    ADVERTISING_SPACE("Ayúdanos a mejorar, escanea el cógido QR y calif " + "\n" + "ica tu orden " +
            "o ve al siguiente enlace " + "\n" + "https://encuestasatisfaccionag.robinfood.com/cal" + "\n" +
            "ificanos", ""),
    QR_INFO_CO("https://www.robinfood.com/", ""),
    INVOICE("%s - %s", ""),
    NOTE("Notas:" + "\n" + "%s", ""),
    PRICE("$%,.2f", ""),
    PRICE_CURRENCY("%s%s", ""),
    RESOLUTION("%s #%s" + "\n" + "Fecha %s - %s" + "\n" + "Desde %s hasta %s", ""),
    PRODUCT_ORDER_ADDICTION("(+) %s" + "\n", ""),
    PRODUCT_ORDER("%d X %s (%s)", "");

}