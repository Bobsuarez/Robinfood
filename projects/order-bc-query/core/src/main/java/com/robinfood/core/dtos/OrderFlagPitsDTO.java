package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderFlagPitsDTO {

    private String carPlate;

    private Long id;

    private Long orderId;
}
