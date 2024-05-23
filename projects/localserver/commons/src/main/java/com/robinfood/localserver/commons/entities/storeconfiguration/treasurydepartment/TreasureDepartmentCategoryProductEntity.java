package com.robinfood.localserver.commons.entities.storeconfiguration.treasurydepartment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TreasureDepartmentCategoryProductEntity {

    private Long id;

    private Long productId;

    private TreasuryDepartmentCategoryProductPropertiesEntity properties;

    private Long treasuryDepartmentCategoryId;

    private Long treasuryDepartmentId;
}
