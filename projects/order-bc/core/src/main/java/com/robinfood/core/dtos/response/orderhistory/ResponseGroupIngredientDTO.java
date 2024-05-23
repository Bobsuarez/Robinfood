package com.robinfood.core.dtos.response.orderhistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseGroupIngredientDTO {

    private Long id;

    private String image;

    private String name;

    private Double value;

}
