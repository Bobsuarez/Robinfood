package com.robinfood.orderorlocalserver.dtos.orderdetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailProductGroupDTO {

    private final Long id;

    private final String name;

    private final List<OrderDetailProductGroupPortionDTO> portions;

    private final List<OrderDetailRemovedPortionDTO> removedPortions;

    private String sku;

    public boolean hasReplacement() {
        return portions.stream().anyMatch(OrderDetailProductGroupPortionDTO::hasReplacement);
    }
}
