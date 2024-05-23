package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.dto.api.transactions.TransactionEntityDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TransactionEntityDTOSample {


    public static TransactionEntityDTO getTransactionEntityExample1() {
        return TransactionEntityDTO
            .builder()
            .uuid("095fceb0-c36c-4d98-8d74-38e568dde06d")
            .id(1L)
            .createdAt(LocalDateTime.of(2022,
                                            Month.MAY,
                                            25,
                                            11,
                                            39,
                                            40
            ))
            .transactionReference("13")
            .transactionCode("sad")
            .succeeded(true)
            .authorizationCode("2315")
            .status(
                TransactionEntityDTO
                    .StatusDTO
                    .builder()
                    .id(1L)
                    .name("Accepted")
                    .build()
            )
            .entity(
                TransactionEntityDTO
                    .EntityDTO
                    .builder()
                    .id(1L)
                    .sourceId(2L)
                    .reference("500")
                    .build()
            )
            .payment(
                TransactionEntityDTO
                    .PaymentDTO
                    .builder()
                    .subtotal( new BigDecimal("10000.0000"))
                    .total(new BigDecimal("10500.0000"))
                    .tax(new BigDecimal("500.0000"))
                    .build()
            )
            .paymentMethod(
                TransactionEntityDTO
                    .PaymentMethodsDTO
                    .builder()
                    .id(1L)
                    .name("Online")
                    .build()
            )
            .build();
    }

    public static TransactionEntityDTO getTransactionEntityExample2() {
        return TransactionEntityDTO
            .builder()
            .uuid("f898c5c5-f2c9-4259-ac3e-14bda61b4fd1")
            .id(2L)
            .createdAt(LocalDateTime.of(2022,
                                            Month.MARCH,
                                            25,
                                            11,
                                            39,
                                            40
            ))
            .transactionReference("13")
            .transactionCode("sad")
            .succeeded(true)
            .authorizationCode("508268")
            .status(
                TransactionEntityDTO
                    .StatusDTO
                    .builder()
                    .id(1L)
                    .name("Accepted")
                    .build()
            )
            .entity(
                TransactionEntityDTO
                    .EntityDTO
                    .builder()
                    .id(1L)
                    .sourceId(2L)
                    .reference("1400840486|4d0e7209-d1f5-456c-8501-b1cb0dedd29e")
                    .build()
            )
            .payment(
                TransactionEntityDTO
                    .PaymentDTO
                    .builder()
                    .subtotal( new BigDecimal("10000.000"))
                    .total(new BigDecimal("10500.0000"))
                    .tax(new BigDecimal("500.0000"))
                    .build()
            )
            .paymentMethod(
                TransactionEntityDTO
                    .PaymentMethodsDTO
                    .builder()
                    .id(1L)
                    .name("Online")
                    .build()
            )
            .build();
    }

    public static List<TransactionEntityDTO> getListTransactionEntityDTO() {
        List<TransactionEntityDTO> listTransaction = new ArrayList<>();
        listTransaction.add(getTransactionEntityExample1());
        listTransaction.add(getTransactionEntityExample2());
        return listTransaction;
    }
}
