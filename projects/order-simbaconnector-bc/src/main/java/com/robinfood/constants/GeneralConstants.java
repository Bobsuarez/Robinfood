package com.robinfood.constants;

import okhttp3.MediaType;

import java.util.Objects;

public class GeneralConstants {

    public static final Boolean DEFAULT_BOOLEAN_TRUE = true;
    public static final Integer DEFAULT_INTEGER = 0;
    public static final Integer DEFAULT_INTEGER_ONE = 1;
    public static final int DEFAULT_INTEGER_TWO = 2;
    public static final String AUTHORIZATION = "authorization";
    public static final String DEFAULT_STRING_EMPTY = "";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final Long DEFAULT_LONG_VALUE_ONE = 1L;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static final String BASE_SIMBA_URL = System.getenv("BASE_SIMBA_URL");
    public static final String API_SIMBA = System.getenv("API_SIMBA");
    public static final String RIGHT_ARROW = "--->";
    public static final String LEFT_ARROW = "<---";
    public static final String NOMBRE_SOFTWARE = "RobinFood System";
    public static final String SOFTWARE_VERSION = "1.0.13";
    public static final String NO_APLICA = "No aplica";
    public static final String FORMAT_DATE_WITH_HOUR_AND_PM_AM = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE_WITH_LOCAL_TIME = "yyyy-MM-dd'T'HH:mm:ss";
    public final static String URL_QR = Objects.nonNull(System.getenv("URL_QR")) ? System.getenv("URL_QR") : System.getProperty("URL_QR");

    public final static String RESPUESTA_UNITARIA = "RespuestaUnitaria";
    public final static String DOC_ELECTRONICO_ESTENDIDO = "DocElectronicoExtendido";
    public final static String DATOS_BASICOS = "DatosBasicos";

    private GeneralConstants() {
        throw new IllegalStateException("Utility class");
    }

}
