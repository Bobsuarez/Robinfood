package com.robinfood.ordereports.mocks;

import com.robinfood.ordereports.dtos.orders.PaymentDetailDTO;
import com.robinfood.ordereports.dtos.orders.CreditCardDTO;
import com.robinfood.ordereports.dtos.orders.PaymentStatusDTO;
import com.robinfood.ordereports.dtos.orders.FailedTransactionDTO;
import com.robinfood.ordereports.dtos.orders.TraceDTO;
import com.robinfood.ordereports.dtos.orders.TerminalDTO;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public final class PaymentDetailDTOMock {

    public static PaymentDetailDTO getDataDefault() {
        return PaymentDetailDTO.builder()
                .authorizationCode("")
                .creditCard(getCreditCardDTOMock())
                .generalPaymentMethodId(6L)
                .id(29338333L)
                .paymentGatewayId(1L)
                .platformId(1L)
                .status(getPaymentStatusDTOMock())
                .storeId(2L)
                .terminal(getTerminalDTOMock())
                .total(BigDecimal.valueOf(1000))
                .userId(1L)
                .uuid("")
                .build();
    }

    private static CreditCardDTO getCreditCardDTOMock(){
        return CreditCardDTO.builder()
                .id(239339L)
                .number("***3973")
                .type("VISA").build();
    }

    private static PaymentStatusDTO getPaymentStatusDTOMock(){
        return PaymentStatusDTO.builder()
                .createdAt("2024-05-05 10:22:00")
                .failedTransaction(getFailedTransactionDTOMock())
                .id(1L)
                .name("Accepted")
                .trace(getTraceDTOMock()).build();
    }

    private static FailedTransactionDTO getFailedTransactionDTOMock(){
        return FailedTransactionDTO.builder()
                .code("")
                .description("")
                .type(0L).build();
    }

    private static List<TraceDTO> getTraceDTOMock(){
        TraceDTO traceDTO = TraceDTO.builder()
                .createdAt("2024-05-05 10:22:00")
                .id(2L)
                .message("")
                .name("Pending").build();
        return Collections.singletonList(traceDTO);
    }

    private static TerminalDTO getTerminalDTOMock(){
        return TerminalDTO.builder()
                .id(1L)
                .name("")
                .uuid("").build();
    }
}
