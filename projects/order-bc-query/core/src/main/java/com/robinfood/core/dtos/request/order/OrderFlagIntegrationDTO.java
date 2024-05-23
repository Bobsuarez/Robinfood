package com.robinfood.core.dtos.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderFlagIntegrationDTO {

    private Long id;

    private Long orderId;
}
