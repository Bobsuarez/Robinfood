package com.robinfood.core.dtos.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PortionGroupDTO {

    private Long id;

    private String name;

    private String sku;
}
