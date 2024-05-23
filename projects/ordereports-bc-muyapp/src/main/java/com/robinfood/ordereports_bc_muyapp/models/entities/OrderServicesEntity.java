package com.robinfood.ordereports_bc_muyapp.models.entities;

import jakarta.persistence.Column;
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
@Table(name = "order_services")
public class OrderServicesEntity {

    private LocalDateTime createdAt;

    private Double discount;

    @Id
    private Long id;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "price_nt")
    private Double priceNt;

    @Column(name = "service_id")
    private Short serviceId;

    @Column(name = "tax_percentage")
    private Double taxPercentage;

    @Column(name = "tax_price")
    private Double taxPrice;

    @Column(name = "total")
    private Double total;
}
