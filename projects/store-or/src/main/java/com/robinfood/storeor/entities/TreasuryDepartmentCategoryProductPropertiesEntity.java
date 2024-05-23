package com.robinfood.storeor.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TreasuryDepartmentCategoryProductPropertiesEntity {

    private final String code;

    private final Long id;

    private final Boolean status;

    private final String value;
}
