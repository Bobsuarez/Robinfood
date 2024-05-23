package com.robinfood.ordereports_bc_muyapp.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "order_final_product_portions")
public class OrderFinalProductPortionEntity {

    private Boolean addition;

    private Double basePrice;

    private Short companyId;

    private Boolean changedProduct;

    private LocalDateTime createdAt;

    private Double discount;

    private Short dicUnitId;

    private Integer effectiveSale;

    private Short groupId;

    private String groupName;

    private String groupSku;

    @Id
    private Long id;

    private String operationDate;

    private Integer orderId;

    private Long orderFinalProductId;

    private Short portionId;

    private String portionName;

    private String portionSku;

    private Integer portionStatus;

    private Short productId;

    private String productName;

    private Integer quantity;

    private Short quantityFree;

    private Short storeId;

    private Double unitsNumber;

    private LocalDateTime updatedAt;

}
