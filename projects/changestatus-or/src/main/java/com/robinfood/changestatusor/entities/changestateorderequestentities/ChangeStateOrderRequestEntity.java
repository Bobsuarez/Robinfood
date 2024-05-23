package com.robinfood.changestatusor.entities.changestateorderequestentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeStateOrderRequestEntity {

    private Long channelId;

    private String brandId;

    private String deliveryIntegrationId;

    private String notes;

    private Long orderId;

    private String orderUid;

    private String orderUuid;

    private String origin;

    private String statusCode;

    private Long statusId;

    private Long transactionId;

    private Long userId;

    private String transactionUuid;
}
