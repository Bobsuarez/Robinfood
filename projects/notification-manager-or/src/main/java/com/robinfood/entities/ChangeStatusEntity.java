package com.robinfood.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ChangeStatusEntity {

    private Long channelId;
    private String couponReference;
    private String eventId;
    private String dateTime;
    private String integrationId;
    private Long orderId;
    private String orderUid;
    private String orderUuid;
    private String statusCode;
    private Long transactionId;
    private String transactionUuid;
}
