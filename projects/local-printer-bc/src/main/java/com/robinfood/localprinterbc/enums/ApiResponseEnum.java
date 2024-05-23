package com.robinfood.localprinterbc.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;



@Getter
public enum ApiResponseEnum {

    RESPONSE_OK_TEMPLATE_FOUND(
            HttpStatus.OK.value(),
            "Template found successfully",
            HttpStatus.OK.name()
    ),
    RESPONSE_OK_PRINT_ORDERS(
            HttpStatus.OK.value(),
            "Print Order successfully",
            HttpStatus.OK.name()
    ),
    RESPONSE_OK_PRINT_INVOICES(
            HttpStatus.OK.value(),
            "Print Invoice successfully",
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
