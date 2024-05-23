package com.robinfood.core.entities;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "order_final_product_portions")
public class OrderFinalProductPortionEntity {

    private Boolean addition;

    private Double basePrice;

    private Long companyId;

    private Boolean changedProduct;

    private LocalDateTime createdAt;

    private Double discount;

    private Long dicUnitId;

    private Integer effectiveSale;

    private Long groupId;

    private String groupName;

    private String groupSku;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String operationDate;

    private Long orderId;

    private Long orderFinalProductId;

    private Long portionId;

    private String portionName;

    private String portionSku;

    private Integer portionStatus;

    private Long productId;

    private String productName;

    private Integer quantity;

    private Integer quantityFree;

    private Long storeId;

    private Double unitsNumber;

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
