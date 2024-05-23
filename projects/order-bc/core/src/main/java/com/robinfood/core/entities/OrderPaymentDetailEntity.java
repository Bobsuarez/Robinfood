package com.robinfood.core.entities;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "order_payment_detail")
public class OrderPaymentDetailEntity {

    private String accountType;

    private String additionalInformation;

    private String approvalCode;

    private LocalDateTime createdAt;

    private String franchiseType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private Long orderPaymentId;

    private String posTerminalCode;

    private Long providerId;

    private String selfManagementCode;

    private Long uniqueTransactionId;

    private LocalDateTime updatedAt;

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
