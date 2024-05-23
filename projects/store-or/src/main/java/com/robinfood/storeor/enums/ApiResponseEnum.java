package com.robinfood.storeor.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseEnum {

    RESPONSE_OK_STORE(
            HttpStatus.OK.value(),
            "Store retrieved successfully",
            HttpStatus.OK.name()
    ),
    RESPONSE_OK_ELECTRONIC_BILLING(
            HttpStatus.OK.value(),
            "Save electronic billing successfully",
            HttpStatus.OK.name()
    ),
    RESPONSE_OK_ELECTRONIC_BILL_LIST(
            HttpStatus.OK.value(),
            "successful search process ",
            HttpStatus.OK.name()
    ),
    RESPONSE_OK_POS_CONFIGURATION(
            HttpStatus.OK.value(),
            "Pos configuration retrieved successfully",
            HttpStatus.OK.name()
    ),
    RESPONSE_OK_POS_BY_STORE(
            HttpStatus.OK.value(),
            "List Pos by Store retrieved successfully",
            HttpStatus.OK.name()
    ),
    RESPONSE_CREATED_POS(
            HttpStatus.CREATED.value(),
            "Pos created successfully",
            HttpStatus.CREATED.name()
    ),
    RESPONSE_UPDATE_POSS(
            HttpStatus.ACCEPTED.value(),
            "Pos updated successfully",
            HttpStatus.ACCEPTED.name()
    ),
    RESPONSE_CREATED_RESOLUTIONS(
            HttpStatus.CREATED.value(),
            "Resolutions created successfully",
            HttpStatus.CREATED.name()
    ),
    RESPONSE_UPDATE_RESOLUTIONS(
            HttpStatus.ACCEPTED.value(),
            "Resolution updated successfully",
            HttpStatus.ACCEPTED.name()
    ),
    RESPONSE_INTERNAL_SERVER_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            HttpStatus.INTERNAL_SERVER_ERROR.name()
    ),

    RESPONSE_OK_FIND_ALL_RESOLUTIONS(
            HttpStatus.OK.value(),
            "List Resolutions retrieved successfully",
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
