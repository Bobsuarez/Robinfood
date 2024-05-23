package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.transaction.RequestPaymentDetailDTO;

public class RequestPaymentDetailDTODataMock {

    public RequestPaymentDetailDTO getDataDefault() {

        return new RequestPaymentDetailDTO(
                "hola",
                "hola",
                "hola",
                "hola",
                1L,
                1L,
                "hola",
                1L,
                "HOLA",
                1L
        );
    }

}
