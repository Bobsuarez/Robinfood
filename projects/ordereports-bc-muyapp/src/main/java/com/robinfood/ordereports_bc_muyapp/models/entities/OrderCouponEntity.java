package com.robinfood.ordereports_bc_muyapp.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "order_coupons")
public class OrderCouponEntity implements Serializable {

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Long couponType;

    @Column(updatable = false)
    private Date createdAt;

    @Id
    private Integer id;

    private String redeemedId;

    @Column(nullable = false)
    private Integer transactionId;

    private Date updatedAt;

    @Column(nullable = false)
    private BigDecimal value;
}
