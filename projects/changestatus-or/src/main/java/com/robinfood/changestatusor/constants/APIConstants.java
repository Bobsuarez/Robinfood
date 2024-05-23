package com.robinfood.changestatusor.constants;

import okhttp3.MediaType;

public class APIConstants {

    // API Headers
    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    public static final String AUTHORIZATION_HEADER_BEARER = "Bearer ";

    // API Response Codes
    public static final int BAD_REQUEST = 400;
    public static final int CREATED_CODE = 201;
    public static final int NOT_FOUND_CODE = 404;
    public static final int PRECONDITION_FAILED_CODE = 412;
    public static final int SUCCESS_CODE = 200;
    public static final int UNAUTHORIZED_CODE = 401;

    // API Response Values
    public static final String BAD_REQUEST_MESSAGE = "Bad Request";
    public static final String DEFAULT_LOCALE = "CO";
    public static final String DEFAULT_MESSAGE = "OK";
    public static final String NOT_FOUND = "NOT FOUND";
    public static final String PRECONDITION_FAILED = "PRECONDITION FAILED";
    public static final String UNAUTHORIZED_MESSAGE = "Unauthorized";

    public static final String STATE_V1 = "api/v1/states";
    public static final String SERVICES_V1 = "v1/services";

    public static final String TIMEZONE_UTC_DEFAULT = "Z";

    public final static MediaType JSON = MediaType.get("application/json; charset=utf-8");
}
