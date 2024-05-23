package com.robinfood.core.dtos.orderhistory.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class OrderHistoryOrigenDTO {

    private final Long id;

    private final String name;

}
