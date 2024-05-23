package com.robinfood.core.dtos.report.salebysegment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StoreSegmentDTO {

    @JsonProperty("id")
    public Long idStore;

    @JsonProperty("orders")
    public OrdersSalesSegmentDTO ordersSalesDTO;

}
