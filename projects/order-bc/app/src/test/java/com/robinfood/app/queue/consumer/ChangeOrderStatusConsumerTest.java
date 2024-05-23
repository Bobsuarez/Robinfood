package com.robinfood.app.queue.consumer;

import com.robinfood.app.usecases.changestatusorders.IChangeOrderStateUseCase;
import com.robinfood.app.usecases.validatechangestate.IValidateChangeStateUseCase;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.dtos.queue.WriteChangeStatusDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangeOrderStatusConsumerTest {

    @Mock
    private IChangeOrderStateUseCase changeOrderStateApiUseCase;

    @Mock
    private IValidateChangeStateUseCase validateChangeStateUseCase;

    @InjectMocks
    private ChangeOrderStatusConsumer changeOrderStatusConsumer;

    @Test
    void change_Order_Status_Consumer_Succes_Test() {

        ChangeOrderStatusDTO changeOrderStatusDTO = new ChangeOrderStatusDTO();
        changeOrderStatusDTO.setOrderId(1L);
        changeOrderStatusDTO.setTransactionId(1L);
        changeOrderStatusDTO.setUserId(1L);

        WriteChangeStatusDTO writeChangeStatusDTO = new WriteChangeStatusDTO();

        when(validateChangeStateUseCase.invoke(changeOrderStatusDTO)).thenReturn(Boolean.TRUE);

        when(changeOrderStateApiUseCase.invoke(changeOrderStatusDTO)).thenReturn(writeChangeStatusDTO);

        changeOrderStatusConsumer.changeStateOrder(changeOrderStatusDTO);

        verify(changeOrderStateApiUseCase, times(1))
                .invoke(changeOrderStatusDTO);
    }
}
