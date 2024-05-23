package com.robinfood.app.usecases.createorderflagsubmarine;

import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.request.order.OrderFlagSubmarineDTO;
import com.robinfood.core.entities.OrderFlagSubmarineEntity;
import com.robinfood.repository.orderflagsubmarine.IOrderFlagSubmarineRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateOrderFlagSubmarineUseCaseTest {

    @Mock
    private IOrderFlagSubmarineRepository mockOrderFlagSubmarineRepository;

    @InjectMocks
    private CreateOrderFlagSubmarineUseCase createOrderFlagSubmarineUseCase;

    private final OrderFlagSubmarineEntity orderFlagSubmarineEntity = new OrderFlagSubmarineEntity(
        null,
        1L,
        1L
    );

    private final OrderFlagSubmarineDTO orderFlagSubmarineDTO = new OrderFlagSubmarineDTO(
        1L,
        true,
        1L
    );

    @Test
    void test_CreateOrderFlagSubmarine_When_Save_Success() {

        when(mockOrderFlagSubmarineRepository.saveAll(Arrays.asList(orderFlagSubmarineEntity)))
            .thenReturn(Arrays.asList(orderFlagSubmarineEntity));

        final Boolean result = createOrderFlagSubmarineUseCase
            .invoke(Arrays.asList(orderFlagSubmarineDTO))
            .join();

        Mockito.verify(mockOrderFlagSubmarineRepository)
            .saveAll(Arrays.asList(orderFlagSubmarineEntity));

        Assertions.assertTrue(result);
    }
}
