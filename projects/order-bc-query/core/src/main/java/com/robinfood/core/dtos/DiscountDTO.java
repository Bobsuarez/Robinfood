package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiscountDTO {

    private Long id;

    private Long typeId;

    private Double value;
}
