package com.robinfood.core.entities.orderhistory.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class OrderHistoryOriginEntity {

    private final Long id;

    private final String name;

}
