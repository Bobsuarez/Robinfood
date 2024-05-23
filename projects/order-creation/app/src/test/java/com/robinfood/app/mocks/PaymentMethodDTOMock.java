package com.robinfood.app.mocks;

import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PaymentMethodDTOMock {

    public static PaymentMethodDTO build() {
        return PaymentMethodDTO.builder()
                .id(1L)
                .originId(1L)
                .value(BigDecimal.valueOf(1000.0))
                .build();
    }

    public static List<PaymentMethodDTO> List() {
        return new ArrayList<>(
                Arrays.asList(
                        PaymentMethodDTO.builder()
                                .id(1L)
                                .originId(1L)
                                .value(BigDecimal.valueOf(2000.0))
                                .build(),
                        PaymentMethodDTO.builder()
                                .originId(1L)
                                .value(BigDecimal.valueOf(4000.0))
                                .build()
                )
        );
    }

    public static List<PaymentMethodDTO> ListWithDetail() {
        return new ArrayList<>(
                Collections.singletonList(
                        PaymentMethodDTO.builder()
                                .detail(PaymentMethodDetailDTOMock.build())
                                .id(6L)
                                .originId(1L)
                                .value(BigDecimal.valueOf(8000.0))
                                .build()
                )
        );
    }
}
