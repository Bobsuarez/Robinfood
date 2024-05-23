package com.robinfood.configurationslocalserverbc.configs.constans;

public final class APIConstants {

    // API Headers
    public static final String UNAUTHORIZED_REQUEST = "Unauthorized request.";
    public static final String DEFAULT_STRING_VALUE = "";
    public static final String DEFAULT_LOCALE = "CO";

    // Controller Endpoints
    public static final String CONFIGURATION_LOCALSERVER_V1 = "/api/v1/configuration";
    // Controller Endpoints
    public static final String CONFIGURATION_GET_TEMPLATE_V1 = "/template-store";

    // Timezone default
    public static final String TIMEZONE_UTC_DEFAULT = "Z";

    private APIConstants() {
        throw new IllegalStateException("Utility class");
    }
}
