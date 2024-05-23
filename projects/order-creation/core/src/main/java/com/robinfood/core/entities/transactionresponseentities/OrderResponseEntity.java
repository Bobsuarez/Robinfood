package com.robinfood.core.entities.transactionresponseentities;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseEntity {

    private BigDecimal discountPrice;

    private List<OrderDiscountResponseEntity> discounts;

    private Long id;

    private String orderInvoiceNumber;

    private String orderNumber;

    private OrderStatusResponseEntity status;

    private BigDecimal subtotal;

    private BigDecimal taxPrice;

    private BigDecimal total;

    private String uid;

    private String uuid;
}
