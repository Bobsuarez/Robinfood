package com.robinfood.localprinterbc.dtos.printingtemplate;

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
