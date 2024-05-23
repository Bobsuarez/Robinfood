package com.robinfood.localserver.commons.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderDetailChangedPortionDTO {

    private Long id;

    private String name;

    private Long parentId;

    private String sku;

    private Long unitId;

    private Double unitNumber;
}
