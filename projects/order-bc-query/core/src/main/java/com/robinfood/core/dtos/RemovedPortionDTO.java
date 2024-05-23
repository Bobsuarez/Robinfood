package com.robinfood.core.dtos;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class RemovedPortionDTO {

    @Nullable
    private Long finalProductId;

    private final Long groupId;

    private final Long id;

    @Nullable
    private Long orderFinalProductId;

    @Nullable
    private Long orderId;

    private final String portionName;
}
