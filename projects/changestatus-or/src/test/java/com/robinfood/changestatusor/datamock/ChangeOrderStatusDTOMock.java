package com.robinfood.changestatusor.datamock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.changestatusor.dtos.ChangeOrderStatusDTO;

public class ChangeOrderStatusDTOMock {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String json  = "{\n" +
            "  \"channelId\": 1,\n" +
            "  \"deliveryIntegrationId\": \"DEL-001\",\n" +
            "  \"notes\": \"This is a test order\",\n" +
            "  \"orderId\": 123,\n" +
            "  \"orderUid\": \"ORDER-001\",\n" +
            "  \"orderUuid\": \"123e4567-e89b-12d3-a456-426614174001\",\n" +
            "  \"origin\": \"web\",\n" +
            "  \"statusCode\": \"PROCESSING\",\n" +
            "  \"statusId\": 1,\n" +
            "  \"transactionId\": 456,\n" +
            "  \"userId\": 789,\n" +
            "  \"uuid\": 789,\n" +
            "  \"transactionUuid\": \"789e0123-45ab-67cd-89ef-0123456789ab\"\n" +
            "}";

    public static ChangeOrderStatusDTO getDefaultData() throws JsonProcessingException {
        return  objectMapper.readValue(json, ChangeOrderStatusDTO.class);
    }


    public static ChangeOrderStatusDTO getDefault(){

        return ChangeOrderStatusDTO.builder()
                .brandId("27")
                .channelId(10L)
                .deliveryIntegrationId("1")
                .notes("Test")
                .orderId(123456L)
                .orderUid("dlknzvlasknir4bnscbs")
                .orderUuid("dlknzvlasknir4bnscbs")
                .origin("local server")
                .statusCode("ORDER_IN_PROGESS")
                .statusId(2L)
                .transactionId(3L)
                .transactionUuid("dlknzvlasknir4bnscbs")
                .userId(1L)
                .build();
    }
}
