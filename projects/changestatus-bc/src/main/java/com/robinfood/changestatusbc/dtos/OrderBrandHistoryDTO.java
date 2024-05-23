package com.robinfood.changestatusbc.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class OrderBrandHistoryDTO {

    private Long brandId;

    private Long orderId;

    private Long orderStatusId;

    private Long userId;
}
