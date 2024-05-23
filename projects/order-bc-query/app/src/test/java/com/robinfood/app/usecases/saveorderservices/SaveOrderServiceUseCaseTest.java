package com.robinfood.app.usecases.saveorderservices;

import com.robinfood.app.datamocks.dto.input.OrderDTODataMock;
import com.robinfood.app.datamocks.dto.input.RequestOrderTransactionDTOMock;
import com.robinfood.core.dtos.request.order.OrderDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.entities.OrderServicesEntity;
import com.robinfood.repository.orderservices.IOrderServiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveOrderServiceUseCaseTest {

    @Mock
    private IOrderServiceRepository orderServiceRepository;

    @InjectMocks
    private SaveOrderServiceUseCase saveOrderServiceUseCase;

    RequestOrderTransactionDTO requestOrderTransactionDTO;

    OrderDTO orderDTO = new OrderDTODataMock().getDataDefault(4L);

    @Test
    public void Given_Request_With_Services_Then_Save_Data_Services(){

        requestOrderTransactionDTO = RequestOrderTransactionDTOMock.inputOrderTransactionValidatedDTO();

        when(orderServiceRepository.save(any(OrderServicesEntity.class))).thenReturn(any(OrderServicesEntity.class));

        saveOrderServiceUseCase.invoke(requestOrderTransactionDTO, Collections.singletonList(1L));

        verify(orderServiceRepository, times(1)).save(any(OrderServicesEntity.class));
    }

    @Test
    public void Given_Request_Null_Services_Then_Do_Not_Save_Data_Services(){

        requestOrderTransactionDTO = RequestOrderTransactionDTOMock.inputOrderTransactionValidatedWithNullServicesDTO();

        saveOrderServiceUseCase.invoke(requestOrderTransactionDTO, Collections.singletonList(1L));

        verify(orderServiceRepository, times(0)).save(any(OrderServicesEntity.class));
    }

    @Test
    public void Given_Request_Empty_Services_Then_Do_Not_Save_Data_Services(){

        requestOrderTransactionDTO = RequestOrderTransactionDTOMock.inputOrderTransactionValidatedWithoutServicesDTO();

        saveOrderServiceUseCase.invoke(requestOrderTransactionDTO, Collections.singletonList(1L));

        verify(orderServiceRepository, times(0)).save(any(OrderServicesEntity.class));
    }
}
