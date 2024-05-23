package com.robinfood.core.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FoodCoinsEntityRequestDTO {

    private Long id;

    private String reference;

    private Long source;
}
