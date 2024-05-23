package com.robinfood.core.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderStateDTO {

    private String code;

    private Long id;

    private Long idUser;

    private String name;

    private BigDecimal order;

    private String orderUuid;

    private Long orderId;
    private String notes;

    private OrderStateDTO subState;

    private String transactionUuid;

    private Long transactionId;

}
