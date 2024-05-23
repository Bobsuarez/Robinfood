package com.robinfood.localprinterbc.dtos.apiresponsebuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.robinfood.localprinterbc.enums.ApiErrorResponseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import static com.robinfood.localprinterbc.configs.constants.APIConstants.DEFAULT_LOCALE;

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
            ApiErrorResponseEnum exceptionEnum
    ) {
        this.errors = errors;
        this.messageGeneral(exceptionEnum);
    }

    public ApiErrorResponseDTO(
            ApiErrorResponseEnum exceptionEnum
    ) {
        this.errors = null;
        this.messageGeneral(exceptionEnum);
    }

    public ApiErrorResponseDTO(
            Integer code,
            String message,
            ApiErrorResponseEnum apiErrorResponseEnum
    ) {
        this.errors = null;
        this.messageGeneralException(code, message, apiErrorResponseEnum);
    }

    private void messageGeneral(ApiErrorResponseEnum apiErrorResponseEnum) {
        this.code = apiErrorResponseEnum.getCode();
        this.status = apiErrorResponseEnum.getStatus().name();
        this.message = apiErrorResponseEnum.getMessage();
    }

    private void messageGeneralException(Integer code, String message, ApiErrorResponseEnum apiErrorResponseEnum) {
        this.code = code;
        this.status = apiErrorResponseEnum.getStatus().name();
        this.message = message;
    }

}
