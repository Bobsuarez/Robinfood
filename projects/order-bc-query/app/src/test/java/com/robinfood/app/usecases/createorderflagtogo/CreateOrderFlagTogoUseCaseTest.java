package com.robinfood.app.usecases.createorderflagtogo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.request.order.OrderFlagTogoDTO;
import com.robinfood.core.entities.OrderFlagTogoEntity;
import com.robinfood.repository.orderflagtogo.IOrderFlagTogoRepository;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateOrderFlagTogoUseCaseTest {

    @Mock
    private IOrderFlagTogoRepository mockOrderFlagTogoRepository;

    @InjectMocks
    private CreateOrderFlagTogoUseCase createOrderFlagTogoUseCase;

    private final OrderFlagTogoEntity orderFlagTogoEntity = new OrderFlagTogoEntity(
        null,
        1L,
        1L,
        1L
    );

    private final OrderFlagTogoDTO orderFlagTogoDTO = new OrderFlagTogoDTO(
        1L,
        true,
        1L,
        1L
    );

    @Test
    void test_CreateOrderFlagTogo_When_Save_Success() {

        when(mockOrderFlagTogoRepository.saveAll(Arrays.asList(orderFlagTogoEntity)))
            .thenReturn(Arrays.asList(orderFlagTogoEntity));

        final Boolean result = createOrderFlagTogoUseCase
            .invoke(Arrays.asList(orderFlagTogoDTO))
            .join();

        verify(mockOrderFlagTogoRepository)
            .saveAll(Arrays.asList(orderFlagTogoEntity));

        assertTrue(result);
    }
}
