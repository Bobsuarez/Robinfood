package com.robinfood.ordereports_bc_muyapp.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    private static final int IS_FULL_SYNCHRONIZED = 1;

    private Short billingResolutionId;

    @Column(name = "franchiseId")
    private Short brandId;

    @Column(name = "franchiseName")
    private String brandName;

    private Short companyId;

    private LocalDateTime createdAt;

    private String currency;

    private Short deliveryTypeId;

    private Double discounts;

    private Short enabledTrigger;

    @Builder.Default
    private Short fullSynchronized = IS_FULL_SYNCHRONIZED;

    @Id
    @Column(unique = true, nullable = false)
    private Integer id;

    private LocalDate localDate;

    private LocalTime localTime;

    private Short numberFinalProducts;

    private LocalDate operationDate;

    @Column(name = "order_invoice_number")
    private String orderInvoiceNumber;

    @Column(name = "order_number")
    private String orderNumber;

    private Short originId;

    private String originName;

    private Boolean paid;

    private Short paymentModelId;

    @Builder.Default
    private String pickupTime = "00:00:00";

    private Short posId;

    @Builder.Default
    private Boolean printed = false;

    private Short statusId;

    private Short storeId;

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
}
