package com.robinfood.storeor.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TreasuryDepartmentCategoryProductPropertiesDTO {

    private final String code;

    private final Long id;

    private final Boolean status;

    private final String value;
}
