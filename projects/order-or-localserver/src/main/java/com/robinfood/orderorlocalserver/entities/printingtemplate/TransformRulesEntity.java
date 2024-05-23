package com.robinfood.orderorlocalserver.entities.printingtemplate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TransformRulesEntity {

    private String description;

    private String name;

    private String params;

}
