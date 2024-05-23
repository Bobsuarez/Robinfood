package com.robinfood.core.dtos;


import com.robinfood.core.dtos.configuration.StoreDTO;
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

    private  Long id;

    private String orderDate;

    private Boolean paid;

    private Long transactionId;

    private  String orderUid;

    private String orderNumber;

    private Long flowId;

    private Long companyId;

    private String timeZone;

    private Long invoice;

    private StoreQueueDTO store;

    private BigDecimal subtotal;

    private BigDecimal tax;

    private BigDecimal discount;

    private BigDecimal total;

}
