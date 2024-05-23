package com.robinfood.core.entities.changestateorderequestentities;

import lombok.Data;

@Data
public class ChangeStateOrderRequestEntity {

    private String brandId;

    private String deliveryIntegrationId;

    private String notes;

    private Long orderId;

    private String orderUuid;

    private String origin;

    private String statusCode;

    private Long statusId;

    private Long userId;
}
