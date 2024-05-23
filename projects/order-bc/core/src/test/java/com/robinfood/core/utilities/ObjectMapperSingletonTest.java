package com.robinfood.core.utilities;

import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.entities.DeliveryTypeEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.robinfood.core.utilities.ObjectMapperSingleton.*;

class ObjectMapperSingletonTest {

    @Test
    void test_JsonToClas_Should_Error() {
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

        Assertions.assertThrows(GenericOrderBcException.class, () -> {
            jsonToClass(json, OrderDTO.class);
        });
    }

    @Test
    void test_JsonToClass_When_DataCorrect_Should_OK() {
        String json = "{\n" +
                "\t\"description\": \"description\",\n" +
                "\t\"id\": 1\n" +
                "}";

        DeliveryTypeEntity deliveryTypeEntity = jsonToClass(json, DeliveryTypeEntity.class);
        Assertions.assertNotNull(deliveryTypeEntity);
    }


    @Test
    void test_ObjectToClassConvertValue() {

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

        Assertions.assertThrows(GenericOrderBcException.class, () -> {
            objectToClassConvertValue(json, OrderDTO.class);
        });
    }

    @Test
    void test_ObjectToClassConvertValue_When_DataCorrect_Should_OK() {

        DeliveryTypeEntity deliveryTypeEntity = new DeliveryTypeEntity();

        deliveryTypeEntity.setId(1L);
        deliveryTypeEntity.setDescription("Description");

        DeliveryTypeEntity deliveryTypeEntityConvertValue =
                objectToClassConvertValue(deliveryTypeEntity, DeliveryTypeEntity.class);
        
        Assertions.assertNotNull(deliveryTypeEntityConvertValue);
    }
}
