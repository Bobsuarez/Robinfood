package com.robinfood.orderorlocalserver.dtos.printingtemplate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TransformRulesDTO {

    private String description;

    private String name;

    private String params;

}
