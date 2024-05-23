package com.robinfood.core.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FoodCoinsEntityRequestEntity {

    private Long id;

    private String reference;

    private Long source;
}
