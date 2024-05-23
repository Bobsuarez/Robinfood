package com.robinfood.app.facade;

import com.robinfood.app.facades.GetOrderStateFacade;
import com.robinfood.app.usecases.getstateorders.IGetStateOrderUseCase;
import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GetOrderStateFacadeTest {

    @Mock
    private IGetStateOrderUseCase mockGetStateOrderUseCase;

    @InjectMocks
    private GetOrderStateFacade getOrderStateFacade;

    private final OrderStateDTO orderStateDTO = new OrderStateDTO(
    );

    private final ChangeOrderStatusDTO changeOrderStatusDTO = new ChangeOrderStatusDTO(
    );


    @Test
    void test_isUuidParameter_When_StateDTO_is_Uuid_Should_get_StateDto() {
        changeOrderStatusDTO.setOrderUuid("aaa");

        when(mockGetStateOrderUseCase.invokeUuid("aaa")).thenReturn(orderStateDTO);

        OrderStateDTO result = getOrderStateFacade.invoke(changeOrderStatusDTO);

        assertEquals(result,orderStateDTO);
    }
    @Test
    void test_isUuidParameter_When_StateDTO_is_Integration_Id_Should_get_StateDto() {
        changeOrderStatusDTO.setDeliveryIntegrationId("aaa");

        when(mockGetStateOrderUseCase.invokeDeliveryIntegrationId("aaa")).thenReturn(orderStateDTO);

        OrderStateDTO result = getOrderStateFacade.invoke(changeOrderStatusDTO);

        assertEquals(result,orderStateDTO);
    }

    @Test
    void test_isUuidParameter_When_StateDTO_is_Order_Id_Should_get_StateDto() {

        changeOrderStatusDTO.setOrderId(1L);

        when(mockGetStateOrderUseCase.invoke(1L)).thenReturn(orderStateDTO);

        OrderStateDTO result = getOrderStateFacade.invoke(changeOrderStatusDTO);

        assertEquals(result,orderStateDTO);
    }

}
