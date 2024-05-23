package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO;

public class RequestPaymentMethodDTODataMock {

    public static RequestPaymentMethodDTO buildRequestPaymentMethodDTO() {
        return new RequestPaymentMethodDTO(
            null,
            1L,
            1L,
            8900.0
        );
    }

}
