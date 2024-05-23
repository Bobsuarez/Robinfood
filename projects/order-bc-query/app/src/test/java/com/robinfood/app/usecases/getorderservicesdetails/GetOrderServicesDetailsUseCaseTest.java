package com.robinfood.app.usecases.getorderservicesdetails;

import com.robinfood.app.strategies.orderhistoryfilter.strategies.OrderHistoryFilterBasicStrategy;
import com.robinfood.app.usecases.getresponseservicebyorder.IGetResponseServiceByOrderUseCase;
import com.robinfood.core.dtos.DeliveryTypeDTO;
import com.robinfood.core.dtos.request.orderhistory.OrderHistoryRequestDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseServiceDTO;
import com.robinfood.core.entities.OrderServicesEntity;
import com.robinfood.core.exceptions.ResourceNotFoundException;
import com.robinfood.repository.orderservices.IOrderServiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderServicesDetailsUseCaseTest {

    @InjectMocks
    private GetOrderServicesUseCase getOrderServicesUseCase;

    @Mock
    private IGetResponseServiceByOrderUseCase getResponseServiceByOrderUseCase;

    @Mock
    private IOrderServiceRepository orderServiceRepository;

    @Test
    void test_invoke_Should_OrderHistoryList_When_InvokeTheUseCase() throws ResourceNotFoundException {

        when(orderServiceRepository.findAllByOrderId(anyLong()))
                .thenReturn(List.of(OrderServicesEntity.builder().build()));

        when(getResponseServiceByOrderUseCase.invoke(any(OrderServicesEntity.class)))
                .thenReturn(Optional.ofNullable(ResponseServiceDTO.builder().build()));

        getOrderServicesUseCase.invoke(anyLong());
    }
}
