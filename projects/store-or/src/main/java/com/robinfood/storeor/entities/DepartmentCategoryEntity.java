package com.robinfood.storeor.entities;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Builder
@Data
public class DepartmentCategoryEntity {

    private final List<CategoryTaxesEntity> categoryTaxes;

    private final String code;

    private final Long familyId;

    private final String name;
}
