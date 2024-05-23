package com.robinfood.core.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OriginDTO {

    private final String color;

    private final Long id;
    
    private final String name;
}
