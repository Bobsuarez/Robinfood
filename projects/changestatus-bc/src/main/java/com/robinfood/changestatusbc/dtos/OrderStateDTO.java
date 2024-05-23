package com.robinfood.changestatusbc.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderStateDTO {

    private String code;

    private Long id;

    private Long idUser;

    //TODO Eliminate this functionality when all channels are communicating through the uuid
    // this is removed at the same time from class (ChangeOrderStateUseCase)
    // function (sendOrderStatusChangedWhenOriginIsSelfManagementAndOrderIsPaid)
    private Boolean isPaid;

    private String name;

    private BigDecimal order;

    private String orderUuid;

    private Long orderId;

    //TODO Eliminate this functionality when all channels are communicating through the uuid
    // this is removed at the same time from class (ChangeOrderStateUseCase)
    // function (sendOrderStatusChangedWhenOriginIsSelfManagementAndOrderIsPaid)
    private Long originId;

    private String notes;

    private OrderStateDTO subState;

    private String transactionUuid;

    private Long transactionId;
}
