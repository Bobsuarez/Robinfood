package com.robinfood.core.dtos.salesstore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class SalesStoresDTO {

    public Long id;

    public List<SalesPaymentMethodDTO> paymentMethods;
}
