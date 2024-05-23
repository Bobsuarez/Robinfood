package com.robinfood.changestatusor.entities.changestateorderequestentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeStateOrderRespondEntity {

    private String brandId;

    private Long channelId;

    private String deliveryIntegrationId;

    private String notes;

    private Long orderId;

    private String orderUid;

    private String orderUuid;

    private String origin;

    private String statusCode;

    private Long statusId;

    private Long transactionId;

    private String transactionUuid;

    private Long userId;

}
