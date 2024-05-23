package com.robinfood.localprinterbc.configs.constants;

public final class GlobalConstants {

    // API Headers
    public static final String TOGO = "toGo";
    public static final String SUGGESTED_PRODUCTS = "suggestedProducts";
    public static final String DRINKS_AND_DESSERTS = "drinksAndDesserts";
    public static final String REMOVE_DRINKS_AND_DESSERTS_TO_SUGGESTED_PRODUCTS = "removeDrinksAndDessertsToSuggestedProducts";
    public static final String GROUP_ADDED_PORTIONS = "groupAddedPortions";
    public static final String GROUP_REMOVED_PORTIONS = "groupRemovedPortions";
    public static final String GROUP_CHANGED_PORTIONS = "groupChangedPortions";
    public static final String GROUP_INCLUDED_PORTIONS = "groupIncludedPortions";
    public static final String GROUP_INCLUDED_PORTIONS_BASE_CUSTOM = "groupIncludedPortionsBaseCustom";
    public static final String ADD_PROPERTY = "addProperty";

    public static final String CHANGED_PORTION = "changedPortion";
    public static final String ADDITION = "addition";
    public static final String ID = "id";

    public static final String IS_ADDITION = "isAddition";
    public static final String SYMBOL = "symbol";
    public static final String WHEREIN = "whereIn";
    public static final String PROP_IN = "propIn";
    public static final String CHILD_PATH_TO = "childPathTo";
    public static final String TITLE = "title";

    public static final String ORDER_BRAND_ID = "orderBrandId";
    public static final String TEMPLATE_TYPE_COMMAND = "ticket_kitchen";
    public static final String TEMPLATE_TYPE_INVOICE = "ticket_invoice";
    public static final String KEY_LOGO = "logo";
    public static final String KEY_LOGO_BOWLO = "logoBowlo";
    public static final String KEY_SUB_TOTAL = "subtotal";
    public static final String KEY_DISCOUNT = "discount";
    public static final String KEY_TAX = "tax";
    public static final String KEY_TOTAL = "total";
    public static final String KEY_DATETIME = "datetime";
    public static final String KEY_VALUE_QR_CODE = "valueqrcode";
    public static final String KEY_VALUE_PAYMENT_METHODS = "valuePaymentMethods";
    public static final String KEY_TOTAL_FINISH = "totalFinish";

    public static final String KEY_SHOW_QR = "showQR";

    public static final String KEY_SOWH_LOGO_BOWLO = "showLogoBowlo";
    public static final String KEY_NAME_PAYMENT_METHODS = "namePaymentMethods";
    public static final String LABEL_TOTAL = "Total:";
    public static final String LABEL_SUB_TOTAL = "Subtotal:";
    public static final String LABEL_SUB_TOTAL_BR = "Produtos de valor:";
    public static final String LABEL_DISCOUNT = "Descuento:";
    public static final String LABEL_DISCOUNT_BR = "Desconto:";
    public static final String LABEL_TAX = "Impuesto:";
    public static final int TWO = 2;
    public static final String VALUE_QR_CODE = "https://wa.me/573103100000";
    public static final String KEY_HAS_CO2 = "hasCO2";
    public static final String KEY_ORDER_TYPE = "orderType";
    public static final String KEY_VALUE_CO2 = "valueCO2";
    public static final String VALUE_COMPENSATION_CO2 = "Compensación por Co2";

    public static final String VALUE_ORDER_INTERNAL = "Orden Interna: ";
    public static final String VALUE_ORDER_INTERNAL_BR = "Ordem Interna: ";
    public static final String VALUE_ORDER = "Orden: ";
    public static final Double VALUE_PERCENTAGE = 1.08;
    public static final Long BRAZIL = 3L;

    public static final String FILE_PATH_QR = "src/main/resources/qrcode/";
    public static final String FILE_PATH_BAR_CODE = "src/main/resources/barcode/";

    //Logs hit printer
    public static final String MESSAGE_HOST_OFFLINE = " Error when trying to connect to the host";
    public static final String MESSAGE_HOST_TIMEOUT = " Timeout trying to connect to host";
    public static final String MESSAGE_HOST_INTERRUPTED_OR_EXECUTION_FAILED = " Interrupted process or failed execution on the host";
    public static final String MESSAGE_HOST_UNKNOWN_EXCEPTION = " Unknown exception to host";


    public static final String FORMAT_DATE_WITH_HOUR_AND_PM_AM = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE_WITH_LOCAL_TIME = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String KEY_QR_FOUND = "keyQrFound";

    public static final String KEY_QR_COL = "keyQrCOL";

    public static final String KEY_FULL_NAME_FE = "fullNameFE";

    public static final String KEY_DOCUMENT_NUMBER_FE = "documentNumberFE";

    public static final String KEY_PHONE_FE = "phoneFE";

    public static final String KEY_EMAIL_FE = "emailFE";

    public static final String KEY_CUDE_FE = "cudeFE";

    private GlobalConstants() {
        throw new IllegalStateException("Utility class");
    }

}
