package com.robinfood.core.dtos.orderstatushistory;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderStatusDTO {

    private String code;

    private Long id;
}
