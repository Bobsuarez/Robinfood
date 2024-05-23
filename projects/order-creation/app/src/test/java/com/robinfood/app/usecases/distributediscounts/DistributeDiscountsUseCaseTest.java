package com.robinfood.app.usecases.distributediscounts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDiscountDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import kotlin.collections.CollectionsKt;
import org.apache.activemq.transaction.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DistributeDiscountsUseCaseTest {

    @InjectMocks
    private DistributeDiscountsUseCase distributeDiscountsUseCase;

    private final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();
    private final OrderDTO order = transactionRequestDTOMocks.orderDTOs.get(0);

    @Test
    void test_Distribute_discounts() {

        order.setTotal(BigDecimal.valueOf(12900.0));

        distributeDiscountsUseCase.invoke(
                order,
                BigDecimal.valueOf(3000.0),
                1L,
                transactionRequestDTOMocks.transactionRequestDTO
        );

        BigDecimal discountTotal = CollectionsKt.map(
                order.getFinalProducts().get(0).getDiscounts(),
                FinalProductDiscountDTO::getValue
        ).stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        assertEquals(BigDecimal.valueOf(3000.0000).setScale(4), discountTotal);
    }

    @Test
    void test_Distribute_discounts_When_isEmpty() {

        TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTO;

        transactionRequestDTO.getOrders().get(0).setFinalProducts(Collections.emptyList());

        order.setTotal(BigDecimal.valueOf(12900.0));

        distributeDiscountsUseCase.invoke(
                transactionRequestDTO.getOrders().get(0),
                BigDecimal.valueOf(3000.0),
                1L,
                transactionRequestDTO

        );
    }
}
