package com.robinfood.storeor.dtos.response;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Builder
@Data
public class DepartmentCategoryDTO {

    private List<CategoryTaxesDTO> categoryTaxes;

    private String code;

    private Long familyId;

    private String name;
}
