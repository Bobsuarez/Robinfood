package com.robinfood.constants;

public class ThirdPartyConstants {

    public static final String THIRD_PARTY_DOCUMENT_NUMBER = System.getenv("THIRD_PARTY_DOCUMENT_NUMBER");
    public static final String THIRD_PARTY_DOCUMENT_TYPE = System.getenv("THIRD_PARTY_DOCUMENT_TYPE");
    public static final String THIRD_PARTY_EMAIL = System.getenv("THIRD_PARTY_EMAIL");
    public static final String THIRD_PARTY_FULL_NAME = System.getenv("THIRD_PARTY_FULL_NAME");

    private ThirdPartyConstants() {
        throw new IllegalStateException("Utility class");
    }
}
