package com.robinfood.orderorlocalserver.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionEnum {

    //Data source
    DATA_ACCESS_EXCEPTION(
            5000,
            "Data source error",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    INTERNAL_SERVER_ERROR(
            5001,
            "internal server error",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    //Invalid information
    ARGUMENT_NOT_VALID_EXCEPTION(
            4000,
            "Argument not valid exception",
            HttpStatus.BAD_REQUEST
    ),
    QUERY_PARAMS_VALID_EXCEPTION(
            4001,
            "Query params with arguments not valid exception",
            HttpStatus.BAD_REQUEST
    ),
    NOT_FOUND(
            4004,
            "resource not found",
            HttpStatus.NOT_FOUND
    );

    private final Integer code;
    private final String message;
    private final HttpStatus status;

    ExceptionEnum(Integer code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

}
