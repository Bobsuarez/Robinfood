package com.robinfood.localserver.commons.dtos.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ChangeStatusRequestDTO {

    private Long id;

    private String locker;

    private String orderBrandId;

    @JsonProperty("order_number")
    private String orderNumber;

    private String orderUuid;

    @JsonProperty("origin_id")
    private Long originId;

    @JsonProperty("status_trace")
    private int statusTrace;

    private Long storeId;

    private String uid;
}
