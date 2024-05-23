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
public class DepartmentCategoryEntity {

    private List<CategoryTaxEntity> categoryTaxes;

    private String code;

    private Long familyId;

    private String name;
}
