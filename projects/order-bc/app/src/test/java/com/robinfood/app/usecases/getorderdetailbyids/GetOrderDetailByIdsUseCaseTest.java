package com.robinfood.app.usecases.getorderdetailbyids;

import com.robinfood.app.datamocks.entity.OrderDetailEntityMock;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.repository.orderdetail.IOrderDetailRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderDetailByIdsUseCaseTest {

    @Mock
    private IOrderDetailRepository orderDetailRepository;

    @InjectMocks
    private GetOrderDetailByIdsUseCase getOrderDetailByIdsUseCase;

    private final List<Long> orderIds = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L));

    private final OrderDetailEntityMock orderDetailEntityMock = new OrderDetailEntityMock();

    private final List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>(Arrays.asList(
            new OrderDetailDTO(
                    0.0,
                    true,
                    1L,
                    "34343434",
                    "Notes"
            ),
            new OrderDetailDTO(
                    0.0,
                    true,
                    2L,
                    "34343435",
                    "Notes"
            )
    ));

    @Test
    void test_GetOrderDetail_Returns_Correctly() {
        when(orderDetailRepository.findAllByOrderIdIn(orderIds))
                .thenReturn(orderDetailEntityMock.getDataDefaultListTwo());

        final List<OrderDetailDTO> result = getOrderDetailByIdsUseCase.invoke(orderIds);

        assertEquals(orderDetailDTOS, result);
    }
}