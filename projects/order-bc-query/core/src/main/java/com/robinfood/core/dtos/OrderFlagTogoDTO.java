package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderFlagTogoDTO {

    private Long id;

    private Long orderId;

    private Long statusId;
}
