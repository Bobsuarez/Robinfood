package com.robinfood.changestatusor.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.robinfood.changestatusor.enums.ExceptionEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorResponseDTO<T> {

    private Integer code;
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
        this.message = exceptionEnum.getMessage();
    }
}
