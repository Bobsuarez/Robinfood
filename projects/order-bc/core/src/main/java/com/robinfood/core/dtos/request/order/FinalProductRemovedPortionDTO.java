package com.robinfood.core.dtos.request.order;

import lombok.Data;

@Data
public class FinalProductRemovedPortionDTO {

    private Long finalProductId;

    private final Long groupId;

    private final Long id;

    private Long orderFinalProductId;

    private Long orderId;

    private final String portionName;
}
