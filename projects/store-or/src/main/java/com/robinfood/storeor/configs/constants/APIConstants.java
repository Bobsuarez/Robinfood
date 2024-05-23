package com.robinfood.storeor.configs.constants;

public final class APIConstants {

    // API Headers
    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    public static final String AUTHORIZATION_HEADER_BEARER = "Bearer ";

    // Controller Endpoints
    public static final String STORE_V1 = "/api/v1/store";
    public static final String POS_STORES_V1 = "/api/v1/pos/stores";
    public static final String BILLING_V1 = "/api/v1/billing";
    public static final String ELECTRONIC_BILLING = "/electronic-billing";

    public static final String GET_ALL = "/all";

    public static final String POS_CONFIGURATION = "/pos-configuration";
    public static final String USER_V1 = "/api/v1/user";
    public static final String POS_BY_STORE_CONFIGURATION = "/{storeId}/pos";
    public static final String RESOLUTIONS = "/resolutions";
    public static final String POS = "/pos";
    public static final String RESOLUTION_ID = "/{resolutionId}";
    public static final String POS_ID = "/{id}";
    public static final String ACTIVE_RESOLUTION = "/active";
    public static final String ACTIVE_POS = "/active";
    public static final String ALL = "/all";

    // API external
    public static final String CREATE_POS_CONFIGURATIONS = "v1/pos/stores/pos";
    public static final String CREATE_RESOLUTIONS_ORDERS = "v1/pos/stores/resolutions";
    public static final String CREATE_RESOLUTIONS_CONFIGURATIONS = "v1/stores/resolutions";

    public static final String FIND_ALL_RESOLUTIONS = "v1/resolutions/all";
    public static final String UPDATE_RESOLUTIONS_ORDERS = "v1/pos/stores/resolutions/{resolutionId}";
    public static final String UPDATE_RESOLUTIONS_CONFIGURATIONS = "v1/stores/resolutions/{resolutionId}";
    public static final String TURN_OR_OFF_RESOLUTIONS_CONFIGURATIONS = "v1/stores/resolutions/{id}/active";
    public static final String TURN_OR_OFF_RESOLUTIONS_ORDERS = "v1/pos/stores/resolutions/{resolutionId}/active";
    public static final String UPDATE_POS_CONFIGURATIONS = "v1/pos/stores/pos/{id}";
    public static final String ACTIVATE_OR_DEACTIVATE_POS_CONFIGURATIONS = "v1/pos/stores/pos/{id}/active";
    public static final String GET_LIST_POS = "v1/pos/all";

    public static final String UPDATE_RESOLUTIONS_WITH_POS = "v1/resolution/{id}/pos/{posId}";

    // Timezone default
    public static final String TIMEZONE_UTC_DEFAULT = "Z";
    public static final Long DEFAULT_LONG = 0L;
    public static  final Long DEFAULT_LONG_ONE = 1L;

    // API Response Codes
    public static final int BAD_REQUEST = 400;
    public static final int CREATED_CODE = 201;
    public static final int UPDATED_CODE = 202;
    public static final int NOT_FOUND_CODE = 404;
    public static final int PRECONDITION_FAILED_CODE = 412;
    public static final int SUCCESS_CODE = 200;
    public static final int UNAUTHORIZED_CODE = 401;

    // API Response Values
    public static final String BAD_REQUEST_MESSAGE = "Bad Request";
    public static final String DEFAULT_LOCALE = "CO";
    public static final String DEFAULT_MESSAGE = "OK";
    public static final String NOT_FOUND = "NOT FOUND";
    public static final String PRECONDITION_FAILED = "PRECONDITION FAILED";
    public static final String UNAUTHORIZED_MESSAGE = "Unauthorized";

    public static final String DEFAULT_STRING_VALUE = "";
    public static final String UNAUTHORIZED_REQUEST = "Unauthorized request.";

    //DeliveryEnum
    public static final Long POS_BASIC_CODE = 1L;
    public static final Long POS_DELIVERY_CODE = 2L;
    public static final Long POS_PHYSICAL_CODE = 3L;

    public static final String ERROR_CREATED_CONFIGURATIONS_BC = "resolutions could not be created in " +
            "configurations bc ";
    public static final String ERROR_CREATE_IN_ORDERS = "Resolutions could not be created in database orders ";

    public static final String ERROR_UPDATE_CONFIGURATIONS_BC = "Resolutions could not be updated in " +
            "configurations bc ";

    public static final String ERROR_UPDATE_IN_ORDERS = "Resolutions could not be updated in database orders ";

    public static final String ERROR_UPDATE_POS_CONFIGURATIONS_BC = "Pos could not be updated in " +
            "configurations bc ";

    //logs
    public static final String LOG_REQUEST_CREATE_RESOLUTIONS = "Create resolutions request {}, to create";

    private APIConstants() {
        throw new IllegalStateException("Utility class");
    }
}

