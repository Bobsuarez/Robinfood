package com.robinfood.core.dtos.report.salebysegment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrdersSalesSegmentDTO {

    private final DataGenericSegmentDTO salesAWeekBefore;

    private final DataGenericSegmentDTO salesCurrent;

}
