package com.robinfood.constants;

public class GeneralConstants {

    public static final Boolean DEFAULT_BOOLEAN_FALSE = false;
    public static final Boolean DEFAULT_BOOLEAN_TRUE = true;
    public static final Integer DEFAULT_INTEGER = 0;
    public static final Boolean IS_PROD = Boolean.parseBoolean(System.getenv("IS_PROD"));
    public static final String AUTHORIZATION = "authorization";
    public static final String DEFAULT_STRING_EMPTY = "";
    public static final String JWT_TOKEN_SECRET = System.getenv("JWT_TOKEN_SECRET");
    public static final String SUCCESSFUL = "Successfully";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String UUID = "uuid";

    private GeneralConstants() {
        throw new IllegalStateException("Utility class");
    }

}
