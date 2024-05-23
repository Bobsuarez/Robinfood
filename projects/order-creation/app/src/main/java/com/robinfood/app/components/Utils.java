package com.robinfood.app.components;

public final class Utils {

    private Utils() {}

    public static String getApplicationProperty(String key) {
        return BaseApplicationContextAware
                .getApplicationContext()
                .getEnvironment()
                .getProperty(key);
    }

    public static boolean parseBoolean(String value) {
        return value != null && "true".equalsIgnoreCase(value);
    }
}
