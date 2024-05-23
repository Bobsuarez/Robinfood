package com.robinfood.configurationsposbc.configs.constans;

public final class APIConstants {

    // API Headers
    public static final String UNAUTHORIZED_REQUEST = "Unauthorized request.";
    public static final String DEFAULT_STRING_VALUE = "";
    public static final String DEFAULT_LOCALE = "CO";

    // Controller Endpoints
    public static final String CONFIGURATION_POS_V1 = "/api/v1/configuration";

    // Timezone default
    public static final String TIMEZONE_UTC_DEFAULT = "Z";

    private APIConstants() {
        throw new IllegalStateException("Utility class");
    }
}
