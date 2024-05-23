package com.robinfood.core.dtos.statusorderhistory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StatusOrderHistoryDTO {

    private final String createdAt;

    private final Long id;

    private final String observation;

    private OrderUserDataDTO orderUserData;

    private final OrderStatusDTO status;

    private final Long userId;
}
