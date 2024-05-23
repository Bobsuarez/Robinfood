package com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TreasureDepartmentCategoryProductDTO {

    private Long id;

    private Long productId;

    private TreasuryDepartmentCategoryProductPropertiesDTO properties;

    private Long treasuryDepartmentCategoryId;

    private Long treasuryDepartmentId;
}
