package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetOrderDetailDTO {

    private Long brandId;

    private String brandName;

    private Long companyId;

    private final BigDecimal co2Total;

    private List<CouponsDTO> coupons;

    private final String currency;

    private final Double discount;

    private final List<GetOrderDetailDiscountDTO> discounts;

    private final BigDecimal deduction;

    private final Long deliveryTypeId;

    private final ElectronicBillDTO electronicBill;

    private Long flowId;

    private BigDecimal foodCoins;

    private final Long id;

    private final String invoice;

    private final String notes;

    private final LocalDate operationDate;

    private final Long originId;

    private final String originName;

    private final String orderNumber;

    private final String orderUuid;

    private final String orderIntegrationId;

    private final String orderIntegrationUser;

    private final Boolean orderIsIntegration;

    private final String orderIntegrationCode;

    @JsonProperty("buyer")
    private final OrderFiscalIdentifierDTO orderFiscalIdentifier;

    private final List<GetOrderDetailPaymentMethodDTO> paymentMethods;

    private final Long posId;

    private final Boolean printed;

    private final List<GetOrderDetailFinalProductDTO> products;

    private final Long statusId;

    private final String statusName;

    private final Long storeId;

    private final String storeName;

    private final Double subtotal;

    private final Double tax;

    private final Double total;

    private final Long transactionId;

    private final String transactionUuid;

    private final String uid;

    private final UserDataDTO user;
}
