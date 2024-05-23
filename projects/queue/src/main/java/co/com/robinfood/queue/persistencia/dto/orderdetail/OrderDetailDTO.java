package co.com.robinfood.queue.persistencia.dto.orderdetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailDTO {

    private  OrderFiscalIdentifierDTO buyer;

    private  Long brandId;

    private  String brandName;

    private  Long companyId;

    private  BigDecimal co2Total;

    private  String currency;

    private  List<CouponDTO> coupons;

    private  Long id;

    private  Double discount;

    private  List<OrderDetailDiscountDTO> discounts;

    private  Long deliveryTypeId;

    private  BigDecimal deduction;

    private  Long flowId;

    private  BigDecimal foodCoins;

    private  String invoice;

    private  String notes;

    private  Long originId;

    private  String originName;

    private  String orderNumber;

    private  String orderUuid;

    private  String orderIntegrationId;

    private  String orderIntegrationUser;

    private  Boolean orderIsIntegration;

    private  String orderIntegrationCode;

    private  String operationDate;

    private  String operationTime;

    private  List<OrderDetailPaymentMethodDTO> paymentMethods;

    private  Long posId;

    private  String posResolutionPrefix;

    private  Boolean printed;

    private  List<OrderDetailProductDTO> products;

    private  List<ServiceDTO> services;

    private  Long statusId;

    private  String statusName;

    private  Long storeId;

    private  String storeName;

    private  Double subtotal;

    private  Double tax;

    private  Double total;

    private  Long transactionId;

    private  String transactionUuid;

    private  String uid;

    private  OrderDetailUserDTO user;

    private  List<OrderDetailUserDTO> users;
}
