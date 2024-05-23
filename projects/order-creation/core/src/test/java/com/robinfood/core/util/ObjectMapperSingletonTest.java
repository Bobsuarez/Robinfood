package com.robinfood.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.exceptions.AppException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static com.robinfood.core.util.ObjectMapperSingleton.jsonToClass;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToClassConvertValue;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ObjectMapperSingletonTest {

    @Test
    void test_When_objectToClassConvertValue_Should_AppException() {

        String json = "{\n" +
                "\t\"notes\": \"Order status change\",\n" +
                "\t\"orderId\": 5486836,\n" +
                "\t\"orderUid\": 5486835,\n" +
                "\t\"origin\": \"Payment-method queue\",\n" +
                "\t\"statusCode\": \"ORDER_PAID\",\n" +
                "\t\"statusId\": 2,\n" +
                "\t\"transactionUuid\": \"9a24d252-ab37-4bfa-bcde-6da48d7291dc\",\n" +
                "\t\"userId\": 1083202\n" +
                "}";

        Assertions.assertThrows(AppException.class, () -> {
            jsonToClass(json, OrderDTO.class);
        });
    }

    @Test
    void test_When_JsonToClass_Should_AppException() {

        String json = "{\n" +
                "\t\"notes\": \"Order status change\",\n" +
                "\t\"orderId\": 5486836,\n" +
                "\t\"orderUid\": 5486835,\n" +
                "\t\"origin\": \"Payment-method queue\",\n" +
                "\t\"statusCode\": \"ORDER_PAID\",\n" +
                "\t\"statusId\": 2,\n" +
                "\t\"transactionUuid\": \"9a24d252-ab37-4bfa-bcde-6da48d7291dc\",\n" +
                "\t\"userId\": 1083202\n" +
                "}";

        Assertions.assertThrows(AppException.class, () -> {
            objectToClassConvertValue(json, OrderDTO.class);
        });
    }

    @Test
    void test_When_objectToJson_Should_AppException() throws JsonProcessingException {

        ObjectMapper objectMapper = mock(ObjectMapper.class);
        JsonProcessingException exception = new JsonProcessingException("Error") {
        };

        doThrow(exception).when(objectMapper).writeValueAsString(any());

        String result = objectToJson(new Object());

        assertEquals("", result);
    }
}