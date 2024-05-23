package com.robinfood.app.usecases.changepaidstate;


import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ChangePaidStateUseCaseTest {
    @Mock
    private IOrdersRepository ordersRepository;

    @InjectMocks
    private ChangePaidStateUseCase changePaidStateUseCase;

    private final OrderEntity orderEntityMock = new OrderEntityMock().getDataDefault();

    @Test
    void Change_Paid_Order_State_Success() {
        when(ordersRepository.findById(1L)).thenReturn(Optional.ofNullable(orderEntityMock));
        assertTrue(changePaidStateUseCase.changePaid(1L));

    }

    @Test
    void Change_Paid_Order_State_Exception() {
        when(ordersRepository.findById(1L)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> {
            changePaidStateUseCase.changePaid(1L);
        });

    }
}
