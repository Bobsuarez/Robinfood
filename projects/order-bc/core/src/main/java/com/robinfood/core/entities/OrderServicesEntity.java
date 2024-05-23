package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Builder
@Table(name = "order_services")
public class OrderServicesEntity {

    private LocalDateTime createdAt;

    private Double discount;

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "price_nt")
    private Double priceNt;

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "tax_percentage")
    private Double taxPercentage;

    @Column(name = "tax_price")
    private Double taxPrice;

    @Column(name = "total")
    private Double total;

    @PrePersist
    public void onInsert() {
        createdAt = LocalDateTime.now(ZoneOffset.UTC);
    }

}
