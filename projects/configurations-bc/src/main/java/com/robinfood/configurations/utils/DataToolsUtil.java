package com.robinfood.configurations.utils;

import static com.robinfood.configurations.constants.ConfigurationsBCConstants.DEFAULT_INTEGER;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.DEFAULT_ONE_INTEGER;

public final class DataToolsUtil {

    private DataToolsUtil() {
    }

    public static int convertToStatusInt(Boolean isObject) {

        int status = DEFAULT_INTEGER;

        if (isObject.equals(Boolean.TRUE)) {
            status = DEFAULT_ONE_INTEGER;
        }
        return status;
    }
}
