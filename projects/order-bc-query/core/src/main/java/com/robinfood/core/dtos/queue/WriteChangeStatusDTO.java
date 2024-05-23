package com.robinfood.core.dtos.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ToString
public class WriteChangeStatusDTO {

    private String couponReference;

    private String dateTime;

    private String integrationId;

    private Long orderId;

    private String orderUid;

    private String orderUuid;

    private String statusCode;

    private Long transactionId;

    private String transactionUuid;
}
