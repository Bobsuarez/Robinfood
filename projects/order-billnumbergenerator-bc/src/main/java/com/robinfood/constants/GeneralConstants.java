package com.robinfood.constants;

import okhttp3.MediaType;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class GeneralConstants {

    public static final String URL_CONNECTOR_SIMBA = System.getenv("URL_CONNECTOR_SIMBA");
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static final Boolean DEFAULT_BOOLEAN_FALSE = false;
    public static final Boolean DEFAULT_BOOLEAN_TRUE = true;
    public static final Integer DEFAULT_INTEGER = 0;
    public static final BigDecimal DEFAULT_BIG_DECIMAL_VALUE = BigDecimal.ZERO;
    public static final Boolean IS_PROD = Boolean.parseBoolean(System.getenv("IS_PROD"));
    public static final String DEFAULT_STRING_EMPTY = "";
    public static final String SUCCESSFUL = "Successfully";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String UUID = "uuid";
    public static final String ORDERS = "Orders";
    public static final String THIRD_PARTY = "ThirdParty";

    public static final String ATTRIBUTE_RESULT_DEFAULT = "data";

    public static final String RIGHT_ARROW = "--->";
    public static final String LEFT_ARROW = "<---";

    private GeneralConstants() {
        throw new IllegalStateException("Utility class");
    }

}
