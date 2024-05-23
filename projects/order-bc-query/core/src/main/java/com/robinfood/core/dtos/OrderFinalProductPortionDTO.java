package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class OrderFinalProductPortionDTO {
    
    private final Boolean addition;

    private final Double basePrice;

    private final Long companyId;

    private final Boolean changedProduct;

    private final LocalDateTime createdAt;

    private final Long dicUnitId;

    private final Double discount;

    private final Integer effectiveSale;

    private final Long groupId;

    private final String groupName;

    private final Long id;

    private final String operationDate;

    private final Long orderId;

    private final Long orderFinalProductId;

    private final Long portionId;

    private final String portionName;

    private final Integer portionStatus;

    private final Long productId;

    private final String productName;

    private final Integer quantity;

    private final Long storeId;

    private final Long unitsNumber;

    private final LocalDateTime updatedAt;
}
