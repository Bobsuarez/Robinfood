package com.robinfood.core.dtos.response.order;

import com.robinfood.core.dtos.OrderStatusDTO;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseCreatedOrderDTO {

    private Double discountPrice;

    private List<ResponseOrderDiscountDTO> discounts;

    private Long id;

    private String orderNumber;

    private String orderInvoiceNumber;

    private OrderStatusDTO status;

    private Double subtotal;

    private Double taxPrice;

    private BigDecimal co2Total;

    private Double total;

    private String uid;

    private String uuid;
}
