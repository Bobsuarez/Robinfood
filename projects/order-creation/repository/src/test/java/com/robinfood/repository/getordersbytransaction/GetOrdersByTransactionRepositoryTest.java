package com.robinfood.repository.getordersbytransaction;

import com.robinfood.core.dtos.getordersbytransaction.OrdersByTransactionResponseDTO;
import com.robinfood.core.entities.getordersbytransaction.OrdersByTransactionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrdersByTransactionRepositoryTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private IGetOrdersByTransactionRemoteDataSource getOrdersByTransactionRemoteDataSource;

    @InjectMocks
    private GetOrdersByTransactionRepository getOrdersByTransactionRepository;

    @Test
    void test_GetOrdersByTransactionRemoteRepository_Is_Successfully() {
        // Arrange
        String token = "token";
        Long transactionId = 3L;

        OrdersByTransactionEntity order =  OrdersByTransactionEntity.builder()
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
        List<OrdersByTransactionEntity> orders = List.of(order);

        OrdersByTransactionResponseDTO orderDTO =  OrdersByTransactionResponseDTO.builder()
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

        when(getOrdersByTransactionRemoteDataSource.invoke(any(), any()))
            .thenReturn(orders);

        when(modelMapper.map(any(), any())).thenReturn(orderDTO);

        // Act
        List<OrdersByTransactionResponseDTO> result = getOrdersByTransactionRepository.invoke(transactionId, token);

        // Assert
        assertEquals(orderDTO.getTransactionId(), result.get(0).getTransactionId());

    }
}
