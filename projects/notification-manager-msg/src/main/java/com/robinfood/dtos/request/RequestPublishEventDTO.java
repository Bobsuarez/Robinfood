package com.robinfood.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RequestPublishEventDTO {

    private String couponReference;

    private String dateTime;

    private String eventId;

    private Object integrationId;

    private Long orderId;

    private String orderUid;

    private String orderUuid;

    private String statusCode;

    private Long transactionId;

    private String transactionUuid;

}
