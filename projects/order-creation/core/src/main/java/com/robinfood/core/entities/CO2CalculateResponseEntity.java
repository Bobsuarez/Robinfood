package com.robinfood.core.entities;

import com.robinfood.core.entities.co2.CO2PortionEntity;
import com.robinfood.core.entities.co2.CO2RemovedPortionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ToString
public class CO2CalculateResponseEntity {

    private BigDecimal co2TotalValue;

    private List<Product> products;

    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    @ToString
    public static class Product {
        private Long id;

        private String sku;

        private Integer quantity;

        private BigDecimal co2UnitValue;

        private BigDecimal co2TotalValue;

        private List<CO2PortionEntity> portions;

        private List<CO2RemovedPortionEntity> removedPortions;
    }
}
