package org.example.util;

import org.example.exceptions.FieldsValidateOrRequiredException;
import org.example.mappers.ResponseMapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateTypeFieldUtil {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\s*?(.+)@(.+?)\\s*$");

    private ValidateTypeFieldUtil() {
        throw new IllegalStateException("Utility class");
    }


    public static void isEmpty(String field, String nameField) {
        boolean isValidate = field == null || field.trim()
                .isEmpty();
        if (isValidate) {
            String errorMessage = nameField + " the text cannot be empty or null";
            throw new FieldsValidateOrRequiredException(
                    ResponseMapper.buildWithError(
                            400,
                            errorMessage,
                            Boolean.TRUE
                    ), errorMessage
            );
        }
    }

    public static void isEmail(String valueString, String nameField) {

        Matcher matcher = EMAIL_PATTERN.matcher(valueString);

        if (!matcher.matches()) {
            String errorMessage = nameField + " the mail entered is not valid";
            throw new FieldsValidateOrRequiredException(
                    ResponseMapper.buildWithError(
                            400,
                            errorMessage,
                            Boolean.TRUE
                    ), errorMessage
            );
        }

    }

    public static void isWithinRange(int number, int lowerBound, int upperBound) {

        boolean valor = number >= lowerBound && number <= upperBound;

        if (!valor) {
            throw new FieldsValidateOrRequiredException(
                    ResponseMapper.buildWithError(
                            400,
                            "ERRRO de tipo de documento",
                            Boolean.TRUE
                    ), "Error tipo de documento"
            );
        }
    }

    public static void is(Object number, String fieldName) {

        if (number instanceof Integer) {
            isIntegerToString(String.valueOf(number), fieldName);
        }
        if (number instanceof String) {
            isIntegerToString((String) number, fieldName);
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
            String errorMessage = nameField + " It’s not a number value";
            throw new FieldsValidateOrRequiredException(
                    ResponseMapper.buildWithError(
                            400,
                            errorMessage,
                            Boolean.TRUE
                    ), errorMessage
            );
        }
    }
}
