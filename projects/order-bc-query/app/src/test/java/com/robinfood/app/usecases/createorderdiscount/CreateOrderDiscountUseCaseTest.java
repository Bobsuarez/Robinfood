package com.robinfood.app.usecases.createorderdiscount;

import com.robinfood.app.datamocks.dto.input.OrderDTODataMock;
import com.robinfood.app.mappers.input.InputDiscountMappers;
import com.robinfood.core.dtos.request.order.DiscountDTO;
import com.robinfood.core.entities.OrderDiscountEntity;
import com.robinfood.core.entities.OrderFinalProductEntity;
import com.robinfood.repository.orderdiscount.IOrderDiscountCRUDRepository;
import com.robinfood.repository.orderdiscount.IOrderDiscountRepository;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import kotlin.collections.CollectionsKt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.ObjectRowListProcessor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderDiscountUseCaseTest {

    @Mock
    private IOrderDiscountCRUDRepository orderDiscountCRUDRepository;

    @Mock
    private IOrderDiscountRepository orderDiscountRepository;

    @Mock
    private  IOrderFinalProductRepository orderFinalProductDataSource;

    @InjectMocks
    private CreateOrderDiscountUseCase createOrderDiscountUseCase;

    private final List<DiscountDTO> orderDiscountDTOList = new ArrayList<>(Collections.singletonList(
            new DiscountDTO(
                    1L,
                    Boolean.TRUE,
                    Boolean.FALSE,
                    1L,
                    1L,
                    1L,
                    100.0
            )
    ));

    private final List<OrderDiscountEntity> orderDiscountEntityList = CollectionsKt.map(
            orderDiscountDTOList,
            InputDiscountMappers::toOrderDiscountEntity
    );

    private final OrderDTODataMock inputOrderDTODataMock = new OrderDTODataMock();

    @Test
    void test_Create_Order_Discount() {

        OrderFinalProductEntity orderFinalProductEntity = new OrderFinalProductEntity();
        orderFinalProductEntity.setFinalProductId(1L);
        orderFinalProductEntity.setId(1L);
        List<OrderFinalProductEntity> listProductFinal = List.of(orderFinalProductEntity);

        when(orderDiscountCRUDRepository.saveAll(orderDiscountEntityList))
                .thenReturn(orderDiscountEntityList);

        when(orderFinalProductDataSource.findAllByOrderId(1L)).thenReturn(listProductFinal);

        Boolean result = createOrderDiscountUseCase
                .invoke(inputOrderDTODataMock.getDataDefaultList(), Collections.singletonList(1L))
                .join();

        verify(orderDiscountCRUDRepository)
                .saveAll(orderDiscountEntityList);

        verify(orderDiscountRepository).setLocalOrderDiscounts(orderDiscountEntityList);

        assertTrue(result);
    }
}