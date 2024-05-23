package com.robinfood.constants;

public class GeneralConstants {

    //General
    public final static String SUCCESSFUL = "Successfully";
    public final static String AUTHORIZATION = "authorization";
    public final static String DEFAULT_STRING_EMPTY = "";
    public final static String DEFAULT_SPACE_STRING_EMPTY = " ";
    public final static Boolean DEFAULT_BOOLEAN_FALSE = false;
    public final static Boolean DEFAULT_BOOLEAN_TRUE = true;
    public final static Integer DEFAULT_INTEGER = 0;
    public final static Integer DEFAULT_ONE_INTEGER = 1;
    public final static String BODY_IS_NULL = "Body is null";
    public final static String PATH_PARAMETER_RESOLUTION_ID_IS_NULL = "Resolution Id is null";

    public final static String PATH_PARAMETER_POS_ID_IS_NULL = "Pos Id is null";
    public static final String FIELDS_RESOLUTION_ID = "resolutionId";
    public static final String FIELDS_POS_ID = "posId";
    public static final String FIELDS_ID = "id";

    //Region Aws
    public static final Boolean IS_PROD = Boolean.parseBoolean(System.getenv("IS_PROD"));

    //Security
    public static String JWT_TOKEN_SECRET = System.getenv("JWT_TOKEN_SECRET");


    public static final String PAGE = "page";
    public static final String SIZE = "size";
    public static final String STATUS = "status";
    public static final String VALUE_CUSTOM_FILTER = "valueCustomFilter";
    public static final String ORDER_BY_END_DATE_RESOLUTION = "orderByEndDateResolution";
    public static final String WITH_POS = "withPos";
    public static final String RESOLUTION_ID = "resolutionId";
}
