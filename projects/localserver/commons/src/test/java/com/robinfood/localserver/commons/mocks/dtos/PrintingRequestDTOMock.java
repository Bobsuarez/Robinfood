package com.robinfood.localserver.commons.mocks.dtos;

import com.robinfood.localserver.commons.dtos.http.PrintingRequestDTO;

public class PrintingRequestDTOMock {

    public PrintingRequestDTO getDefaultData() {
        return PrintingRequestDTO
                .builder()
                .orderId(5463970L)
                .isReprint(Boolean.TRUE)
                .build();
    }
}
