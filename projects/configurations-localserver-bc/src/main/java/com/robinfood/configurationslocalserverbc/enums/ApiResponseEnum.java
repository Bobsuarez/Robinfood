package com.robinfood.configurationslocalserverbc.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseEnum {

    RESPONSE_OK_CONFIGURATION_LOCALSERVER_TEMPLATE_BY_STORE(
            HttpStatus.OK.value(),
            "Configuration LocalServer Template by Store  successfully",
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
