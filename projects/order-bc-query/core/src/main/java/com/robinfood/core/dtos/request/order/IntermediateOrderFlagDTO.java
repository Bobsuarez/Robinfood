package com.robinfood.core.dtos.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class IntermediateOrderFlagDTO {

    private String flag;

    private Long id;

    private Long orderId;
}
