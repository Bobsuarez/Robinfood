package com.robinfood.changestatusor.queue.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.changestatusor.consumer.ChangeOrderStatusConsumer;
import com.robinfood.changestatusor.datamock.ChangeOrderStatusDTOMock;
import com.robinfood.changestatusor.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusor.usecases.changeorderstatus.IChangeOrderStatusUseCase;
import com.robinfood.changestatusor.usecases.validatechangestate.IValidateChangeStateUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeOrderStatusConsumerTest {

    @Mock
    private IChangeOrderStatusUseCase changeOrderStatusUseCase;

    @Mock
    private IValidateChangeStateUseCase validateChangeStateUseCase;

    @InjectMocks
    private ChangeOrderStatusConsumer changeOrderStatusConsumer;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void change_Order_Status_Consumer_Success_Test() throws JsonProcessingException {

        ChangeOrderStatusDTO changeOrderStatusDTO = ChangeOrderStatusDTOMock.getDefaultData();

        when(validateChangeStateUseCase.invoke(changeOrderStatusDTO)).thenReturn(Boolean.TRUE);

        when(changeOrderStatusUseCase.invoke(changeOrderStatusDTO)).thenReturn(changeOrderStatusDTO);

        changeOrderStatusConsumer.changeStateOrder(ChangeOrderStatusDTOMock.json);

        verify(changeOrderStatusUseCase, times(1))
                .invoke(changeOrderStatusDTO);
    }
}
