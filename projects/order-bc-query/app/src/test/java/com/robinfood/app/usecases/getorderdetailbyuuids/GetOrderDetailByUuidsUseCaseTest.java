package com.robinfood.app.usecases.getorderdetailbyuuids;

import com.robinfood.app.usecases.getorderdetailorder.IGetOrderDetailUseCase;
import com.robinfood.core.dtos.GetOrderDetailDTO;
import com.robinfood.repository.orders.IOrdersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrderDetailByUuidsUseCaseTest {

    @Mock
    private IGetOrderDetailUseCase getOrderDetailUseCase;

    @Mock
    private IOrdersRepository ordersRepository;

    @InjectMocks
    private GetOrderDetailByUuidsUseCase getOrderDetailByUuidsUseCase;

    @Test
    void test_Get_Order_Detail_By_Uuids_UseCase(){
        when(ordersRepository.findAllByUuidIn(anyList()))
                .thenReturn(List.of());

        when(getOrderDetailUseCase.invoke(anyList()))
                .thenReturn(List.of());

        List<GetOrderDetailDTO> orderDetailDTOS = getOrderDetailByUuidsUseCase.invoke(List.of("uuid"));

        verify(ordersRepository).findAllByUuidIn(anyList());
        verify(getOrderDetailUseCase).invoke(anyList());
    }

}