package com.robinfood.core.dtos.report.sale;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrdersSalesDTO {

    @JsonProperty("salesAWeekBefore")
    public SalesAWeekBeforeDTO salesAWeekBeforeDto;

    @JsonProperty("salesCurrent")
    public SalesCurrentDTO salesCurrentDto;

}
