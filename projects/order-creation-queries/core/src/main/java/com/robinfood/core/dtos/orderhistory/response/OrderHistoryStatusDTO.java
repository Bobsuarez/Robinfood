package com.robinfood.core.dtos.orderhistory.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class OrderHistoryStatusDTO {

    private final Long id;

    private final String name;

}
