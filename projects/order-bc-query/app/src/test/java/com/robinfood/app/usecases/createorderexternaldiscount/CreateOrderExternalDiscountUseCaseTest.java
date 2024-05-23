package com.robinfood.app.usecases.createorderexternaldiscount;

import com.robinfood.app.datamocks.dto.input.DeliveryDTODataMock;
import com.robinfood.app.datamocks.entity.OrderExternalDiscountEntityMock;
import com.robinfood.repository.orderexternaldiscount.IOrderExternalDiscountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderExternalDiscountUseCaseTest {

    @Mock
    private IOrderExternalDiscountRepository orderExternalDiscountRepository;

    @InjectMocks
    private CreateOrderExternalDiscountUseCase createOrderExternalDiscountUseCase;

    private final OrderExternalDiscountEntityMock inputOrderExternalDiscountDTODataMock = new OrderExternalDiscountEntityMock();

    @Test
    void test_Create_Order_External_Discount() {
        when(orderExternalDiscountRepository.save(any()))
                .thenReturn(inputOrderExternalDiscountDTODataMock.getDataDefault());

        createOrderExternalDiscountUseCase
                .invoke(new DeliveryDTODataMock().getDataDefault(), 1L);

        verify(orderExternalDiscountRepository, times(1)).save(any());
    }
}
