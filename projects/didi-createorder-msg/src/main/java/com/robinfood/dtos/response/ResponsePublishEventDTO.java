package com.robinfood.dtos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponsePublishEventDTO {

    private String brandId;

    private String notes;

    private String orderId;

    private String origin;

    private String statusCode;

    private Long userId;
}
