package com.robinfood.localprinterbc.dtos.decorator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.robinfood.localprinterbc.dtos.orders.CouponsDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderBuyerDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDiscountDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailPaymentMethodDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailUserDTO;
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
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OrderPrintDTO {

    private OrderBuyerDTO buyer;

    private Long brandId;

    private String brandName;

    private BigDecimal co2Total;

    private String currency;

    private List<CouponsDTO> coupons;

    private Long id;

    private BigDecimal deduction;

    private BigDecimal discount;

    private List<OrderDetailDiscountDTO> discounts;

    private Long deliveryTypeId;

    private GroupPortionsPrintDTO drinksAndDesserts;

    private Long flowId;

    private String invoice;

    private String notes;

    private Long originId;

    private String originName;

    private String orderNumber;

    private String orderIntegrationId;

    private String orderIntegrationUser;

    private String orderIntegrationCode;

    private Boolean orderIsIntegration;

    private String orderUuid;

    private List<OrderDetailPaymentMethodDTO> paymentMethods;

    private Boolean printed;

    private List<OrderDetailProductDTO> suggestedProducts;

    private Long statusId;

    private String statusName;

    private Long storeId;

    private String storeName;

    private Double subtotal;

    private Double tax;

    private ToGoDTO toGo;

    private BigDecimal total;

    private Long transactionId;

    private String uid;

    private IdAndUserDTO userAndIdOrder;

    private OrderDetailUserDTO user;

    private List<OrderDetailUserDTO> users;
}
