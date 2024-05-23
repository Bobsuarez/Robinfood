package com.robinfood.orderorlocalserver.dtos.orderqueue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderCreatedQueueDTO implements Serializable {

    private Long companyId;

    private BigDecimal discount;

    private Long flowId;

    private Long id;

    private Long invoice;

    private String orderDate;

    private  String orderUid;

    private String orderUuid;

    private String orderNumber;

    private Boolean paid;

    private StoreQueueDTO store;

    private BigDecimal subtotal;

    private String timeZone;

    private BigDecimal tax;

    private BigDecimal total;

    private Long transactionId;
}
