package com.robinfood.core.dtos.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuProductGroupDTO {

    private final Integer free;
    private final Long groupTypeId;
    private final Long id;
    private final Integer max;
    private final Integer min;
    private final String namePlural;
    private final String nameSingular;
    private final List<MenuGroupPortionDTO> portions;
    private final String selectionNamePlural;
    private final String selectionNameSingular;
    private final String sku;
    private final Integer subsidy;
}
