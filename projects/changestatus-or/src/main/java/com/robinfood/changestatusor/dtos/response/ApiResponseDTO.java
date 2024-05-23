package com.robinfood.changestatusor.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import static com.robinfood.changestatusor.constants.APIConstants.BAD_REQUEST;
import static com.robinfood.changestatusor.constants.APIConstants.BAD_REQUEST_MESSAGE;
import static com.robinfood.changestatusor.constants.APIConstants.DEFAULT_MESSAGE;
import static com.robinfood.changestatusor.constants.APIConstants.NOT_FOUND;
import static com.robinfood.changestatusor.constants.APIConstants.NOT_FOUND_CODE;
import static com.robinfood.changestatusor.constants.APIConstants.PRECONDITION_FAILED;
import static com.robinfood.changestatusor.constants.APIConstants.PRECONDITION_FAILED_CODE;
import static com.robinfood.changestatusor.constants.APIConstants.SUCCESS_CODE;
import static com.robinfood.changestatusor.constants.APIConstants.UNAUTHORIZED_CODE;
import static com.robinfood.changestatusor.constants.APIConstants.UNAUTHORIZED_MESSAGE;

@Getter
@Setter
public class ApiResponseDTO<T> {

    @Schema(example = "200", description = "The code of the HTTP response")
    private Integer code = SUCCESS_CODE;

    @Schema(example = "'data': {}", description = "The content of the HTTP response")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    @Schema(example = "OK", description = "The message of the HTTP response")
    private String message = DEFAULT_MESSAGE;

    public ApiResponseDTO(T data) {
        this.data = data;
    }

    public ApiResponseDTO(String message) {
        this.data = null;
        this.message = message;
    }

    public ApiResponseDTO(Integer code, String message, HttpStatus status) {

        this.code = code;
        this.data = null;
        this.message = message;
    }

    /**
     * Sets parameters for a UNAUTHORIZED response
     */
    public void badRequest() {
        setCode(BAD_REQUEST);
        if (message == null) {
            setMessage(BAD_REQUEST_MESSAGE);
        }
    }

    /**
     * Sets parameters for a PRECONDITION_FAILED response
     */
    public void preconditionFailed() {
        setCode(PRECONDITION_FAILED_CODE);
        if (message == null) {
            setMessage(PRECONDITION_FAILED);
        }
    }

    /**
     * Sets parameters for a UNAUTHORIZED response
     */
    public void unauthorized() {
        setCode(UNAUTHORIZED_CODE);
        if (message == null) {
            setMessage(UNAUTHORIZED_MESSAGE);
        }
    }

    /**
     * Sets parameters for a NOT_FOUND response
     */
    public void notFound() {
        setCode(NOT_FOUND_CODE);
        if (message == null) {
            setMessage(NOT_FOUND);
        }
    }
}
