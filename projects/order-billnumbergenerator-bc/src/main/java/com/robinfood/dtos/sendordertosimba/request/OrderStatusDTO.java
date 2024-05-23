package com.robinfood.dtos.sendordertosimba.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderStatusDTO {

    private Long id;

    private String name;
}
