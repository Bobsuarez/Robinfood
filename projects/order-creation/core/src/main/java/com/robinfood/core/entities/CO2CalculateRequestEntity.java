package com.robinfood.core.entities;

import java.util.List;

import com.robinfood.core.entities.co2.CO2PortionEntity;
import com.robinfood.core.entities.co2.CO2RemovedPortionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class CO2CalculateRequestEntity {
    private List<Product> products;

    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    @ToString
    public static class Product {
        private Long id;

        private String sku;

        private Integer quantity;

        private List<CO2PortionEntity> portions;

        private List<CO2RemovedPortionEntity> removedPortions;
    }
}
