package com.robinfood.app.usecases.createorderflag;

import com.robinfood.core.dtos.request.order.*;
import com.robinfood.core.entities.OrderFlagEntity;
import com.robinfood.repository.orderflag.IOrderFlagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateOrderFlagUseCaseTest {

    @Mock
    private IOrderFlagRepository mockOrderFlagRepository;

    @InjectMocks
    private CreateOrderFlagUseCase createOrderFlagUseCase;

    private final OrderFlagEntity orderFlagEntity = new OrderFlagEntity(
            "integration",
            1L,
            1L
    );

    @Test
    void test_CreateOrderFlag_When_Save_Success() {

        List<OrderFlagEntity> orderFlagEntities = new ArrayList<>();

        orderFlagEntities.add(orderFlagEntity);

        when(mockOrderFlagRepository.saveAll(orderFlagEntities)).thenReturn(orderFlagEntities);

        final Boolean result = createOrderFlagUseCase.invoke(Arrays.asList(
                new IntermediateOrderFlagDTO("integration", 1L, 1L)
        )).join();

        verify(mockOrderFlagRepository)
                .saveAll(orderFlagEntities);

        assertTrue(result);
    }
}
