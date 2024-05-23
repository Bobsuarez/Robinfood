package com.robinfood.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class RequestChangeOrderStatusDTO {

    private Long channelId;

    private String deliveryIntegrationId;

    private String notes;

    private String orderUuid;

    private String origin;

    private Long orderId;

    private String statusCode;

    private Long statusId;

    private Long storeId;

    private Long userId;
}
