package com.robinfood.constants;

import okhttp3.MediaType;

public class Constants {

    // API
    public static final String URL_ROUTING_INTEGRATION_BC = System.getenv("URL_ROUTING_INTEGRATION_BC");
    public static final String URL_SSO = System.getenv("URL_SSO");
    // HTTP CODE
    public static final Long HTTP_OK = 200L;
    // HEADERS
    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN = "token";
    // SERVER ERROR CODE
    public static final Integer SERVER_ERROR_CODE = 500;
    // DEFAULT VALUES
    public static final int DEFAULT_BOOLEAN_FALSE_VALUE_AS_INT = 0;
    public static final int DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT = 1;
    // DELETE BACKSLASH
    public static final String TARGET_BACKSLASH = "\\";
    public static final String TARGET_TO_COMPARE_STRING = "\"{";
    // FLOWS
    public static final String CHANGE_STATUS = "CHANGE_STATUS";
    public static final Long CHANGE_STATUS_ID = 1L;
    // TOKEN
    public static final String AUTH_KEY = System.getenv("AUTH_KEY");
    public static final String TOKEN_AUTH_SECRET = System.getenv("TOKEN_AUTH_SECRET");
    public static final String TOKEN_ISSUER = System.getenv("TOKEN_ISSUER");
    public static final String CONTENT_TYPE = "application/json";
    // EVENT DISPATCHED
    public static final Long EVENT_DISPATCHED = 1L;
    // SUBSCRIBER TYPES
    public static final String SUBSCRIBER_HTTP_TYPE = "HTTP";
    // PROPERTY KEY
    public static final String PROPERTY_URL_TYPE = "URL";
    //symbols
    public static final String RIGHT_ARROW = "--->";
    public static final String LEFT_ARROW = "<---";
    //JsonResponse
    public static final String SSO_ATTRIBUTE_RESULT = "result";
    public static final String ATTRIBUTE_RESULT_DEFAULT = "data";
    public static final int VALUE_TIME_OUT = 10;
    public final static MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private Constants() {
        throw new IllegalStateException("Utility class");
    }
}
