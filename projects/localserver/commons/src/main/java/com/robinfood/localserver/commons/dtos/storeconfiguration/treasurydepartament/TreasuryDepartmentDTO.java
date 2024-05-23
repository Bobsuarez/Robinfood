package com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TreasuryDepartmentDTO {

    private List<DepartmentCategoryDTO> departmentCategories;

    private List<EntityTreasuryDepartmentDTO> entities;

    private String name;

    private List<PaymentDTO> payments;

    private List<TreasureDepartmentCategoryProductDTO> products;

    private List<TaxDTO> taxes;
}
