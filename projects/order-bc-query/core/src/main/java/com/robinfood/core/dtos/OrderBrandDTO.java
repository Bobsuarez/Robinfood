package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class OrderBrandDTO {

    private Long id;

    private String name;
}
