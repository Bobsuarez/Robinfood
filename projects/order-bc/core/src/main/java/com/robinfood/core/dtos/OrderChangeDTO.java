package com.robinfood.core.dtos;

import lombok.Data;

@Data
public class OrderChangeDTO {
    private Long orderId;
    private String code;
}
