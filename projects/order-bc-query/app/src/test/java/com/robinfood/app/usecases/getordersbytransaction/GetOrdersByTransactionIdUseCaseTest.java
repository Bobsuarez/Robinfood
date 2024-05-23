package com.robinfood.app.usecases.getordersbytransaction;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetOrdersByTransactionIdUseCaseTest {

    @Mock
    private IOrdersRepository mockOrdersRepository;

    @InjectMocks
    private GetOrdersByTransactionIdUseCase mockUseCase;

    private final List<OrderEntity> orderEntities = new ArrayList<>(Arrays.asList(
        new OrderEntity(
            1L,
            1L,
            "Brand Name",
            1L,
            LocalDateTime.of(2021, 10, 11, 10, 10, 10),
            "CO",
            1L,
            0.0,
            1,
            1L,
            LocalDate.of(2021, 10, 11),
            LocalTime.of(10, 10, 10),
                1,
                LocalDate.of(2021, 10, 11),
                "476383",
                "234234",
                1L,
                "Origin Name",
                Boolean.TRUE,
                1L,
                "Pickup Time",
                1L,
                Boolean.TRUE,
                1L,
                1L,
                "Store Name",
                0.0,
                0.0,
                1L,
                BigDecimal.valueOf(0.0),
                0.0,
                "uid",
                "50eaf34f-7252-46ef-9a69-2225b06e14be",
                LocalDateTime.now(),
                1L,
                1L
        )
    ));

    private final List<OrderDTO> orderDTOS = new ArrayList<>(Arrays.asList(
        new OrderDTO(
            1L,
            1L,
            "Brand Name",
            1L,
            LocalDateTime.of(2021, 10, 11, 10, 10, 10),
            "CO",
            1L,
            0.0,
            1L,
            LocalDate.of(2021, 10, 11),
                LocalTime.of(10, 10, 10),
                1,
                LocalDate.of(2021, 10, 11),
                "476383",
                "234234",
                1L,
                "Origin Name",
                Boolean.TRUE,
                "Pickup Time",
                1L,
                Boolean.TRUE,
                1L,
                1L,
                "Store Name",
                0.0,
                0.0,
                1L,
                BigDecimal.valueOf(0.0),
                0.0,
                "uid",
                "50eaf34f-7252-46ef-9a69-2225b06e14be",
                1L,
                1L
        )
    ));

    @Test
    void test_When_Get_Orders_By_Transaction_Happy_Path() {
        Long transactionId = 3L;

        when(mockOrdersRepository.findOrderEntitiesByTransactionId(any())).thenReturn(orderEntities);

        List<OrderDTO> result = mockUseCase.invoke(transactionId);

        Assertions.assertEquals(orderDTOS, result);
    }

}
