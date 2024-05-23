package com.robinfood.localserver.commons.entities.storeconfiguration.treasurydepartment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TreasuryDepartmentEntity {

    private List<DepartmentCategoryEntity> departmentCategories;

    private List<EntityTreasuryDepartmentEntity> entities;

    private String name;

    private List<PaymentEntity> payments;

    private List<TreasureDepartmentCategoryProductEntity> products;

    private List<TaxEntity> taxes;

}
