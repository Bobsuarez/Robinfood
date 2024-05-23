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
public class EntityTreasuryDepartmentDTO {

    private String name;

    private List<ParameterDTO> parameters;
}
