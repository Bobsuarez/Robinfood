package com.robinfood.core.entities;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PortionEntity {

    private final ReplacementPortionEntity changedTo;

    private final Integer free;

    private final Long id;

    private final Boolean included;

    private final String name;

    private final BigDecimal price;

    private final Long unitNumber;

    private final Integer quantity;

    private final String sku;

}
