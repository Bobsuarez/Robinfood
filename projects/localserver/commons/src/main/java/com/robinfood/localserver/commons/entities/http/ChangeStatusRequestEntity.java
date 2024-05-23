package com.robinfood.localserver.commons.entities.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ChangeStatusRequestEntity {

    private Long id;

    private String locker;

    private String orderBrandId;

    @JsonProperty("order_number")
    private String orderNumber;

    private String orderUuid;

    private Long originId;

    private Integer statusId;

    @JsonProperty("status_trace")
    private int statusTrace;

    private String uid;
}
