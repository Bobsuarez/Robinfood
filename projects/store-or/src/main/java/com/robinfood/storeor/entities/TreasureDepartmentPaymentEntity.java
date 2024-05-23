package com.robinfood.storeor.entities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TreasureDepartmentPaymentEntity {

    private List<TreasurePaymentEntitiesEntity> entities;

    private final Long id;

    private final String name;
}
