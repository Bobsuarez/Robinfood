package com.robinfood.core.entities;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_VALUE;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "order_payments")
public class OrderPaymentEntity {

    private LocalDateTime createdAt;

    @Builder.Default
    private Double discount = DEFAULT_DOUBLE_VALUE;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long originId;

    private Long paymentMethodId;

    private Double subtotal;

    @Builder.Default
    private Double tax = DEFAULT_DOUBLE_VALUE;

    private LocalDateTime updatedAt;

    private Double value;

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
