package com.robinfood.core.dtos.report.salebysegment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrdersDTOResponse {

    private final PercentageSalesDifferenceDTOResponse percentageSalesDifference;

    private final SalesAWeekBeforeDTOResponse salesAWeekBefore;

    private final SalesCurrentDTOResponse salesCurrent;

}
