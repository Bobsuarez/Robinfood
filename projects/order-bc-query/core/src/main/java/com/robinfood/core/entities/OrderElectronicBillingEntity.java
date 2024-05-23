package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "order_electronic_billings")
public class OrderElectronicBillingEntity {

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    private String responsePayload;

    @Column(nullable = false)
    private String requestPayload;

    @Column(nullable = false)
    private String statusCode;

    @Column(nullable = false)
    private Long storeId;

    @Column(nullable = false)
    private String storeName;

    @UpdateTimestamp
    private Date updatedAt;
}
