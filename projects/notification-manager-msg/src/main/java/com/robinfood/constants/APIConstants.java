package com.robinfood.constants;

public class APIConstants {

    public static final String BROKER_URL = System.getenv("BROKER_URL");
    public static final String BROKER_USER = System.getenv("BROKER_USER");
    public static final String BROKER_PASSWORD = System.getenv("BROKER_PASSWORD");
    public static final String DESTINATION = System.getenv("DESTINATION");
    public static final String JWT_TOKEN_SECRET = System.getenv("JWT_TOKEN_SECRET");


    private APIConstants() {
        throw new IllegalStateException("Utility class");
    }

}
