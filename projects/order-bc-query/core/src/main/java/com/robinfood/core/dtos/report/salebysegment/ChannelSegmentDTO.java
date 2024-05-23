package com.robinfood.core.dtos.report.salebysegment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ChannelSegmentDTO {

    @JsonProperty("id")
    public Long idChannel;

    @JsonProperty("orders")
    public OrdersSalesSegmentDTO ordersSalesDTO;

}
