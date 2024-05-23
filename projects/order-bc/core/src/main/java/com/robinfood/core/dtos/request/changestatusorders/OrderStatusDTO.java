package com.robinfood.core.dtos.request.changestatusorders;

import lombok.Data;

@Data
public class OrderStatusDTO {

    private final String notes;

    private final Long orderId;

    private final Long statusId;

    private final Long userId;
}
