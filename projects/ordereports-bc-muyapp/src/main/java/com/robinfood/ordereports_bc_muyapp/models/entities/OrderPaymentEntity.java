package com.robinfood.ordereports_bc_muyapp.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.robinfood.ordereports_bc_muyapp.constants.GlobalConstants.DEFAULT_DOUBLE_VALUE;

@AllArgsConstructor
@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "order_payments")
public class OrderPaymentEntity {

    private LocalDateTime createdAt;

    @Builder.Default
    private Double discount = DEFAULT_DOUBLE_VALUE;

    @Id
    private Long id;

    private Integer orderId;

    private Short originId;

    private Short paymentMethodId;

    private Double subtotal;

    @Builder.Default
    private Double tax = DEFAULT_DOUBLE_VALUE;

    private LocalDateTime updatedAt;

    private Double value;

}
