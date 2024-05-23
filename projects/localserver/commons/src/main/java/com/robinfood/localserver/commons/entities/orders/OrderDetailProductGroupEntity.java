package com.robinfood.localserver.commons.entities.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderDetailProductGroupEntity {

    private Long id;

    private String name;

    private List<OrderDetailProductGroupPortionEntity> portions;

    private List<OrderDetailRemovedPortionEntity> removedPortions;

    private String sku;
}
