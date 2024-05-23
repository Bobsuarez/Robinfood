package com.robinfood.configurationslocalserverbc.dtos.apiresponsebuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.robinfood.configurationslocalserverbc.enums.ApiResponseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.robinfood.configurationslocalserverbc.configs.constans.APIConstants.TIMEZONE_UTC_DEFAULT;

@ToString
@Getter
@Setter
public class APIResponseDTO<T> {

    @Schema(example = "200", description = "The code of the HTTP response")
    private Integer code;

    @Schema(example = "'data': {}", description = "The content of the HTTP response")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    @Schema(example = "CO", description = "The locale of the response UTC")
    private ZonedDateTime locale = ZonedDateTime.now(ZoneId.of(TIMEZONE_UTC_DEFAULT));

    @Schema(example = "OK", description = "The message of the HTTP response")
    private String message;

    @Schema(example = "HttpStatus", description = "The HTTP Status")
    private String status;

    public APIResponseDTO(T data, ApiResponseEnum apiResponseEnum) {
        this.data = data;
        this.message = apiResponseEnum.getMessage();
        this.code = apiResponseEnum.getCode();
        this.status = apiResponseEnum.getStatus();
    }
}
