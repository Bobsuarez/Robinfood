package com.robinfood.core.entities.changestatusordersrequestentities;

import lombok.Data;

@Data
public class OrderStatusRequestEntity {

    private final String notes;

    private final Long orderId;

    private final Long statusId;

    private final Long userId;
}
