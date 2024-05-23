package com.robinfood.util;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.exceptions.FieldsValidateOrRequiredException;
import com.robinfood.mappers.ResponseMapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateTypeFieldUtil {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\s*?(.+)@(.+?)\\s*$");

    private ValidateTypeFieldUtil() {
        throw new IllegalStateException("Utility class");
    }


    public static void isEmpty(String field, String nameField) {

        boolean isValidate = field == null || field.trim().isEmpty();

        if (isValidate) {
            catchErrorFieldsValidate(nameField, " the text cannot be empty or null");
        }
    }

    public static void isEmail(String valueString, String nameField) {

        Matcher matcher = EMAIL_PATTERN.matcher(valueString);

        if (!matcher.matches()) {
            catchErrorFieldsValidate(nameField, " the mail entered is not valid");
        }
    }

    public static void isWithinRange(String nameField, int number, int lowerBound, int upperBound) {

        boolean isValidateRange = number >= lowerBound && number <= upperBound;

        if (!isValidateRange) {
            catchErrorFieldsValidate(nameField, " The value is not allowed, it must be from 1 to 4");
        }
    }

    public static void isInteger(Object number, String fieldName) {

        if (number instanceof Integer) {
            isIntegerToString(String.valueOf(number), fieldName);
        }
        if (number instanceof String) {
            isIntegerToString((String) number, fieldName);
        }
    }

    private static void isIntegerToString(String number, String nameField) {

        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            catchErrorFieldsValidate(nameField, " It’s not a number value");
        }
    }

    private static void catchErrorFieldsValidate(String nameField, String complementMessage) {

        String errorMessage = nameField + complementMessage;

        throw new FieldsValidateOrRequiredException(
                ResponseMapper.buildWithError(
                        HttpStatusConstants.SC_PRECONDITION_FAILED.getCodeHttp(),
                        errorMessage,
                        Boolean.TRUE
                ), errorMessage
        );
    }
}
