package com.robinfood.taxes.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductTaxData {
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal taxRate;
}
