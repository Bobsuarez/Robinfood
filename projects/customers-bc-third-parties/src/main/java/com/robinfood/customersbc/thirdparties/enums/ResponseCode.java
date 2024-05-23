package com.robinfood.customersbc.thirdparties.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS(200, "SUCCESS"),
    CREATED(201, "CREATED"),
    ENTITY_NOT_FOUND(404, "ENTITY_NOT_FOUND"),
    CLIENT_ERROR(400, "CLIENT_ERROR"),
    JSON_MAPPING_ERROR(400, "JSON_MAPPING_ERROR"),
    HTTP_CLIENT_ERROR(400, "HTTP_CLIENT_ERROR"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    FORBIDDEN(403, "FORBIDDEN"),
    SERVICE_ERROR(500, "ERROR ON THE SERVER");

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
