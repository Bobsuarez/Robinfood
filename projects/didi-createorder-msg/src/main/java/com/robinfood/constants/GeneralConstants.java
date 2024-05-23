package com.robinfood.constants;

import okhttp3.MediaType;

public class GeneralConstants {

    public static final Boolean DEFAULT_BOOLEAN_FALSE = Boolean.FALSE;
    public static final Boolean DEFAULT_BOOLEAN_TRUE = Boolean.TRUE;
    public static final Integer DEFAULT_INTEGER = 0;
    public static final Boolean IS_PROD = Boolean.parseBoolean(System.getenv("IS_PROD"));
    public static final String AUTHORIZATION = "authorization";
    public static final String MESSAGE_FROM_HEADER = "messagefrom";
    public static final String MESSAGE_FROM_OUT = "messageFrom";
    public static final String MESSAGE_COUNTRY_HEADER = "messagecountry";
    public static final String MESSAGE_COUNTRY_OUT = "messageCountry";
    public static final String DEFAULT_STRING_EMPTY = "";
    public static final String DEFAULT_BEARER_AUTHORIZATION = "Bearer";
    public static final String SUCCESSFUL = "Successfully";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String UUID = "uuid";

    //symbols
    public static final String RIGHT_ARROW = "--->";
    public static final String LEFT_ARROW = "<---";
    public static final String NAME_APPLICATION = "didi-createOrder-msg-1";

    public static final String TOKEN = "token";

    //JsonResponse
    public static final String ROUTING_ATTRIBUTE_RESULT = "data";
    public static final String SSO_ATTRIBUTE_RESULT = "result";

    public static final int VALUE_TIME_OUT = 10;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");


    private GeneralConstants() {
        throw new IllegalStateException("Utility class");
    }

}
