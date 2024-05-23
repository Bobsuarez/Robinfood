package com.robinfood.mappers;

import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.dtos.response.ResponseDTO;

import static com.robinfood.constants.Constants.DEFAULT_BOOLEAN_TRUE;

public final class ResponseDTOMapper {

    private ResponseDTOMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ResponseDTO getResponseSecurityError(String message) {

        return ResponseDTO.builder()
                .code(HttpStatusConstant.SC_UNAUTHORIZED.getCodeHttp())
                .message(message)
                .error(DEFAULT_BOOLEAN_TRUE)
                .build();
    }

    public static ResponseDTO getResponseInternalServerError(String message) {

        return ResponseDTO.builder()
                .code(HttpStatusConstant.SC_INTERNAL_SERVER_ERROR.getCodeHttp())
                .message(message)
                .error(DEFAULT_BOOLEAN_TRUE)
                .build();
    }

    public static ResponseDTO buildDefault(Integer code, Object data, String message) {

        return ResponseDTO.builder()
                .code(code)
                .data(data)
                .message(message)
                .build();
    }

    public static ResponseDTO buildWithError(Integer code, String message, Boolean error) {

        return ResponseDTO.builder()
                .code(code)
                .error(error)
                .message(message)
                .build();
    }
}
