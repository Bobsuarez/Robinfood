package com.robinfood.core.dtos.orderbyuser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ResponseOrderDTO {

    private List<ResponseBrandDTO> brands;

    private String datetime;

    private Long flowId;

    private Long id;

    private boolean paid;

    private ResponsePaymentDTO payment;

    private Long platformId;

    private List<ResponseFinalProductDTO> products;

    private ResponseOrderStatusDTO status;

    private ResponseStoreDTO store;

    private String timezone;

    private String uid;

}
