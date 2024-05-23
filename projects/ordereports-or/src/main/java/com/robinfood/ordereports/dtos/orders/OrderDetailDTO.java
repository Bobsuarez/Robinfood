package com.robinfood.ordereports.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetailDTO implements Serializable {

    private AddressDTO address;

    private List<BrandDTO> brands;

    private String createdAt;

    private List<CouponDTO> coupons;

    private DeliveryCourierServiceDTO deliveryCourierService;

    private Double discount;

    private List<DiscountDTO> discounts;

    private Long flowId;

    private Long id;

    private String orderNumber;

    private OriginDTO origin;

    private Boolean paid;

    private PaymentDTO payment;

    private Long paymentModelId;

    private Boolean printed;

    private List<ProductDTO> products;

    private List<ServiceDTO> services;

    private Long statusId;

    private String transactionUuid;

    private String uid;

    private UserDTO user;
}
