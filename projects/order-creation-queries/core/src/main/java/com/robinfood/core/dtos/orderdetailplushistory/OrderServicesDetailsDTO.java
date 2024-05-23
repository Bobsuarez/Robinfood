package com.robinfood.core.dtos.orderdetailplushistory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderServicesDetailsDTO {

    private final Long id;

    private final String name;

    private final Integer quantity;

    private final Double unitPrice;
}
