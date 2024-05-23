package com.robinfood.core.dtos.response.transaction;

import com.robinfood.core.dtos.request.transaction.RequestPaymentDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponsePaymentMethodDTO {

    private RequestPaymentDetailDTO detail;

    private Long id;

    private Long originId;

    private Double value;

}
