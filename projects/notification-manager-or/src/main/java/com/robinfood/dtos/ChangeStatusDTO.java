package com.robinfood.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ChangeStatusDTO {

    private Long channelId;

    private String couponReference;

    private String dateTime;

    private String integrationId;

    private String notes;

    private Long orderId;

    private Long orderUid;

    private String orderUuid;

    private String origin;

    private String statusCode;

    private Long statusId;

    private String transactionId;

    private String transactionUuid;

    private Long userId;
}
