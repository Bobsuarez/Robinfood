package com.robinfood.localserver.commons.dtos.orders.billing;

import com.robinfood.localserver.commons.dtos.orders.CouponsDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderDetailDiscountDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderDetailPaymentMethodDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderDetailUserDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.EntityTreasuryDepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderBillingDTO {

    private Long brandId;

    private String brandName;

    private BigDecimal co2Total;

    private String currency;

    @Builder.Default
    private List<CouponsDTO> coupons = new ArrayList<>();

    private Long id;

    private BigDecimal deduction;

    private BigDecimal discount;

    @Builder.Default
    private List<OrderDetailDiscountDTO> discounts = new ArrayList<>();

    private Long deliveryTypeId;

    private Long flowId;

    private String invoice;

    private String notes;

    private Long originId;

    private String originName;

    private String orderNumber;

    private Boolean orderIsIntegration;

    private String orderUuid;

    private List<OrderDetailPaymentMethodDTO> paymentMethods;

    private Boolean printed;

    private List<OrderBillingProductDTO> products;

    private Long statusId;

    private String statusName;

    private Long storeId;

    private String storeName;

    private Double subtotal;

    private Double tax;

    private BigDecimal total;

    private Long transactionId;

    private List<EntityTreasuryDepartmentDTO> treasuryEntities;

    private List<TreasuryPaymentsDTO> treasuryPayments;

    private String uid;

    private OrderDetailUserDTO user;

    private List<OrderDetailUserDTO> users;
}
