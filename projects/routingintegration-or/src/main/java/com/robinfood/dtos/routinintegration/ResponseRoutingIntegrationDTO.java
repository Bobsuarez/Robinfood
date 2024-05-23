package com.robinfood.dtos.routinintegration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ResponseRoutingIntegrationDTO {

    @JsonProperty("channel_id")
    public Long channelId;

    public String notes;

    public String orderId;

    public String orderUuid;

    public String origin;

    public String statusCode;

    public Long userId;
}
