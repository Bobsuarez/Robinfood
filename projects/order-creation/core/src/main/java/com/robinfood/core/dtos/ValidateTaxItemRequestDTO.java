package com.robinfood.core.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateTaxItemRequestDTO  implements Serializable {

    private  Long articleId;

    private  Long articleTypeId;

    private  BigDecimal discount;

    private  BigDecimal price;

    private  Integer quantity;
}
