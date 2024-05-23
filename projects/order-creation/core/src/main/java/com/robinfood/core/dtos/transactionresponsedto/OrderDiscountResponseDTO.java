package com.robinfood.core.dtos.transactionresponsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderDiscountResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long externalDiscount;

    private Long finalProductId;

    private Long id;

    private BigDecimal value;
}
