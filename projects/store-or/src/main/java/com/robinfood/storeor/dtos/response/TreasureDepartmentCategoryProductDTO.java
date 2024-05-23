package com.robinfood.storeor.dtos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TreasureDepartmentCategoryProductDTO {

    private final Long id;

    private final Long productId;

    private final TreasuryDepartmentCategoryProductPropertiesDTO properties;

    private final Long treasuryDepartmentCategoryId;

    private final Long treasuryDepartmentId;
}
