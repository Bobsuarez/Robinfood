package com.robinfood.core.dtos.transactionresponsedto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO implements Serializable {

    private static final long serialVersionUID = 1848835553142804410L;

    private BigDecimal discountPrice;

    private List<OrderDiscountResponseDTO> discounts;

    private Long id;

    private String orderInvoiceNumber;

    private String orderNumber;

    private OrderStatusResponseDTO status;

    private BigDecimal subtotal;

    private BigDecimal taxPrice;

    private BigDecimal total;

    private String uid;

    private String uuid;
}
