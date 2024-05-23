package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetOrderDetailFinalProductGroupDTO {

    @JsonIgnore
    private final Long finalProductId;

    private final Long id;

    private final String name;

    @JsonIgnore
    private final Long orderId;

    private final List<GetOrderDetailFinalProductPortionDTO> portions;

    private final List<OrderDetailRemovedPortionDTO> removedPortions;

    private final String sku;

}
