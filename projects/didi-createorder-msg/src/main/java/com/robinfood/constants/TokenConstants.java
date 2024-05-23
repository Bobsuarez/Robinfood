package com.robinfood.constants;

public class TokenConstants {

    public static final String AUTH_KEY = System.getenv("AUTH_KEY");
    public static final String TOKEN_AUTH_SECRET = System.getenv("TOKEN_AUTH_SECRET");
    public static final String TOKEN_ISSUER = System.getenv("TOKEN_ISSUER");
    private TokenConstants() {
        throw new IllegalStateException("Utility class");
    }
}
