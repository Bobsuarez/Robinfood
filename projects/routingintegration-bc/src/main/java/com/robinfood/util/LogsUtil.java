package com.robinfood.util;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.Objects;

public class LogsUtil {

    private static LambdaLogger logger;

    private LogsUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void info(String message, Object complement) {
        logger.log("[INFO] " + message + " " + complement.toString());
    }

    public static void info(String message, Object... complement) {
        StringBuilder complementMessage = new StringBuilder();
        for (Object data : complement) {
            complementMessage
                    .append(" ")
                    .append(data);
        }
        logger.log("[INFO] " + message + complementMessage);
    }

    public static void error(String message, Object... complement) {
        StringBuilder complementMessage = new StringBuilder();
        for (Object data : complement) {
            complementMessage
                    .append(" ")
                    .append(data);
        }
        logger.log("[ERROR] " + message + complementMessage);
    }

    public static void getInstance(Context context) {
        if (Objects.isNull(logger)) {
            logger = context.getLogger();
        }
    }

}
