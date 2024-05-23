package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.FOUR_ZEROS_STRING;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Builder
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
    @GenericGenerator(
            name = "UseExistingIdOtherwiseGenerateUsingIdentity",
            strategy = "com.robinfood.core.helpers.UseExistingIdOtherwiseGenerateUsingIdentity"
    )
    @GeneratedValue(generator = "UseExistingIdOtherwiseGenerateUsingIdentity")
    @Column(unique = true, nullable = false)
    private Long id;

    private LocalDate localDate;

    private LocalTime localTime;

    private Integer numberFinalProducts;

    private LocalDate operationDate;

    @Builder.Default
    @Column(name = "order_invoice_number")
    private String orderInvoiceNumber = FOUR_ZEROS_STRING;

    @Builder.Default
    @Column(name = "order_number")
    private String orderNumber = FOUR_ZEROS_STRING;

    private Long originId;

    private String originName;

    private Boolean paid;

    private Long paymentModelId;

    @Builder.Default
    private String pickupTime = "00:00:00";

    private Long posId;

    @Builder.Default
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

    @Transient
    @OneToMany(mappedBy = "orderId", fetch = FetchType.LAZY)
    private List<OrderPaymentEntity> paymentEntityList;

    @Transient
    @OneToMany(mappedBy = "orderId", fetch = FetchType.LAZY)
    private List<OrderUserDataEntity> userDataEntity;

    @Transient
    @OneToMany (mappedBy = "orderId", fetch = FetchType.LAZY)
    private List<OrderIntegrationEntity> orderIntegrationEntityList;

    @PrePersist
    public void onInsert() {
        createdAt = LocalDateTime.now(ZoneOffset.UTC);
        updatedAt = createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    }

    //<editor-fold desc="Constructor od the entity">
    public OrderEntity(
            Long billingResolutionId,
            Long brandId,
            String brandName,
            Long companyId,
            LocalDateTime createdAt,
            String currency,
            Long deliveryTypeId,
            Double discounts,
            Integer enabledTrigger,
            Long id,
            LocalDate localDate,
            LocalTime localTime,
            Integer numberFinalProducts,
            LocalDate operationDate,
            String orderInvoiceNumber,
            String orderNumber,
            Long originId,
            String originName,
            Boolean paid,
            Long paymentModelId,
            String pickupTime,
            Long posId,
            Boolean printed,
            Long statusId,
            Long storeId,
            String storeName,
            Double subtotal,
            Double taxes,
            Long transactionId,
            BigDecimal co2Total,
            Double total,
            String uid,
            String uuid,
            LocalDateTime updatedAt,
            Long userId,
            Long workshiftId
    ) {
        this.billingResolutionId = billingResolutionId;
        this.brandId = brandId;
        this.brandName = brandName;
        this.companyId = companyId;
        this.createdAt = createdAt;
        this.currency = currency;
        this.deliveryTypeId = deliveryTypeId;
        this.discounts = discounts;
        this.enabledTrigger = enabledTrigger;
        this.id = id;
        this.localDate = localDate;
        this.localTime = localTime;
        this.numberFinalProducts = numberFinalProducts;
        this.operationDate = operationDate;
        this.orderInvoiceNumber = orderInvoiceNumber;
        this.orderNumber = orderNumber;
        this.originId = originId;
        this.originName = originName;
        this.paid = paid;
        this.paymentModelId = paymentModelId;
        this.pickupTime = pickupTime;
        this.posId = posId;
        this.printed = printed;
        this.statusId = statusId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.subtotal = subtotal;
        this.taxes = taxes;
        this.transactionId = transactionId;
        this.co2Total = co2Total;
        this.total = total;
        this.uid = uid;
        this.uuid = uuid;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.workshiftId = workshiftId;
    }
    //</editor-fold>
}
