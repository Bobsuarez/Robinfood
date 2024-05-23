package com.robinfood.core.dtos.report.salebysegment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ChannelSegmentDTO {

    private final Long id;

    private final OrdersSalesSegmentDTO orders;
}
