package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "order_detail")
public class OrderDetailEntity {

    private Double additionalProductsPrice;

    private Double basePriceNt;

    private Double baseTaxPrice;

    private Long billingResolutionId;

    private LocalDateTime createdAt;

    private Double consumptionValue;

    private Double discountPrice;

    private BigDecimal finalProductsPrice;

    private Boolean hasConsumption;

    private String invoice;

    private Long menuId;

    private String notes;

    @Id
    private Long orderId;

    private Double servicesPrice;

    private Double servicePriceNt;

    private Double serviceTaxPrice;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onInsert() {
        createdAt = LocalDateTime.now(ZoneOffset.UTC);
        updatedAt = createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    }

}
