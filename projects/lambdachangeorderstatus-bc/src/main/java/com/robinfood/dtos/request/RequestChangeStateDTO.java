package com.robinfood.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RequestChangeStateDTO {

    private String deliveryIntegrationId;

    private String notes;

    private Long orderId;

    private String orderUuid;

    private String origin;

    private String statusCode;

    private Long statusId;

    private Long userId;
}
