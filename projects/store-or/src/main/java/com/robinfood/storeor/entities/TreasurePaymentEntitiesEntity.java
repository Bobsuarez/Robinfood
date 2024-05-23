package com.robinfood.storeor.entities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TreasurePaymentEntitiesEntity {

    private final List<TreasurePaymentEntityParametersEntity> parameters;

    private final Long paymentMethodId;
}
