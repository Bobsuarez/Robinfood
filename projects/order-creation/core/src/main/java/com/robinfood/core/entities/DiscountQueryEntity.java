package com.robinfood.core.entities;

import java.util.List;
import lombok.Data;

@Data
public class DiscountQueryEntity {

    private final Object criteriaInfo;

    private final List<DiscountContainerRequestEntity> entities;
}
