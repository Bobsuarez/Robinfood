package com.robinfood.utils;

import static com.robinfood.constants.Constants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static com.robinfood.constants.Constants.TARGET_BACKSLASH;
import static com.robinfood.constants.Constants.TARGET_TO_COMPARE_STRING;

public class FormatStringEventActivemqUtil {

    private FormatStringEventActivemqUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String execute(String messageActiveMQEvent) {

        messageActiveMQEvent = deleteBackslash(TARGET_BACKSLASH, messageActiveMQEvent);

        String isValidateJson = messageActiveMQEvent.substring(0, 2);

        if (isValidateJson.equals(TARGET_TO_COMPARE_STRING)) {
            messageActiveMQEvent = deleteFirstAndLastCharacter(messageActiveMQEvent);
        }

        return messageActiveMQEvent;
    }

    private static String deleteBackslash(String target, String message) {

        return message.replace(target, "");
    }

    private static String deleteFirstAndLastCharacter(String message) {

        return message.substring(
                DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT,
                message.length() - DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT
        );
    }
}
