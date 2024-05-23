package com.robinfood.app.usecases.getordersbytransaction;

import com.robinfood.core.dtos.getordersbytransaction.OrdersByTransactionResponseDTO;
import com.robinfood.repository.getordersbytransaction.IGetOrdersByTransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrdersByTransactionUseCaseTest {

    @Mock
    private IGetOrdersByTransactionRepository mockGetOrdersByTransactionRepository;

    @InjectMocks
    private GetOrdersByTransactionUseCase getOrdersByTransactionUseCase;

    @Test
    void test_When_Get_Orders_By_Transaction_Happy_Path() {

        OrdersByTransactionResponseDTO orderResponse = OrdersByTransactionResponseDTO.builder()
            .billingResolutionId(1L)
            .brandId(4L)
            .brandName("Pixi")
            .companyId(1L)
            .currency("COP")
            .deliveryTypeId(1L)
            .discounts(2500D)
            .id(3328802L)
            .originId(10L)
            .statusId(2L)
            .transactionId(3L)
            .userId(55318L)
            .build();

        List<OrdersByTransactionResponseDTO> orders = new ArrayList<>();
        Collections.singletonList(orderResponse);

        Long transactionId = 3L;
        String token = "token";

        when(mockGetOrdersByTransactionRepository.invoke(any(), any())).thenReturn(orders);

        List<OrdersByTransactionResponseDTO> result = getOrdersByTransactionUseCase.invoke(token, transactionId);

        assertEquals(orders, result);

    }
}
