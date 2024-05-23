package com.robinfood.core.entities.transactionrequestentities;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortionRequestEntity {

    private BigDecimal discount;

    private Integer free;

    private GroupRequestEntity group;

    private Long id;

    private String name;

    private BigDecimal price;

    private PortionProductEntity product;

    private Integer quantity;

    private ReplacementPortionRequestEntity replacementPortion;

    private String sku;

    private Long unitId;

    private Long unitNumber;
}
