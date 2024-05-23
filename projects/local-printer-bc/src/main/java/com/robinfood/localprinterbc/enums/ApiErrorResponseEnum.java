package com.robinfood.localprinterbc.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiErrorResponseEnum {

    INTERNAL_SERVER_ERROR(
            500,
            "internal server error",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    //Invalid information
    ARGUMENT_NOT_VALID_EXCEPTION(
            400,
            "Argument not valid exception",
            HttpStatus.BAD_REQUEST
    ),
    QUERY_PARAMS_VALID_EXCEPTION(
            401,
            "Query params with arguments not valid exception",
            HttpStatus.BAD_REQUEST
    ),
    NOT_FOUND(
            404,
            "resource not found",
            HttpStatus.NOT_FOUND
    );

    private final Integer code;
    private final String message;
    private final HttpStatus status;

    ApiErrorResponseEnum(Integer code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}

