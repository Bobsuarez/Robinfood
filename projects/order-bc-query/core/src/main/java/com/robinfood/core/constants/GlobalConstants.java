package com.robinfood.core.constants;

import java.time.format.DateTimeFormatter;

public final class GlobalConstants {

    // Api Response Values
    public static final int DEFAULT_CODE = 200;
    public static final String DEFAULT_LOCALE = "CO";
    public static final String DEFAULT_MESSAGE = "OK";
    public static final String ERROR = "Error.";
    public static final String ERROR_STATE = "State not possible to change";
    public static final String FORBIDDEN_REQUEST = "Forbidden request.";
    public static final String UNAUTHORIZED_REQUEST = "Unauthorized request.";

    // Default Values
    public static final boolean DEFAULT_BOOLEAN_FALSE_VALUE = false;
    public static final boolean DEFAULT_BOOLEAN_TRUE_VALUE = true;
    public static final int DEFAULT_BOOLEAN_FALSE_VALUE_AS_INT = 0;
    public static final int DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT = 1;
    public static final int DEFAULT_INTEGER_VALUE = 0;
    public static final int MIN_INTEGER_VALUE = 1;
    public static final double DEFAULT_DOUBLE_VALUE = 0.0;
    public static final double DEFAULT_DOUBLE_VALUE_EPSILON = 0.00000001;
    public static final String DEFAULT_STRING_VALUE = "";
    public static final Long DEFAULT_LONG_EMPTY_VALUE = 0L;
    public static final Double DEFAULT_DOUBLE_EMPTY_VALUE = 0.0;
    public static final Double DEFAULT_DOUBLE_VALUE_ONE = 1.0;
    public static final int LOCK_TRIGGER = 2;
    public static final int ENABLED_TRIGGER = 1;
    public static final int DEFAULT_TWO = 2;

    // Pagination
    public static final String DEFAULT_CURRENT_PAGE = "1";
    public static final String DEFAULT_PER_PAGE = "7";

    // Standard values
    public static final String FOUR_ZEROS_STRING = "0000";


    //Order Status
    public static final Long ORDER_STATUS_CREATED = 1L;
    public static final Long ORDER_STATUS_PROCESSES = 2L;
    public static final Long ORDER_STATUS_CANCELLED = 6L;
    public static final Long ORDER_STATUS_RECOIL = 7L;
    public static final Long DELIVERY_TYPE_EXTERNAL_ID = 4L;

    public static final Long DELIVERY_TYPE_INTERNAL_ID = 2L;

    //Order Paid
    public static final Boolean ORDER_PAID = true;
    public static final Boolean ORDER_NOT_PAID = false;

    //ORDER FLAG NAMES
    public static final String CORPORATE = "corporate";
    public static final String INTEGRATIONS = "integrations";
    public static final String PITS = "pits";
    public static final String SUBMARINE = "submarine";
    public static final String TOGO = "togo";

    // Report Category
    public static final String REPORT_CATEGORY_SERVICES  = "Servicios";

    // Timezone default by device
    public static final String TIMEZONE_BY_DEVICE_DEFAULT = "America/Bogota";

    // State Codes
    public static final String ORDER_APPROVED_PAYMENT = "ORDER_PAID";
    public static final String ORDER_IN_PROGRESS = "ORDER_IN_PROGESS";
    public static final String ORDER_DISCARDED = "ORDER_DISCARDED";

    //Discounts
    public static final Long PRODUCT_DISCOUNT_ID = 1782L;
    public static final Long PRODUCT_TYPE_DISCOUNT_ID = 3L;

    // Timezone default
    public static final String TIMEZONE_UTC_DEFAULT = "Z";

    // Apm
    public static final int APM_MAP_PROPERTIES = 6;

    // Fiscal Identifier
    public static final String UNREGISTERED_USER = "Unregistered user";

    //Countries
    public static final Long BRASIL_CODE = 5L;
    public static final Long COLOMBIA_CODE = 1L;
    public static final Long MEXICO_CODE = 3L;

    public static final String TOTAL_DISCOUNT = "TOTAL_DISCOUNT";
    public static final String PRODUCT_PRICE = "PRODUCT_PRICE";
    public static final String TOTAL_TAXES = "TOTAL_TAXES";

    public static final String UUID_DEFAULT_TEMPLATE_NAME = "-template";

    //TTL
    public static final long EXPIRED_TIME_REGISTER = 86400;

    public static final Long DAYS_OF_THE_WEEK = 7L;

    // Key Local Date Time
    public static final String LOCAL_DATE_TIME_END = "LocalDateTimeEnd";
    public static final String LOCAL_DATE_TIME_START = "LocalDateTimeStart";

    // Key Local Date Time
    public static final String LOCAL_DATE_END = "localDateEnd";
    public static final String LOCAL_DATE_START = "localDateStart";

    public static final String DATE_TIME_CURRENT = "DateTimeCurrent";

    //Format LocalDateTime
    public static final DateTimeFormatter DEFAULT_FORMAT_YYYY_MM_DD_AND_HH_MM_SS = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Long LIMIT_DAYS = 0L;

    public static final String BASIC_DATA_OBJECT_IN_JSON_PAYLOAD = "$.payload.RespuestaUnitaria.DocElectronicoExtendido.DatosBasicos";

    private GlobalConstants() {
        throw new IllegalStateException("Utility class");
    }
}
