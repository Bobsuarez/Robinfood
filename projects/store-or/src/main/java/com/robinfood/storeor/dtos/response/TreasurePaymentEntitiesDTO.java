package com.robinfood.storeor.dtos.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TreasurePaymentEntitiesDTO {

    private final List<TreasurePaymentEntityParametersDTO> parameters;

    private final Long paymentMethodId;
}
