package com.robinfood.customersbc.thirdparties.constants;

public final class JwtTokenConstants {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000L;
    public static final String CLAIM_USER_KEY = "user";
    public static final String CLAIM_COMPANY_ID_KEY = "company_id";
    public static final String SESSION_USER_PASSWORD = "***";

    private JwtTokenConstants() {}
}
