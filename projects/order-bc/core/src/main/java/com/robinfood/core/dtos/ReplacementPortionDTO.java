package com.robinfood.core.dtos;

import lombok.Data;

@Data
public class ReplacementPortionDTO {

    private final Long id;

    private final String name;

    private final PortionProductDTO product;
}
