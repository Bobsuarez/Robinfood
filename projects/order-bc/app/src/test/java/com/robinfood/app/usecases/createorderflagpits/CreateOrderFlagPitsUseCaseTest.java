package com.robinfood.app.usecases.createorderflagpits;

import com.robinfood.core.dtos.request.order.OrderFlagPitsDTO;
import com.robinfood.core.entities.OrderFlagPitsEntity;
import com.robinfood.repository.orderflagpits.IOrderFlagPitsRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateOrderFlagPitsUseCaseTest {

    @Mock
    private IOrderFlagPitsRepository mockOrderFlagPitsRepository;

    @InjectMocks
    private CreateOrderFlagPitsUseCase createOrderFlagPitsUseCase;

    private final OrderFlagPitsEntity orderFlagPitsEntity = new OrderFlagPitsEntity(
            "123HAS",
            null,
            1L,
            1L
    );

    private final OrderFlagPitsDTO orderFlagPitsDTO = new OrderFlagPitsDTO(
            "123HAS",
            1L,
            null,
            1L
    );

    @Test
    void test_CreateOrderFlagPits_When_Save_Success() {

        when(mockOrderFlagPitsRepository.saveAll(Arrays.asList(orderFlagPitsEntity)))
                .thenReturn(Arrays.asList(orderFlagPitsEntity));

        final List<OrderFlagPitsDTO> result = createOrderFlagPitsUseCase
                .invoke(Arrays.asList(orderFlagPitsDTO))
                .join();

        Mockito.verify(mockOrderFlagPitsRepository)
                .saveAll(Arrays.asList(orderFlagPitsEntity));

        assertEquals(Arrays.asList(orderFlagPitsDTO), result);
    }
}
