package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import static com.robinfood.core.constants.APIConstants.DEFAULT_LOCALE;
import static com.robinfood.core.constants.APIConstants.DEFAULT_MESSAGE;

@AllArgsConstructor
@Getter
@Setter
public class ApiResponseDTO<T> {

    @Schema(example = "200", description = "The code of the HTTP response")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer code;

    @Schema(example = "'data': {}", description = "The content of the HTTP response")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    @Schema(example = "CO", description = "The locale of the response")
    private String locale = DEFAULT_LOCALE;

    @Schema(example = "OK", description = "The message of the HTTP response")
    private String message = DEFAULT_MESSAGE;

    @Schema(example = "HttpStatus", description = "The HTTP Status")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HttpStatus status;

    public ApiResponseDTO(T data) {
        this.data = data;
    }

    public ApiResponseDTO(
        T data,
        HttpStatus status
    ) {
        this.code = status.value();
        this.data = data;
        this.status = status;
    }

    public ApiResponseDTO(
        String message,
        HttpStatus status
    ) {
        this.code = status.value();
        this.data = null;
        this.message = message;
        this.status = status;
    }

    public ApiResponseDTO(String message) {
        this.data = null;
        this.message = message;
    }
}
