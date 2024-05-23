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
@NoArgsConstructor
@Builder
@Table(name = "order_final_product_taxes")
public class OrderProductTaxEntity {

    private Long articleId;

    private Long articleTypeId;

    private LocalDateTime createdAt;

    private Long dicTaxId;

    private Long familyTaxTypeId;

    @Id
    private Long id;

    private Long orderFinalProductId;

    private Long orderId;

    private Double taxPrice;

    private Long taxTypeId;

    private String taxTypeName;

    private Double taxValue;

    private LocalDateTime updatedAt;
}
