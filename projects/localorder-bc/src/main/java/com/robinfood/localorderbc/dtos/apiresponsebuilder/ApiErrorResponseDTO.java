package com.robinfood.localorderbc.dtos.apiresponsebuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.robinfood.localorderbc.enums.ExceptionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import static com.robinfood.localorderbc.configs.constants.APIConstants.DEFAULT_LOCALE;

@Getter
@Setter
public class ApiErrorResponseDTO<T> {

    @Schema(example = "200", description = "The code of the HTTP response")
    private Integer code = HttpStatus.OK.value();

    @Schema(example = "'data': {}", description = "The content of the HTTP response")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T errors;

    @Schema(example = "CO", description = "The locale of the response UTC")
    private String locale = DEFAULT_LOCALE;

    @Schema(example = "OK", description = "The message of the HTTP response")
    private String message;

    @Schema(example = "HttpStatus", description = "The HTTP Status")
    private String status;

    public ApiErrorResponseDTO() {
        this.errors = null;
    }

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

    public ApiErrorResponseDTO(
            Integer code,
            String message,
            ExceptionEnum exceptionEnum
    ) {
        this.errors = null;
        this.messageGeneralException(code, message, exceptionEnum);
    }

    private void messageGeneral(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.status = exceptionEnum.getStatus().name();
        this.message = exceptionEnum.getMessage();
    }

    private void messageGeneralException(Integer code, String message, ExceptionEnum exceptionEnum) {
        this.code = code;
        this.status = exceptionEnum.getStatus().name();
        this.message = message;
    }

}
