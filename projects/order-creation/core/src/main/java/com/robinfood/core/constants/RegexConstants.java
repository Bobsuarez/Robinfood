package com.robinfood.core.constants;

public final class RegexConstants {

    //Regex
    public static final String REGEX_NOT_CONTAIN_SPECIAL_CHARACTERS = "Must not contain special characters";

    public static final String REGEX_VALIDATOR_EMAIL = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private RegexConstants() {
        throw new IllegalStateException("Utility class");
    }
}
