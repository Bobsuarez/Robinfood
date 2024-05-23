package com.robinfood.core.dtos;

import lombok.Data;

@Data
public class DetailChangedPortionDTO {

    private final Long id;

    private final String name;

    private final Long productId;
}
