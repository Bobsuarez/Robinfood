package com.robinfood.core.entities;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class FinalProductEntity {

    private final List<GroupEntity> groups;

    private final Long id;

    private final BigDecimal price;

    private final Integer quantity;

    private final String sku;

    private final String type;
}
