package com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TreasuryDepartmentCategoryProductPropertiesDTO {

    private String code;

    private Long id;

    private Boolean status;

    private String value;
}
