package com.robinfood.paymentmethodsbc.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum con el listado de codigos de respuesta y respectivo mensaje
 */
@Getter
public enum ResponseCode {
    SUCCESS(200, "SOLICITUD EXITOSA"),

    CREATED(201, "CREACIÓN EXITOSA"),

    RESOURCES_NOT_EXIST(404, "RECURSO NO EXISTE"),

    METHOD_NOT_EXIST(405, "METODO NO EXISTE"),

    PARAMETER_NOT_FOUND(422, "PARAMETRO NO ENCONTRADO"),

    ENTITY_NOT_FOUND(422, "ENTIDAD NO ENCONTRADA"),

    PARAMETER_INVALID(422, "PARAMETRO NO VALIDO"),

    VALIDATION_ERROR(422, "ERROR DE VALIDACION"),

    PARAMETER_TYPE_ERROR(422, "ERROR TIPO DE DATO"),

    SERVICE_ERROR(500, "ERROR EN EL SERVIDOR"),

    CLIENT_ERROR(400, "ERROR EN EL CLIENTE"),

    UNAUTHORIZED(401, "ACCESO DENEGADO"),

    FORBIDEN(403, "PERMISO DENEGADO"),

    DATABASE_ERROR(501, "ERROR DE BASE DE DATOS");

    private int code;
    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseCode getResponseCodeByCode(int code) {
        return Arrays.stream(ResponseCode.values())
            .filter(x -> x.getCode() == code)
            .findAny().orElse(SERVICE_ERROR);
    }
}
