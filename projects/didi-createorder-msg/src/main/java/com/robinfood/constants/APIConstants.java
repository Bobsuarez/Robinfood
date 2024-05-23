package com.robinfood.constants;

public class APIConstants {

    public static final String AWS_ACCESS_KEY_DIDI = System.getenv("AWS_ACCESS_KEY_DIDI");
    public static final String AWS_REGION_DIDI = System.getenv("AWS_REGION_DIDI");
    public static final String AWS_SECRET_KEY_DIDI = System.getenv("AWS_SECRET_KEY_DIDI");
    public static final String AWS_SQS_POINT_URL = System.getenv("AWS_SQS_POINT_URL");
    public static final String JWT_TOKEN_SECRET = System.getenv("JWT_TOKEN_SECRET");
    public static final String URL_SSO = System.getenv("URL_SSO");
    public static final String URL_STACK_SECOND = System.getenv("URL_STACK_SECOND");


    private APIConstants() {
        throw new IllegalStateException("Utility class");
    }

}
