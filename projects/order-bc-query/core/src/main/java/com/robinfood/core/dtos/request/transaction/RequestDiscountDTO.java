package com.robinfood.core.dtos.request.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestDiscountDTO {

    private Long id;

    private Long typeId;

    private Double value;
}
