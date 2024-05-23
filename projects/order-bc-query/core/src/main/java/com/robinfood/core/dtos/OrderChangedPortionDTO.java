package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class OrderChangedPortionDTO {

    private final Long changedPortionId;

    private final String changedPortionName;

    private final Long changedProductId;

    private final String changedProductName;

    private final LocalDateTime createdAt;

    private final Long id;

    private final Long orderId;

    private final Long orderFinalProductPortionId;

    private final Long originalPortionId;

    private final String originalPortionName;

    private final Long originalProductId;

    private final String originalProductName;
}
