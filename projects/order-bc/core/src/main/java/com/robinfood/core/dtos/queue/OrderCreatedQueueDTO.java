package com.robinfood.core.dtos.queue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedQueueDTO implements Serializable {

    private static final long serialVersionUID = -8133003200203333168L;

    private ChannelDTO channel;

    private  Long id;

    private String orderDate;

    private Boolean paid;

    private Long transactionId;

    private String transactionUuid;

    private  String orderUid;

    private  String orderUuid;

    private String orderNumber;

    private Long flowId;

    private Long countryId;

    private Long companyId;

    private List<CouponDTO> coupons;

    private String timeZone;

    private Long invoice;

    private BigDecimal subtotal;

    private BigDecimal tax;

    private BigDecimal discount;

    private BigDecimal total;

    private UserDTO user;

    private AddressQueueDTO address;

    private List<PickupTimeQueueDTO> pickupTimes;

    private List<ProductDTO> products;

    private List<PaymentsDTO> payments;

    private List<DiscountDTO> discounts;

    private StoreDTO store;

}
