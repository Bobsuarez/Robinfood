package com.robinfood.core.dtos.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderFlagTogoDTO {

    private Long id;

    private Boolean isActive;

    private Long orderId;

    private Long statusId;
}
