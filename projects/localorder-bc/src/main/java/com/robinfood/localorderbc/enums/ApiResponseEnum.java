package com.robinfood.localorderbc.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseEnum {

    RESPONSE_OK_ORDER_CREATE(
            HttpStatus.OK.value(),
            "Order create successfully",
            HttpStatus.OK.name()
    ),
    RESPONSE_OK_ORDER_CHANGE_STATE(
            HttpStatus.OK.value(),
            "Order status changed successfully",
            HttpStatus.OK.name()
    ),
    RESPONSE_OK_GET_ORDER(
            HttpStatus.OK.value(),
            "Get Order successfully",
            HttpStatus.OK.name()
    ),
    RESPONSE_OK_ALL_GET_ORDER(
            HttpStatus.OK.value(),
            "Get all Order successfully",
            HttpStatus.OK.name()
    ),
    RESPONSE_INTERNAL_SERVER_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            HttpStatus.INTERNAL_SERVER_ERROR.name()
    );

    private final Integer code;
    private final String message;
    private final String status;

    ApiResponseEnum(Integer code, String message, String status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

}
