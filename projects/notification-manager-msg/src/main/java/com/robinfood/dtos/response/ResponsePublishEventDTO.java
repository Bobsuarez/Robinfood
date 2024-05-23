package com.robinfood.dtos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponsePublishEventDTO {

    public String brandId;

    public String notes;

    public String orderId;

    public String origin;

    public String statusCode;

    public Long userId;
}
