package com.robinfood.core.entities.menuvalidationentities;

import com.robinfood.core.entities.GroupEntity;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class MenuValidationProductEntity {

    private final Long brandId;

    private final BigDecimal discount;

    private final List<GroupEntity> groups;

    private final Long id;

    private final Long articleId;

    private final BigDecimal price;

    private final Integer quantity;

    private final String sku;

    private final BigDecimal subtotal;

    private final BigDecimal tax;

    private final Integer taxType;

    private final String type;
}
