package com.robinfood.localserver.commons.entities.storeconfiguration.treasurydepartment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TreasuryDepartmentCategoryProductPropertiesEntity {

    private String code;

    private Long id;

    private Boolean status;

    private String value;
}
