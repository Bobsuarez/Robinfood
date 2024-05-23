package com.robinfood.configurationsposbc.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseEnum {

    RESPONSE_OK_CONFIGURATION_POS_BY_STORE_USER(
            HttpStatus.OK.value(),
            "Configuration Pos by Store and User successfully",
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
