package com.robinfood.constants;

import okhttp3.MediaType;

public class Constants {

    public final static String STATUS_CHANGE_PUBLISHED = "Status Change Published";
    public final static String DATES_NOT_FOUND_MESSAGE = "Not currencies were found for the requested dates";

    // HEADERS
    public static final String AUTHORIZATION = "authorization";
    public static final String CONTENT_TYPE = "application/json";
    public static final String POWERED_BY = "AWS Lambda & Serverless";

    public final static String DEFAULT_STRING_EMPTY = "";

    public final static Boolean DEFAULT_BOOLEAN_FALSE = false;
    public final static Boolean DEFAULT_BOOLEAN_TRUE = true;

    // Flow code
    public final static String CHANGE_STATUS = "CHANGE_STATUS";

    //symbols
    public static final String RIGHT_ARROW = "--->";
    public static final String LEFT_ARROW = "<---";

    //JsonResponse
    public static final String ROUTING_ATTRIBUTE_RESULT = "data";

    //Security
    public final static String JWT_TOKEN_SECRET = System.getenv("JWT_TOKEN_SECRET");

    public final static MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public final static String STORE_ID = "storeId";
}
