package com.robinfood.core.entities.services;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OriginEntity {

    private final String color;

    private final Long id;
    
    private final String name;
}
