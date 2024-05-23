package com.robinfood.core.dtos.orderstatushistory;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderStatusHistoryDTO {

    private String createdAt;

    private Long id;

    private String observation;

    private Long userId;

    private OrderStatusDTO status;
}
