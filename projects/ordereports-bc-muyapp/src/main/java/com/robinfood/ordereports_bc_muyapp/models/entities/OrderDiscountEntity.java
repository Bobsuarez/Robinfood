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
@Builder
@Entity
@NoArgsConstructor
@Table(name = "order_discounts")
public class OrderDiscountEntity {

    private LocalDateTime createdAt;

    private Long discountId;

    private Double discountValue;

    @Id
    private Long id;

    private Short orderDiscountTypeId;

    private Integer orderId;

    private Long orderFinalProductId;

    private LocalDateTime updatedAt;
}
