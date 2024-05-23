package com.robinfood.core.dtos.report.salebystore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PaymentMethodsOfStoreDTO {

    @JsonProperty("id")
    public Long paymentMethodId;

    @JsonProperty("orders")
    public CountAndValueByOrdersDTO orders;

}
