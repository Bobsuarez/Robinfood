package com.robinfood.app.usecases.getuserorder;

import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.datamocks.entity.OrderFinalProductEntityMock;
import com.robinfood.app.usecases.getstatusbyid.IGetStatusByIdUseCase;
import com.robinfood.app.usecases.gettransactionbyid.IGetTransactionFlowByIdUseCase;
import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.dtos.TransactionFlowDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDTO;
import com.robinfood.core.entities.OrderDeviceEntity;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderFinalProductEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.orderdevice.IOrderDeviceRepository;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

/**
 * Test of GetUserResponseOrderByEntityUseCase
 */
@ExtendWith(MockitoExtension.class)
public class GetUserResponseOrderByEntityUseCaseTest {

    @Mock
    private IOrderFinalProductRepository orderFinalProductRepository;
    @Mock
    private IGetStatusByIdUseCase getStatusByIdUseCase;
    @Mock
    private IOrderDeviceRepository orderDeviceRepository;
    @Mock
    private IGetTransactionFlowByIdUseCase getTransactionFlowByIdUseCase;
    @InjectMocks
    private GetUserResponseOrderByEntityUseCase useCase;

    @Test
    void testGetResponseOrderByUserIdWithResultShouldBeOk() {

        OrderEntity order = new OrderEntityMock().getDataDefault();
        List<OrderFinalProductEntity> finalProducts = new OrderFinalProductEntityMock().getDataDefaultList();

        when(
            orderFinalProductRepository.findAllByOrderId(1L)
        ).thenReturn(
            finalProducts
        );

        when(
            orderDeviceRepository.findFirstByOrderIdOrderByIdDesc(1L)
        ).thenReturn(
            Optional.of(getOrderDeviceEntity())
        );

        when(
            getTransactionFlowByIdUseCase.invoke(1L)
        ).thenReturn(
            getTransactionFlow()
        );

        when(
            getStatusByIdUseCase.invoke(1L)
        ).thenReturn(
            getOrderStatusDTO()
        );

        ResponseOrderDTO response = useCase.invoke(order);
        assertNotNull(response);
        assertThat(response.getPlatformId(), is(equalTo(1L)));
        assertThat(response.getBrands().size(), is(equalTo(1)));
        assertThat(response.getProducts().size(), is(equalTo(1)));
    }

    @Test
    void testGetResponseOrderByUserIdWithoutDeviceShouldBeOk() {

        OrderEntity order = new OrderEntityMock().getDataDefault();
        List<OrderFinalProductEntity> finalProducts = new OrderFinalProductEntityMock().getDataDefaultList();

        when(
            orderFinalProductRepository.findAllByOrderId(1L)
        ).thenReturn(
            finalProducts
        );

        when(
            orderDeviceRepository.findFirstByOrderIdOrderByIdDesc(1L)
        ).thenReturn(
            Optional.empty()
        );

        when(
            getTransactionFlowByIdUseCase.invoke(1L)
        ).thenReturn(
            getTransactionFlow()
        );

        when(
            getStatusByIdUseCase.invoke(1L)
        ).thenReturn(
            getOrderStatusDTO()
        );

        ResponseOrderDTO response = useCase.invoke(order);
        assertNotNull(response);
        assertNull(response.getPlatformId());
        assertThat(response.getBrands().size(), is(equalTo(1)));
        assertThat(response.getProducts().size(), is(equalTo(1)));
    }

    @Test
    void testGetResponseOrderByUserIdWithoutStatusAndTransactionFlowShouldBeOk() {

        OrderEntity order = new OrderEntityMock().getDataDefault();
        List<OrderFinalProductEntity> finalProducts = new OrderFinalProductEntityMock().getDataDefaultList();

        when(
            getTransactionFlowByIdUseCase.invoke(1L)
        ).thenThrow(GenericOrderBcException.class);

        when(
            orderFinalProductRepository.findAllByOrderId(1L)
        ).thenReturn(
            finalProducts
        );

        when(
            orderDeviceRepository.findFirstByOrderIdOrderByIdDesc(1L)
        ).thenReturn(
            Optional.of(getOrderDeviceEntity())
        );

        when(
            getStatusByIdUseCase.invoke(1L)
        ).thenThrow(NotFoundException.class);

        ResponseOrderDTO response = useCase.invoke(order);
        assertNotNull(response);
        assertThat(response.getPlatformId(), is(equalTo(1L)));
        assertThat(response.getBrands().size(), is(equalTo(1)));
        assertThat(response.getProducts().size(), is(equalTo(1)));
        assertThat(response.getFlowId(), is(equalTo(1L)));
        assertNull(response.getStatus());
    }

    private OrderStatusDTO getOrderStatusDTO() {
        return new OrderStatusDTO(
            1L,
            "Pedido"
        );
    }

    private TransactionFlowDTO getTransactionFlow() {
        return new TransactionFlowDTO(
            LocalDateTime.now(),
            1L,
            1L,
            21321L,
            LocalDateTime.now()
        );
    }

    private OrderDeviceEntity getOrderDeviceEntity() {
        return new OrderDeviceEntity(
            LocalDateTime.now(),
            1L,
            "127.0.0.1",
            1L,
            1L,
            "1.0.0"
        );
    }

}
