package com.robinfood.configurations.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JsonUtilsTest {

    @Mock
    private ObjectMapper objectMapper;

    private static final String json = "{\"code\":200,\"message\":\"Test\",\"error\":null,\"data\":null,\"metaData\":null}";
    private ApiResponseDTO dto;

    @BeforeEach
    public void setUp() {
        dto = new ApiResponseDTO();
        dto.setCode(200);
        dto.setMessage("Test");
        dto.setError(null);
        dto.setData(null);
        dto.setMetaData(null);
    }

    @Test
    public void test_ConvertToJson_Should_ReturnJsonString_When_ObjectIsPassed() {
        String currentJson = JsonUtils.convertToJson(dto);
        Assertions.assertNotNull(currentJson);
        Assertions.assertEquals(currentJson, json);
    }

    @Test
    public void test_ConvertToJson_Should_ReturnJsonObject_When_ObjectIsPassed() {
        String expectedJson = String.join("", "[", json, ", ", json, "]");
        ApiResponseDTO[] dtoList = new ApiResponseDTO[]{dto, dto};
        String currentJson = JsonUtils.convertToJson(dtoList);
        Assertions.assertNotNull(currentJson);
        Assertions.assertEquals(currentJson, expectedJson);
    }

}