package com.robinfood.core.dtos.menu;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class MenuSuggestedPortionResponseDTO {

    private final List<MenuSuggestedPortionDataDTO> changes;
    private final Long id;
    private final String image;
    private final String name;
    private final Long parentId;
    private final String sku;
}
