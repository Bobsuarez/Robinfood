package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuHallsDTO {

    private final Long id;

    private final List<MenuProductDTO> menuProductDTOList;

    private final String name;

    private final Long position;
}
