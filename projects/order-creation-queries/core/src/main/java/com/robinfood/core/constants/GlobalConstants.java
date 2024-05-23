package com.robinfood.core.constants;

import com.robinfood.core.entities.SortEntity;

public final class GlobalConstants {

    // Default Values
    public static final int DEFAULT_INTEGER_VALUE = 0;
    public static final int DEFAULT_FIRST_LETTER = 1;
    public static final String DEFAULT_LOCALE = "CO";
    public static final String DEFAULT_STRING_VALUE = "";
    public static final Long DEFAULT_LONG_VALUE = 0L;

    public static final String DEFAULT_NAME_TOTAL = "Total";

    public static final Integer NUMBER_OF_DECIMALS = 4;
    public static final Long LONG_VALUE_ONE_HUNDRED = 100L;

    public static final boolean DEFAULT_BOOLEAN_TRUE_VALUE = true;

    public static final boolean DEFAULT_BOOLEAN_FALSE_VALUE = false;

    // Log messages
    public static final String REQUEST_CONTROLLER = "Request finished";
    public static final String RESPONSE_CONTROLLER = "Response finished";

    // Apm
    public static final int APM_MAP_PROPERTIES = 6;

    // RobinFood brand id
    public static final Long ROBINFOOD_BRAND_ID = 9L;

    // Pagination
    public static final String DEFAULT_CURRENT_PAGE = "1";
    public static final String DEFAULT_PER_PAGE = "7";
    public static final String LOCAL_DATE_END = "LocalDateEnd";
    public static final String LOCAL_DATE_START = "LocalDateStart";

    // Special characters
    public static final String WHITE_SPACE = " ";
    public static final String HYPHEN = "-";
    public static final String UNDERSCORE = "_";

    public static final String NAME_TOTAL_COMPANY = "Total de la compañia";
    public static final String CURRENCY_USD = "USD";
    public static final String FORMAT_DATE = "yyyy-MM-dd HH:mm:ss";

    // status orders paid
    public static final String CODE_ORDER_PAID = "ORDER_PAID";
    public static final String CODE_ORDER_CANCELED = "ORDER_CANCELED";
    // id orders canceled and deleted
    public static final Long ID_ORDER_CANCELED = 6L;
    public static final Long ID_ORDER_DELETED = 8L;

    public static final SortEntity SORT_ENTITY = SortEntity.builder()
            .empty(true)
            .sorted(true)
            .unsorted(true)
            .build();

    private GlobalConstants() {
        throw new IllegalStateException("Utility class");
    }
}
