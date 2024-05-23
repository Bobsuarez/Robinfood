package com.robinfood.app.usecases.createorderdiscountproduct;

import com.robinfood.app.datamocks.dto.input.OrderDTODataMock;
import com.robinfood.core.entities.OrderDiscountEntity;
import com.robinfood.repository.orderdiscount.IOrderDiscountCRUDRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderDiscountProductUseCaseTest {

    @Mock
    private IOrderDiscountCRUDRepository orderDiscountCRUDRepository;

    @InjectMocks
    private CreateOrderDiscountProductUseCase createOrderDiscountProductUseCase;


    private final OrderDTODataMock inputOrderDTODataMock = new OrderDTODataMock();

    @Test
    void test_Create_Order_Product_Discount() {


        final List<OrderDiscountEntity> orderDiscountEntityList = OrderDTODataMock.getOrderDiscountEntityListMock;

        inputOrderDTODataMock.setDiscountDTOList(Collections.emptyList());

        when(orderDiscountCRUDRepository.saveAll(orderDiscountEntityList))
                .thenReturn(orderDiscountEntityList);

        assertDoesNotThrow(() -> createOrderDiscountProductUseCase.invoke(
                inputOrderDTODataMock.getDataDefaultList(),
                Collections.singletonList(1L)).join()
        );
    }
}
