package com.robinfood.util;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

public class LogsUtil {

    private static final String prefixInfo = "[INFO] ";
    private static LambdaLogger logger;

    private LogsUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void info(String message, Object complement) {
        String messageFormat = String.format(message, complement);
        logger.log(prefixInfo + messageFormat);
    }

    public static void info(String message, Object... complement) {
        String messageFormat = String.format(message, complement);
        logger.log(prefixInfo + messageFormat);
    }

    public static void info(String message) {
        logger.log(prefixInfo + message);
    }

    public static void error(String message, Object... complement) {
        String messageFormat = String.format(message, complement);
        logger.log("[ERROR] " + messageFormat);
    }

    public static void getInstance(Context context) {
        if (Objects.isNull(logger)) {
            logger = context.getLogger();
        }
    }

    public static String getStackTraceAsString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }

}
