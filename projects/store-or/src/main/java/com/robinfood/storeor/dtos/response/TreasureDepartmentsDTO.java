package com.robinfood.storeor.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class TreasureDepartmentsDTO {

    private List<DepartmentCategoryDTO> departmentCategories;

    private List<TreasureEntitiesDTO> entities;

    private String name;

    private List<TreasureDepartmentPaymentDTO> payments;

    private List<TreasureDepartmentCategoryProductDTO> products;

    private List<TreasureDepartmentTaxesDTO> taxes;
}
