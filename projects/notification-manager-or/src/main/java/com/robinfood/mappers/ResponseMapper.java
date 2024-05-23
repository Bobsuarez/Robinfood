package com.robinfood.mappers;

import com.robinfood.dtos.response.ResponseDTO;

public final class ResponseMapper {

    private ResponseMapper() {
        throw new IllegalStateException("Utility class");
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
