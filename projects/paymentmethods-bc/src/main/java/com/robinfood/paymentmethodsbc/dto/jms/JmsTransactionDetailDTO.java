package com.robinfood.paymentmethodsbc.dto.jms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JmsTransactionDetailDTO {
    private Long status;

    private Long paymentGatewayId;

    private Long countryId;

    private Long entityId;
}
