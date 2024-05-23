package com.robinfood.core.dtos.response.orderhistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class ResponseOrderDetailDTO {

    private ResponseOrderAddressDTO address;

    private List<ResponseBrandDTO> brands;

    private Long flowId;

    private Long id;

    private String orderNumber;

    private ResponseOriginDTO origin;

    private boolean paid;

    private ResponsePaymentDTO payment;

    private Long paymentModelId;

    private List<ResponseFinalProductDTO> products;

    private List<ResponseServiceDTO> services;

    private ResponseOrderStatusDTO status;

    private String uid;

}
