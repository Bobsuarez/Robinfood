package com.robinfood.ordereports.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GroupIngredientDTO implements Serializable {

    private Boolean addition;

    private Double discount;

    private Long free;

    private Long id;

    private String image;

    private String name;

    private Long quantity;

    private Double value;
}
