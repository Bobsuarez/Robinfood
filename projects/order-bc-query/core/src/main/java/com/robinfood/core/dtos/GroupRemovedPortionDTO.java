package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class GroupRemovedPortionDTO {

    private final LocalDateTime createdAt;

    private final Long finalProductId;

    private final Long groupId;

    private final Long id;

    private final Long orderId;

    private final Long orderFinalProductId;

    private final Long portionId;

    private final String portionName;
}
