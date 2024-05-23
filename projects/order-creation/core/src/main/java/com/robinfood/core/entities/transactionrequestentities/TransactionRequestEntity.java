package com.robinfood.core.entities.transactionrequestentities;

import com.robinfood.core.entities.OrderFiscalIdentifierEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponResponseEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TransactionRequestEntity {

    private AddressEntity address;

    private Boolean alreadyValidated;

    private CompanyEntity company;

    private List<CouponEntity> coupons;

    private List<PerformCouponResponseEntity> couponResponseEntities;

    private DeliveryEntity delivery;

    private DeviceEntity device;

    private List<DeductionEntity> deductions;

    private Long flowId;

    private Long id;

    private List<OrderRequestEntity> orders;

    private OrderFiscalIdentifierEntity buyer;

    private Boolean paid;

    private List<PaymentMethodEntity> paymentMethods;

    private List<PaymentMethodEntity> paymentsPaid;

    private BigDecimal total;

    private NewUserEntity user;

    private UUID uuid;

    private BigDecimal co2Total;

    /**
     * The constructor is overloaded, because the addresses attribute is optional.
     */
    public TransactionRequestEntity(
            Boolean alreadyValidated,
            CompanyEntity company,
            DeliveryEntity delivery,
            DeviceEntity device,
            Long flowId,
            Long id,
            List<OrderRequestEntity> orders,
            OrderFiscalIdentifierEntity orderFiscalIdentifier,
            Boolean paid, List<PaymentMethodEntity> paymentMethods,
            BigDecimal total, NewUserEntity user) {

        this.alreadyValidated = alreadyValidated;
        this.company = company;
        this.delivery = delivery;
        this.device = device;
        this.flowId = flowId;
        this.id = id;
        this.orders = orders;
        this.buyer = orderFiscalIdentifier;
        this.paid = paid;
        this.paymentMethods = paymentMethods;
        this.total = total;
        this.user = user;
    }
}
