package com.robinfood.taxes.domain;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxableItem {

    private Long productTypeId;

    private Long articleId;

    private BigDecimal price;

    private BigDecimal discount;

    private Integer quantity;

    private BigDecimal totalTax;

    private List<CalculatedTax> taxes;

}
