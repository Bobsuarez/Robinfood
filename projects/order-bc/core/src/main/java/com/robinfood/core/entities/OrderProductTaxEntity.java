package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "order_final_product_taxes")
public class OrderProductTaxEntity {

    private Long articleId;

    private Long articleTypeId;

    private LocalDateTime createdAt;

    private Long dicTaxId;

    private Long familyTaxTypeId;

    @Id
    @GeneratedValue()
    private Long id;

    private Long orderFinalProductId;

    private Long orderId;

    private Double taxPrice;

    private Long taxTypeId;

    private String taxTypeName;

    private Double taxValue;

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
