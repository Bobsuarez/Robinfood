package com.robinfood.storeor.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TreasureDepartmentCategoryProductEntity {

    private final Long id;

    private final Long productId;

    private final TreasuryDepartmentCategoryProductPropertiesEntity properties;

    private final Long treasuryDepartmentCategoryId;

    private final Long treasuryDepartmentId;
}
