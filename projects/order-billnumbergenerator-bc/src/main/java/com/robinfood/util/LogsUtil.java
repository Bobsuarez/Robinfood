package com.robinfood.util;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.Objects;

public class LogsUtil {

    private static final String prefixInfo = "[INFO] ";
    private static LambdaLogger logger;

    private LogsUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void info(String message, Object complement) {
        String messageFormat = builderMessage(message, complement);
        logger.log(prefixInfo + messageFormat);
    }

    public static void info(String message, Object... complement) {
        String messageFormat = builderMessage(message, complement);
        logger.log(prefixInfo + messageFormat);
    }

    public static void info(String message) {
        logger.log(prefixInfo + message);
    }

    public static void error(String message, Object... complement) {
        String messageFormat = builderMessage(message, complement);
        logger.log("[ERROR] " + messageFormat);
    }

    public static void getInstance(Context context) {
        if (Objects.isNull(logger)) {
            logger = context.getLogger();
        }
    }

    private static String builderMessage(String message, Object... complement) {
        try {
            return String.format(message, complement);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
