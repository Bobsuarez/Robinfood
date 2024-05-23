package com.robinfood.core.dtos.report.salebystore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.core.dtos.report.salebysegment.DataGenericSegmentDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CountAndValueByOrdersDTO {

    @JsonProperty("salesAWeekBefore")
    public DataGenericSegmentDTO salesAWeekBefore;

    @JsonProperty("salesCurrent")
    public DataGenericSegmentDTO salesCurrent;

}
