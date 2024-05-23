package com.robinfood.changestatusbc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import static com.robinfood.changestatusbc.configs.constants.GlobalConstants.FOUR_ZEROS_STRING;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    private static final int IS_FULL_SYNCHRONIZED = 1;

    private Long billingResolutionId;

    @Column(name = "franchiseId")
    private Long brandId;

    @Column(name = "franchiseName")
    private String brandName;

    private Long companyId;

    private LocalDateTime createdAt;

    private String currency;

    private Long deliveryTypeId;

    private Double discounts;

    private Integer enabledTrigger;

    private final Integer fullSynchronized = IS_FULL_SYNCHRONIZED;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    private LocalDate localDate;

    private LocalTime localTime;

    private Integer numberFinalProducts;

    private LocalDate operationDate;

    @Column(name = "order_invoice_number")
    private String orderInvoiceNumber = FOUR_ZEROS_STRING;

    @Column(name = "order_number")
    private String orderNumber = FOUR_ZEROS_STRING;

    private Long originId;

    private String originName;

    private Boolean paid;

    private Long paymentModelId;

    private String pickupTime = "00:00:00";

    private Long posId;

    private Boolean printed = false;

    private Long statusId;

    private Long storeId;

    private String storeName;

    private Double subtotal;

    private Double taxes;

    private Long transactionId;

    @Column(name = "co2_compensation_price")
    private BigDecimal co2Total;

    private Double total;

    private String uid;

    private String uuid;

    private LocalDateTime updatedAt;

    private Long userId;

    private Long workshiftId;

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
