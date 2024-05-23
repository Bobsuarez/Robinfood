package com.robinfood.core.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class PaymentMethodEntityDTO {

    private Long id;

    private Integer typePaymentMethodId;

    private Integer orderFlowPrintId;

    private Integer statusId;

    private String name;

    private String nameShort;

    private String icon;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
