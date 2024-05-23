package com.robinfood.core.dtos.report.salebystore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class GetSaleByStoreResponseDTO {

    @JsonProperty("id")
    public Long storeId;

    @JsonProperty("paymentMethods")
    public List<PaymentMethodsOfStoreDTO> paymentMethods;

}
