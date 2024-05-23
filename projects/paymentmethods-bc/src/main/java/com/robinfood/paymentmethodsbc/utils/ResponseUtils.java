package com.robinfood.paymentmethodsbc.utils;

import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO.ErrorDTO;
import com.robinfood.paymentmethodsbc.enums.ResponseCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ResponseUtils {

    private ResponseUtils() {}

    public static ErrorDTO getErrorDTOMap(
        String type,
        Map<String, String> value
    ) {
        HashMap<String, String> errorsMap = new HashMap<>(value);

        return ErrorDTO.builder()
            .type(type)
            .details(List.of(errorsMap))
            .build();
    }

    public static ErrorDTO getErrorDTOMap(
        String type,
        String key,
        String value
    ) {
        HashMap<String, String> errorsMap = new HashMap<>();
        errorsMap.put(key, value);

        return ErrorDTO.builder()
            .type(type)
            .details(List.of(errorsMap))
            .build();
    }

    public static ResponseDTO<Object> getExceptionResponseResultDTO(
        ResponseCode responseCode,
        String message,
        String key,
        String errorValue
    ) {
        return ResponseDTO.builder()
            .code(responseCode.getCode())
            .message(message)
            .error(getErrorDTOMap(responseCode.toString(), key, errorValue))
            .build();
    }

    public static ResponseDTO<Object> getResponseDTO(
        ResponseCode responseCode
    ) {
        return ResponseDTO.builder()
            .code(responseCode.getCode())
            .message(responseCode.getMessage())
            .build();
    }
}
