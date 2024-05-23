package com.robinfood.changestatusbc.usecases.changepaidstate;

import com.robinfood.changestatusbc.entities.OrderEntity;
import com.robinfood.changestatusbc.repositories.orders.IOrdersRepository;
import datamock.OrderEntityMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangePaidStateUseCaseTest {

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