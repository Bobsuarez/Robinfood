package com.robinfood.configurationslocalserverbc.dtos.templateResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TransformRulesResponseDTO {

    private String description;

    private String name;

    private String params;

}
