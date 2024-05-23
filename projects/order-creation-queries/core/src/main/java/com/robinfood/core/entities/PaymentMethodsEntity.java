package com.robinfood.core.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentMethodsEntity {

    private String createdAt;

    private String icon;

    private Long id;

    private String name;

    private String nameShort;

    private Integer orderFlowPrintId;

    private Integer statusId;

    private Integer typePaymentMethodId;

    private String updatedAt;

}
