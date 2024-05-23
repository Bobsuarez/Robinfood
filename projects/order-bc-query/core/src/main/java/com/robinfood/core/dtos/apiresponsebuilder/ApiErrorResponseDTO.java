package com.robinfood.core.dtos.apiresponsebuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.robinfood.core.enums.ExceptionEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.robinfood.core.constants.GlobalConstants.TIMEZONE_UTC_DEFAULT;

@Getter
@Setter
public class ApiErrorResponseDTO<T> {

    private Integer code;
    private ZonedDateTime locale = ZonedDateTime.now(ZoneId.of(TIMEZONE_UTC_DEFAULT));
    private HttpStatus status;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T errors;

    public ApiErrorResponseDTO(
            T errors,
            ExceptionEnum exceptionEnum
    ) {
        this.errors = errors;
        this.messageGeneral(exceptionEnum);
    }

    public ApiErrorResponseDTO(
            ExceptionEnum exceptionEnum
    ) {
        this.errors = null;
        this.messageGeneral(exceptionEnum);
    }

    private void messageGeneral(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.status = exceptionEnum.getStatus();
        this.message = exceptionEnum.getMessage();
    }
}
