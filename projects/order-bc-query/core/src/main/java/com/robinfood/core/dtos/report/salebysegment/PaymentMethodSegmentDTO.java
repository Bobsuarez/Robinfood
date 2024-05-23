package com.robinfood.core.dtos.report.salebysegment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PaymentMethodSegmentDTO {

    @JsonProperty("id")
    public Long idPayment;

    @JsonProperty("orders")
    public OrdersSalesSegmentDTO ordersSalesDTO;

}
