package com.robinfood.paymentmethodsbc.constants;

public final class JwtTokenConstants {
    // 5 hours
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000L;

    public static final String BEARER = "Bearer ";

    public static final String ALG_KEY = "alg";
    public static final String TYP_KEY = "typ";
    public static final String ISSUED_AT_KEY = "iat";
    public static final String EXP_KEY = "exp";
    public static final String ISSUER_KEY = "iss";
    public static final String AUDIENCE_KEY = "aud";
    public static final String MODULES_KEY = "mod";
    public static final String PERMISSIONS_KEY = "per";
    public static final String JSONWEBTOKEN_ID_KEY = "jti";
    public static final String NOT_BEFORE_KEY = "nbf";

    public static final String HS512 = "HS512";
    public static final String JWT = "JWT";
    public static final String PUBLIC_AUDIENCE = "public";
    public static final String SERVICE_AUDIENCE = "service";
    public static final String ISSUER = "paymentmethods.bc.redeban.v1";
    public static final int NOT_BEFORE = 0;

    // default 12 hours
    public static final Long PUBLIC_EXPIRATION = 12L;

    private JwtTokenConstants() {}
}
