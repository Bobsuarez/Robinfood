package com.robinfood.core.dtos.report.salebysegment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrdersSalesSegmentDTO {

    @JsonProperty("salesAWeekBefore")
    public DataGenericSegmentDTO salesAWeekBefore;

    @JsonProperty("salesCurrent")
    public DataGenericSegmentDTO salesCurrent;

}
