package com.robinfood.customersbc.thirdparties.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;
import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO.ErrorDTO;
import com.robinfood.customersbc.thirdparties.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public final class ResponseUtils {

    private ResponseUtils() {}

    public static <T> DataBuffer buildDataBufferResponse(
        ServerHttpResponse response,
        ResponseDTO<T> responseDTO
    ) {
        final ObjectMapper objectMapper = new ObjectMapper();
        DataBufferFactory bufferFactory = response.bufferFactory();
        try {
            return bufferFactory.wrap(objectMapper.writeValueAsBytes(responseDTO));
        } catch (JsonProcessingException ex) {
            log.error("Error writing response", ex);
            return bufferFactory.wrap(new byte[0]);
        }
    }

    public static <T> ResponseDTO<T> getResponseDTO(
        final T object,
        final ResponseCode responseCode
    ) {
        return ResponseDTO
            .<T>builder()
            .data(object)
            .code(responseCode.getCode())
            .message(responseCode.getMessage())
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

    public static ErrorDTO getErrorDTOFromMap(
        final String type,
        final Map<String, String> messageMap
    ) {
        return ErrorDTO.builder()
            .type(type)
            .details(List.of(messageMap))
            .build();
    }
}
