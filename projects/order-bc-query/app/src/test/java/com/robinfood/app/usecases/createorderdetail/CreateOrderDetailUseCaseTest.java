package com.robinfood.app.usecases.createorderdetail;

import com.robinfood.app.datamocks.dto.input.OrderDTODataMock;
import com.robinfood.app.datamocks.dto.input.ServiceDTODataMock;
import com.robinfood.app.datamocks.dto.output.OutputCreatedOrderDTODataMock;
import com.robinfood.app.datamocks.entity.OrderDetailEntityMock;
import com.robinfood.app.usecases.getsumaddproductprice.IGetSumAddProductPriceUseCase;
import com.robinfood.app.usecases.getsumproductsprice.IGetSumProductPriceUseCase;
import com.robinfood.app.usecases.getsumserviceprice.IGetSumServicePriceUseCase;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.entities.OrderDetailEntity;
import com.robinfood.repository.orderdetail.IOrderDetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateOrderDetailUseCaseTest {

    private final OutputCreatedOrderDTODataMock resultCreatedOrderDTOMock = new OutputCreatedOrderDTODataMock();

    private final OrderDetailEntityMock orderDetailEntityMock = new OrderDetailEntityMock();

    private final OrderDTODataMock orderDTOMock = new OrderDTODataMock();

    private final ServiceDTODataMock serviceDTOMock = new ServiceDTODataMock();

    private final List<FinalProductDTO> finalProductDTOList = orderDTOMock.getFinalProductDTOList();

    @Mock
    IGetSumAddProductPriceUseCase mockGetSumAddProductPriceUseCase;

    @Mock
    IGetSumServicePriceUseCase mockGetSumServicePriceUseCase;

    @Mock
    IGetSumProductPriceUseCase mockGetSumProductPriceUseCase;

    @Mock
    private IOrderDetailRepository mockOrderDetailRepository;

    @InjectMocks
    private CreateOrderDetailUseCase createOrderDetailUseCase;

    @Test
    void test_CreateOrderOrderDetail_When_Save_Success() {

        final List<OrderDetailEntity> orderDetailEntities = orderDetailEntityMock.getDataDefaultList();

        when(mockGetSumAddProductPriceUseCase.invoke(finalProductDTOList)).thenReturn(0.0);
        when(mockGetSumServicePriceUseCase.invoke(serviceDTOMock.getDataDefaultList())).thenReturn(0.0);
        when(mockGetSumProductPriceUseCase.invoke(finalProductDTOList)).thenReturn(8900.0);

        when(mockOrderDetailRepository.saveAll(orderDetailEntities)).thenReturn(orderDetailEntities);

        Boolean wasSuccessful = createOrderDetailUseCase.invoke(
                orderDTOMock.getDataDefaultList(),
                resultCreatedOrderDTOMock.getDataDefaultList()
        ).join();

        Assertions.assertTrue(wasSuccessful);

    }
}
