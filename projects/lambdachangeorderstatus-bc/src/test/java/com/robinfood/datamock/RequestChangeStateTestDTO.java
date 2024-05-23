package com.robinfood.datamock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RequestChangeStateTestDTO {

    private String deliveryIntegrationId;

    private String notes;

    private Long orderId;

    private String orderUuid;

    private String origin;

    private String statusCode;

    private Long statusId;

    private Long storeId;

    private Long userId;
}