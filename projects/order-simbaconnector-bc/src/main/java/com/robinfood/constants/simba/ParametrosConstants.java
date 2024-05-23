package com.robinfood.constants.simba;

public class ParametrosConstants {

    public static final Boolean ACTIVADO = Boolean.TRUE;
    public static final String EMAIL = System.getenv("EMAIL");
    public static final String VERSION_DOC_ELECTRONICO = "4.0";
    public static final String MODO_RESPUESTA = "1";
    public static final String ENVIRONMENT_TYPE = System.getenv("ENVIRONMENT_TYPE"); // 1=DEV 2=PROD
    public static final String TOKEN_EMPRESA = System.getenv("COMPANY_TOKEN");
    public static final String PASSWORD_EMPRESA = System.getenv("COMPANY_PASSWORD");
    public static final String TIPO_REPORTE = "1";
    public static final String ID_ETIQUETA_UBICACION_CORREO = "1";
    public static final String NOMBRE_INDICADOR = "SKIPVALIDDIANLOGI";
    public static final String NOMBRE_INDICADOR_DIAN_REQUI = "SKIPVALIDDIANREQU";

    private ParametrosConstants() {
        throw new IllegalStateException("Utility class");
    }

}
