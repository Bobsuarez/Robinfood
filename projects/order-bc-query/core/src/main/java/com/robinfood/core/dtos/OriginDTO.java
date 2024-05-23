package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class OriginDTO {

    private final Long id;
    
    private final String name;
}
