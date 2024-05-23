package com.robinfood.localserver.commons.entities.orders.billing;

import com.robinfood.localserver.commons.entities.orders.CouponEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailDiscountEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailPaymentMethodEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailUserEntity;
import com.robinfood.localserver.commons.entities.storeconfiguration.treasurydepartment.EntityTreasuryDepartmentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderBillingEntity {

    private Long brandId;

    private String brandName;

    private BigDecimal co2Total;

    private String currency;

    private List<CouponEntity> coupons;

    private Long id;

    private BigDecimal deduction;

    private BigDecimal discount;

    private List<OrderDetailDiscountEntity> discounts;

    private Long deliveryTypeId;

    private Long flowId;

    private String invoice;

    private String notes;

    private Long originId;

    private String originName;

    private String orderNumber;

    private Boolean orderIsIntegration;

    private List<OrderDetailPaymentMethodEntity> paymentMethods;

    private Boolean printed;

    private List<OrderBillingProductEntity> products;

    private Long statusId;

    private String statusName;

    private Long storeId;

    private String storeName;

    private Double subtotal;

    private Double tax;

    private BigDecimal total;

    private Long transactionId;

    private List<EntityTreasuryDepartmentEntity> treasuryEntities;

    private List<TreasuryPaymentsEntity> treasuryPayments;

    private String uid;

    private OrderDetailUserEntity user;

    private List<OrderDetailUserEntity> users;
}
