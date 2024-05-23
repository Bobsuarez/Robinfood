package com.robinfood.enums;

import lombok.Getter;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
public enum ErrorLogsEnum {

    ERROR_CONNECTION("0501", "Failed to connect to the database"),

    ERROR_APP_EXCEPTION("0502", "%s, %s, %s"),

    ERROR_EXCEPTION_TOKEN("0503", "Message Error Token: %s  Caused By: %s"),

    ERROR_DATA_NOT_FOUND("0504", "Data not found"),

    ERROR_DATA_NOT_FOUND_COMPLEMENT("0505", "Data not found __COMPLEMENT__"),

    ERROR_INSERT_DATA("0506", "Error inserting the data check values"),

    ERROR_RESPONSE_HTTP("0507", "Response HTTP unsuccessfully URL: __COMPLEMENT__ , exception : {}"),

    ERROR_RESPONSE_HTTP_BODY_NULL("0508",
            "Response HTTP URL: __COMPLEMENT__ , exception : body is null or empty"),

    ERROR_PROCESS_SEND_CONNECTOR_SIMBA("0509",
            "[ProcessConnectorToSimbaUseCase] --> invoke ApplicationException : __COMPLEMENT__"),

    ERROR_PROCESS_VALIDATE_THIRD_PARTY("0510", """
            Error when validating the data in thirdparty : %s ,the final consumer is used.
            """),
    ERROR_PROCESS_BUILD_PARAMETROS_SIMBA("0511",
            "[DecoratorParametros] --> invoke ApplicationException : __COMPLEMENT__"),

    ERROR_PROCESS_BUILD_EXTENSIONES_SIMBA("0512",
            "[DecoratorExtensiones] --> invoke ApplicationException : __COMPLEMENT__"),

    ERROR_PROCESS_BUILD_LINEAS_SIMBA("0513",
            "[DecoratorLineas] --> invoke ApplicationException : __COMPLEMENT__"),

    ERROR_PROCESS_BUILD_TERCEROS_SIMBA("0514",
            "[DecoratorTercero] --> invoke ApplicationException : __COMPLEMENT__"),

    ERROR_PROCESS_BUILD_TOTALES_SIMBA("0515",
            "[DecoratorTotales] --> invoke ApplicationException : __COMPLEMENT__"),

    ERROR_PROCESS_BUILD_AGREGADO_COMERCIAL_SIMBA("0516",
            "[DecoratorAgregadoComercial] --> invoke ApplicationException : __COMPLEMENT__"),

    ERROR_PROCESS_BUILD_ENCABEZADO_SIMBA("0517",
            "[DecoratorEncabeaado] --> invoke ApplicationException : __COMPLEMENT__");

    private static final Pattern compilePattern = Pattern.compile("__COMPLEMENT__");
    private final String code;
    private final String message;

    ErrorLogsEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String replaceComplement(Object complement) {
        return replaceString(this.message, complement);
    }

    private String replaceString(String objectReplace, Object complement) {
        if (Objects.nonNull(complement)) {
            return objectReplace.replaceAll(compilePattern.toString(), complement.toString());
        }

        return objectReplace.replaceAll(compilePattern.toString(), "");
    }

    public String getMessageWithCode() {
        return code + " " + message;
    }

    public String getMessageWithCodeWithComplement(Object complement) {
        return code + " " + replaceString(this.message, complement);
    }
}
