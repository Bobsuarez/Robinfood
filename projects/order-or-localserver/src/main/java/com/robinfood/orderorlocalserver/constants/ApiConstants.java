package com.robinfood.orderorlocalserver.constants;

public class ApiConstants {

    //status codes
    public static final Integer HTTP_SUCCESS_RESPONSE = 200;


    //Apis
    public static final String API_GET_ORDER_DETAIL = "v1/orders/detail/print";

    public static final String API_GET_SSO_TOKEN = "v1/services";

    public static final String CONFIGURATION_GET_TEMPLATE_V1 = "/v1/configuration/template-store";

    //Endpoints
    public static final String API_PRINTING_TEMPLATES_V1 = "/api/v1/printing-templates";

    public static final String API_GET_PRINTING_TEMPLATES = "/templates";

    //messages
    public static final String AUTHORIZATION_HEADER_BEARER = "Bearer ";

    public static final String DEFAULT_MESSAGE = "OK";

    public static final String DEFAULT_LOCALE = "CO";

    public static final String UNAUTHORIZED_REQUEST = "Unauthorized request.";

}
