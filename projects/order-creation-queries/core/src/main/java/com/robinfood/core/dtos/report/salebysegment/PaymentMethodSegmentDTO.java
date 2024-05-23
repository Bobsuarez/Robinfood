package com.robinfood.core.dtos.report.salebysegment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PaymentMethodSegmentDTO {

    private final Long id;

    private final String name;

    private final OrdersSalesSegmentDTO orders;

}
