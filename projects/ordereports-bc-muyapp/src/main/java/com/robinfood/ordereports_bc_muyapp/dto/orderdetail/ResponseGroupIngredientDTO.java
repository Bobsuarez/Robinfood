package com.robinfood.ordereports_bc_muyapp.dto.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGroupIngredientDTO {

    private Boolean addition;

    private Double discount;

    private Short free;

    private Long id;

    private String image;

    private String name;

    private Integer quantity;

    private Double value;
}
