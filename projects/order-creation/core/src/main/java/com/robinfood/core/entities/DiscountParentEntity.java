package com.robinfood.core.entities;

import java.util.List;
import lombok.Data;

@Data
public class DiscountParentEntity {

    private final List<DiscountContainerResponseEntity> entities;
}
