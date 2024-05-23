package com.robinfood.localserver.commons.entities.storeconfiguration.treasurydepartment;

import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.ParameterDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class EntityTreasuryDepartmentEntity {

    private String name;

    private List<ParameterDTO> parameters;
}
