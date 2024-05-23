package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String redeemedId;

    @Column(nullable = false)
    private Long transactionId;

    @UpdateTimestamp
    private Date updatedAt;

    @Column(nullable = false)
    private BigDecimal value;
}
