package com.robinfood.core.entities;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javax.persistence.Entity;
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
@Table(name = "order_integrations")
public class OrderIntegrationEntity {

    private String addressCity;

    private String addressDescription;

    private Date arrivalDate;

    private Time arrivalTime;

    private String code;

    private LocalDateTime createdAt;

    private Long franchiseId;

    private String franchiseName;

    private String integrationId;

    private String notes;

    @Id
    private Long orderId;

    private Long originId;

    private String originName;

    private Integer orderType;

    private String paymentMethod;

    private Long storeId;

    private String storeName;

    private Double totalDelivery;

    private Double totalDiscount;

    private Double totalOrder;

    private Double totalTip;

    private LocalDateTime updatedAt;

    private String userName;

    private String userPhone;

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
