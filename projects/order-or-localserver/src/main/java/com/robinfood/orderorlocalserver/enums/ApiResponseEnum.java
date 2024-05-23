package com.robinfood.orderorlocalserver.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseEnum {

    RESPONSE_OK_TEMPLATES_BY_STORE(
            HttpStatus.OK.value(),
            "Templates by store retreived successfully",
            HttpStatus.OK.name()
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
