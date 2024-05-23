package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderFlagDTO {

    private String flag;

    private Long id;

    private Long orderId;
}
