package com.robinfood.changestatusbc.configs.constants;

public final class APIConstants {

    private APIConstants() {
        throw new IllegalStateException("Utility class");
    }

    // Controller Version
    public static final String V1 = "api/v1";

    // Controller Endpoints
    public static final String CHANGE_STATUS_ENDPOINT = "/states";

    //Message Response
    public static final String STATUS_CHANGE_SUCCESSFUL_MESSAGE = "Status Change Successful";
}
