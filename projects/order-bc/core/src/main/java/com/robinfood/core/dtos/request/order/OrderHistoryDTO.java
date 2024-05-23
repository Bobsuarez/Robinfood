package com.robinfood.core.dtos.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderHistoryDTO {

    private String observation;

    private Long orderId;

    private Long orderStatusId;

    private Long userId;

}
