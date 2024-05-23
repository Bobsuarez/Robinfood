package com.robinfood.paymentmethodsbc.constants;

import java.util.regex.Pattern;

public final class CreditCardConstants {
    public static final Pattern CC_ONLY_NUMBERS_REGEX = Pattern.compile(
        "^[\\d]+$"
    );
    public static final Pattern CC_VALID_CVV_REGEX = Pattern.compile(
        "^[\\d]{3,4}$"
    );
    public static final Pattern CC_VALID_YEAR_REGEX = Pattern.compile("^([\\d]{4})$");
    public static final Pattern CC_VALID_MONTH_REGEX = Pattern.compile(
        "^([0]?[1-9]|[1][0-2])$"
    );
    public static final int UPDATED = 1;
    public static final int CREDIT_CARD_MAX_SIZE = 20;
    public static final int DEFAULT_IDENTIFICATION_TYPE = 0;
    public static final String DEFAULT_EMAIL = "email@example.com";
    public static final int DEFAULT_UPDATE_VALUE = 0;


    private CreditCardConstants() {}
}
