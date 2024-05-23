package com.robinfood.app.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.core.dtos.DataElectronicBillingDTO;
import com.robinfood.core.dtos.OrderElectronicBillingDTO;
import com.robinfood.core.entities.OrderElectronicBillingEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetOrderElectronicBillingMapper {
    public static OrderElectronicBillingDTO orderElectronicBillingEntityToOrderElectronicBillingDTO(
            OrderElectronicBillingEntity orderElectronicBillingEntity
    ) {

        return OrderElectronicBillingDTO.builder()
                .createdAt(orderElectronicBillingEntity.getCreatedAt())
                .id(orderElectronicBillingEntity.getId())
                .orderId(orderElectronicBillingEntity.getOrderId())
                .responsePayload(orderElectronicBillingEntity.getResponsePayload())
                .requestPayload(orderElectronicBillingEntity.getRequestPayload())
                .statusCode(orderElectronicBillingEntity.getStatusCode())
                .storeId(orderElectronicBillingEntity.getStoreId())
                .storeName(orderElectronicBillingEntity.getStoreName())
                .updatedAt(orderElectronicBillingEntity.getUpdatedAt()).build();
    }

    public static DataElectronicBillingDTO orderElectronicBillingEntityToDataElectronicBillingDTO(
            String responsePayload
    ) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(responsePayload, DataElectronicBillingDTO.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
