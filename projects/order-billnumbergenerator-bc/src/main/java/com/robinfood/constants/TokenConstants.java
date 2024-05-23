package com.robinfood.constants;

public class TokenConstants {

    public static final String AUTHORIZATION = "authorization";
    public static final String JWT_TOKEN_SECRET = System.getenv("JWT_TOKEN_SECRET");
    public static final String JWT_TOKEN_AUD = System.getenv("JWT_TOKEN_AUD");

    private TokenConstants() {
        throw new IllegalStateException("Utility class");
    }
}
