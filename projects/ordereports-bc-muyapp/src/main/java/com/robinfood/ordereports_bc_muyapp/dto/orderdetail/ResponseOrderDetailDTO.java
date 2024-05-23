package com.robinfood.ordereports_bc_muyapp.dto.orderdetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Setter
public class ResponseOrderDetailDTO {

    private ResponseOrderAddressDTO address;

    private List<ResponseBrandDTO> brands;

    private List<ResponseCouponsDTO> coupons;

    private String createdAt;

    private Short flowId;

    private Integer id;

    private String orderNumber;

    private ResponseOriginDTO origin;

    private boolean paid;

    private ResponsePaymentDTO payment;

    private Short paymentModelId;

    private boolean printed;

    private List<ResponseFinalProductDTO> products;

    private List<ResponseServiceDTO> services;

    private Short statusId;

    private Long transactionId;

    private String transactionUuid;

    private String uid;

    private ResponseUserDataDTO user;

}
