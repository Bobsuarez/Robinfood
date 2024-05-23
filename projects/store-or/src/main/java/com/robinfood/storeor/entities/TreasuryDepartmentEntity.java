package com.robinfood.storeor.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class TreasuryDepartmentEntity {

    private final List<DepartmentCategoryEntity> departmentCategories;

    private final List<TreasureEntitiesEntity> entities;

    private String name;

    private final List<TreasureDepartmentPaymentEntity> payments;

    private final List<TreasureDepartmentCategoryProductEntity> products;

    private final List<TreasureDepartmentTaxesEntity> taxes;
}
