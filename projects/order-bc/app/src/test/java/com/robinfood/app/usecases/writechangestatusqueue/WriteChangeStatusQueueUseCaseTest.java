package com.robinfood.app.usecases.writechangestatusqueue;

import com.robinfood.app.mappers.WriteChangeStatusMapper;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.dtos.queue.WriteChangeStatusDTO;
import com.robinfood.repository.queue.IProducerOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class WriteChangeStatusQueueUseCaseTest {

    @Mock
    private IProducerOrderRepository producerOrderRepository;

    @Mock
    private WriteChangeStatusMapper writeChangeStatusMapper;

    @InjectMocks
    private WriteChangeStatusQueueUseCase writeChangeStatusQueueUseCase;

    @Test
    void test_WriteChangeStatusQueue_Happy_Path () {

        ChangeOrderStatusDTO status = ChangeOrderStatusDTO.builder()
            .notes("pruebas unitarias")
            .orderId(1234L)
            .origin("as")
            .statusCode("WAITING_TO_DELIVERY_ASSIGMENT")
            .statusId(2L)
            .userId(1L)
            .build();

        writeChangeStatusQueueUseCase.invoke(status);

        verify(producerOrderRepository).sendChangeStatusMessage(any());

        verify(writeChangeStatusMapper, times(1)).changeOrderStatusDTOToWriteChangeStatusDTO(any());
    }

    @Test
    void test_WriteChangeStatusQueue_Discard_Path() {

        ChangeOrderStatusDTO status = ChangeOrderStatusDTO.builder()
                .notes("pruebas unitarias")
                .orderId(1234L)
                .origin("as")
                .statusCode("ORDER_DISCARDED")
                .statusId(2L)
                .userId(1L)
                .build();

        WriteChangeStatusDTO result = writeChangeStatusQueueUseCase.invoke(status);
        WriteChangeStatusDTO writeChangeStatusDTO = new WriteChangeStatusDTO();
        assertEquals(result, writeChangeStatusDTO);
    }
}
