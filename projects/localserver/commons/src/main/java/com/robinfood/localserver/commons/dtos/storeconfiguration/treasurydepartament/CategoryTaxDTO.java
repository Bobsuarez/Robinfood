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
public class CategoryTaxDTO {

    private Long id;

    private Long taxTypeId;

    private String name;

    private List<ParameterDTO> parameters;
}
