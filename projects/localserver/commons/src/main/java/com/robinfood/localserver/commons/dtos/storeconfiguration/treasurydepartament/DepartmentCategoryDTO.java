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
public class DepartmentCategoryDTO {

    private List<CategoryTaxDTO> categoryTaxes;

    private String code;

    private Long familyId;

    private String name;
}
