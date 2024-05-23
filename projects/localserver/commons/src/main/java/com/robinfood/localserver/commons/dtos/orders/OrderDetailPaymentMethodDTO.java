package com.robinfood.localserver.commons.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderDetailPaymentMethodDTO {

    private Double discount;

    private Long id;

    private String name;

    private Long originId;

    private Double subtotal;

    private Double tax;

    private BigDecimal value;
}
