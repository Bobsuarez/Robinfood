package com.robinfood.app.usecases.getuserorderdetail;

import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.usecases.getstatusbyid.IGetStatusByIdUseCase;
import com.robinfood.app.usecases.gettransactionbyid.IGetTransactionFlowByIdUseCase;
import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.dtos.TransactionFlowDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseBrandDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseFinalProductDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseFinalProductGroupDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderAddressDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDetailDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderStatusDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderTraceDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOriginDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponsePaymentDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseServiceDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseStoreDTO;
import com.robinfood.core.entities.OrderDeviceEntity;
import com.robinfood.core.entities.OrderHistoryEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.core.exceptions.ResourceNotFoundException;
import com.robinfood.repository.orderdevice.IOrderDeviceRepository;
import com.robinfood.repository.orderhistory.IOrderHistoryRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Implementation of IGetUserOrderDetailByUIdUseCase
 */
@ExtendWith(MockitoExtension.class)
public class GetUserOrderDetailByUIdUseCaseTest {

    @Mock
    private IOrdersRepository ordersRepository;
    @Mock
    private IOrderDeviceRepository orderDeviceRepository;
    @Mock
    private IOrderHistoryRepository orderHistoryRepository;
    @Mock
    private IGetStatusByIdUseCase getStatusByIdUseCase;
    @Mock
    private IGetTransactionFlowByIdUseCase getTransactionFlowByIdUseCase;
    @Mock
    private IGetUserOrderDetailAdditionalInfoUseCase getUserOrderDetailInfoUseCase;
    @InjectMocks
    private GetUserOrderDetailByUIdUseCase useCase;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(
            useCase,
            "notStatusIds",
            List.of(9L)
        );
    }

    @Test
    void testGetOrderDetailByUserIdWithResultShouldBeOk() throws ResourceNotFoundException {

        List<OrderHistoryEntity> orderHistory = getOrderHistory();

        when(
            ordersRepository.findByUidAndUserIdAndStatusIdNotIn("123", 1L, List.of(9L))
        ).thenReturn(
            Optional.of(new OrderEntityMock().getDataDefault())
        );

        when(
            getTransactionFlowByIdUseCase.invoke(1L)
        ).thenReturn(
            getTransactionFlow()
        );

        when(
            orderDeviceRepository.findFirstByOrderIdOrderByIdDesc(1L)
        ).thenReturn(
            Optional.of(getOrderDeviceEntity())
        );

        when(
            orderHistoryRepository.findAllByOrderId(1L)
        ).thenReturn(
            orderHistory
        );

        when(
            getStatusByIdUseCase.invoke(1L)
        ).thenReturn(
            getOrderStatusDTO()
        );

        when(
            getStatusByIdUseCase.invoke(1L)
        ).thenReturn(
            getOrderStatusDTO()
        );

        when(
            getUserOrderDetailInfoUseCase.invoke(getInitialResponseOrderDetailWithPlatformId())
        ).thenReturn(
            getResponseOrderDetailWithPlatformId()
        );

        ResponseOrderDetailDTO response = useCase.invoke("123", 1L);
        assertNotNull(response);
        assertNotNull(response.getPayment());
        assertNotNull(response.getOrigin());
        assertNotNull(response.getServices());
        assertNotNull(response.getProducts());
        assertNotNull(response.getBrands());
        assertNotNull(response.getAddress());
        assertThat(response.getFlowId(), is(equalTo(1L)));
        assertThat(response.getServices().size(), is(equalTo(1)));
        assertThat(response.getProducts().size(), is(equalTo(1)));
        assertThat(response.getBrands().size(), is(equalTo(1)));
    }

    @Test
    void testGetOrderDetailByUserIdWithoutOrderHistoryShouldBeError() {

        when(
            ordersRepository.findByUidAndUserIdAndStatusIdNotIn("123", 1L, List.of(9L))
        ).thenReturn(
            Optional.of(new OrderEntityMock().getDataDefault())
        );

        when(
            getTransactionFlowByIdUseCase.invoke(1L)
        ).thenReturn(
            getTransactionFlow()
        );

        when(
            orderHistoryRepository.findAllByOrderId(1L)
        ).thenReturn(
            List.of()
        );

        assertThrows(GenericOrderBcException.class,
            () -> useCase.invoke("123", 1L)
        );
    }

    @Test
    void testGetOrderDetailByOrderIdWithOrderNotFoundResultShouldBeError() {

        when(
            ordersRepository.findByUidAndUserIdAndStatusIdNotIn("123", 1L, List.of(9L))
        ).thenReturn(
            Optional.empty()
        );

        assertThrows(ResourceNotFoundException.class,
            () -> useCase.invoke("123", 1L)
        );
    }

    @Test
    void testGetOrderDetailByUserIdWithoutTransactionFlowShouldBeOK() throws ResourceNotFoundException {

        List<OrderHistoryEntity> orderHistory = getOrderHistory();

        when(
            ordersRepository.findByUidAndUserIdAndStatusIdNotIn("123", 1L, List.of(9L))
        ).thenReturn(
            Optional.of(new OrderEntityMock().getDataDefault())
        );

        when(
            getTransactionFlowByIdUseCase.invoke(1L)
        ).thenThrow(GenericOrderBcException.class);

        when(
            orderDeviceRepository.findFirstByOrderIdOrderByIdDesc(1L)
        ).thenReturn(
            Optional.empty()
        );

        when(
            orderHistoryRepository.findAllByOrderId(1L)
        ).thenReturn(
            orderHistory
        );

        when(
            getStatusByIdUseCase.invoke(1L)
        ).thenReturn(
            getOrderStatusDTO()
        );

        when(
            getUserOrderDetailInfoUseCase.invoke(getInitialResponseOrderDetail())
        ).thenReturn(
            getResponseOrderDetail()
        );

        ResponseOrderDetailDTO response = useCase.invoke("123", 1L);
        assertNotNull(response);
        assertNotNull(response.getPayment());
        assertNotNull(response.getOrigin());
        assertNotNull(response.getServices());
        assertNotNull(response.getProducts());
        assertNotNull(response.getBrands());
        assertNotNull(response.getAddress());
        assertThat(response.getFlowId(), is(equalTo(1L)));
        assertThat(response.getServices().size(), is(equalTo(1)));
        assertThat(response.getProducts().size(), is(equalTo(1)));
        assertThat(response.getBrands().size(), is(equalTo(1)));
        assertThat(response.getOrigin().getPlatformId(), is(equalTo(2L)));
    }

    @Test
    void testGetOrderDetailByUserIdWithoutOrderDeviceShouldBeOk() throws ResourceNotFoundException {

        List<OrderHistoryEntity> orderHistory = getOrderHistory();

        when(
            ordersRepository.findByUidAndUserIdAndStatusIdNotIn("123", 1L, List.of(9L))
        ).thenReturn(
            Optional.of(new OrderEntityMock().getDataDefault())
        );

        when(
            getTransactionFlowByIdUseCase.invoke(1L)
        ).thenReturn(
            getTransactionFlow()
        );

        when(
            orderDeviceRepository.findFirstByOrderIdOrderByIdDesc(1L)
        ).thenReturn(
            Optional.empty()
        );

        when(
            orderHistoryRepository.findAllByOrderId(1L)
        ).thenReturn(
            orderHistory
        );

        when(
            getStatusByIdUseCase.invoke(1L)
        ).thenReturn(
            getOrderStatusDTO()
        );

        when(
            getUserOrderDetailInfoUseCase.invoke(getInitialResponseOrderDetail())
        ).thenReturn(
            getResponseOrderDetail()
        );

        ResponseOrderDetailDTO response = useCase.invoke("123", 1L);
        assertNotNull(response);
        assertNotNull(response.getPayment());
        assertNotNull(response.getOrigin());
        assertNotNull(response.getServices());
        assertNotNull(response.getProducts());
        assertNotNull(response.getBrands());
        assertNotNull(response.getAddress());
        assertThat(response.getFlowId(), is(equalTo(1L)));
        assertThat(response.getServices().size(), is(equalTo(1)));
        assertThat(response.getProducts().size(), is(equalTo(1)));
        assertThat(response.getBrands().size(), is(equalTo(1)));
        assertThat(response.getOrigin().getPlatformId(), is(equalTo(2L)));
    }

    private OrderStatusDTO getOrderStatusDTO() {
        return new OrderStatusDTO(
            1L,
            "Pedido"
        );
    }

    private List<OrderHistoryEntity> getOrderHistory() {
        return List.of(
            new OrderHistoryEntity(
                null,
                null,
                1L,
                "Pedido Creado",
                1L,
                1L,
                1L
            )
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

    private ResponseOrderDetailDTO getInitialResponseOrderDetail() {
        return ResponseOrderDetailDTO.builder()
            .id(1L)
            .paid(true)
            .flowId(1L)
            .paymentModelId(1L)
            .origin(
                ResponseOriginDTO.builder()
                    .platformId(2L)
                    .companyId(1L)
                    .store(ResponseStoreDTO.builder()
                        .id(1L)
                        .name("muy 79")
                        .image(StringUtils.EMPTY)
                        .build()
                    )
                    .build()
            )
            .payment(
                ResponsePaymentDTO.builder()
                    .subtotal(8900D)
                    .tax(8900D)
                    .discount(0D)
                    .total(8900D)
                    .co2Total(BigDecimal.ZERO)
                    .build()
            )
            .status(
                ResponseOrderStatusDTO.builder()
                    .id(1L)
                    .name("Pedido")
                    .description("Pedido Creado")
                    .trace(
                        List.of(
                            ResponseOrderTraceDTO.builder()
                                .id(1L)
                                .name("Pedido")
                                .description("Pedido Creado")
                                .build()
                        )
                    )
                    .build()
            )
            .build();
    }

    private ResponseOrderDetailDTO getInitialResponseOrderDetailWithPlatformId() {
        return ResponseOrderDetailDTO.builder()
            .id(1L)
            .paid(true)
            .flowId(1L)
            .paymentModelId(1L)
            .origin(
                ResponseOriginDTO.builder()
                    .companyId(1L)
                    .store(ResponseStoreDTO.builder()
                        .id(1L)
                        .name("muy 79")
                        .image(StringUtils.EMPTY)
                        .build()
                    )
                    .platformId(1L)
                    .build()
            )
            .payment(
                ResponsePaymentDTO.builder()
                    .subtotal(8900D)
                    .tax(8900D)
                    .discount(0D)
                    .total(8900D)
                    .co2Total(BigDecimal.ZERO)
                    .build()
            )
            .status(
                ResponseOrderStatusDTO.builder()
                    .id(1L)
                    .name("Pedido")
                    .description("Pedido Creado")
                    .trace(
                        List.of(
                            ResponseOrderTraceDTO.builder()
                                .id(1L)
                                .name("Pedido")
                                .description("Pedido Creado")
                                .build()
                        )
                    )
                    .build()
            )
            .build();
    }

    private ResponseOrderDetailDTO getResponseOrderDetail() {
        return getInitialResponseOrderDetail().toBuilder()
            .services(
                List.of(
                    ResponseServiceDTO.builder()
                        .id(1L)
                        .name("Test service")
                        .total(2900D)
                        .discount(0D)
                        .total(2900D)
                        .build()
                )
            )
            .address(
                ResponseOrderAddressDTO.builder()
                    .address("CL 1 No 90 - 40")
                    .notes("Edificio Donde Vivo , Apto 009")
                    .latitude("4.666378")
                    .longitude("-74.057391")
                    .zipCode("4324435")
                    .build()
            )
            .brands(
                List.of(
                    ResponseBrandDTO.builder()
                        .id(1L)
                        .name("MUY")
                        .image(StringUtils.EMPTY)
                        .build()
                )
            )
            .products(
                List.of(
                    ResponseFinalProductDTO.builder()
                        .id(1L)
                        .name("Test product")
                        .value(8900D)
                        .co2Total(BigDecimal.ZERO)
                        .price(new BigDecimal("8900.0"))
                        .image(StringUtils.EMPTY)
                        .brandId(1L)
                        .groups(
                            List.of(
                                ResponseFinalProductGroupDTO.builder()
                                    .id(1L)
                                    .name("Test group")
                                    .build()
                            )
                        )
                        .build()
                )
            )
            .build();
    }

    private ResponseOrderDetailDTO getResponseOrderDetailWithPlatformId() {
        return getInitialResponseOrderDetailWithPlatformId().toBuilder()
            .services(
                List.of(
                    ResponseServiceDTO.builder()
                        .id(1L)
                        .name("Test service")
                        .total(2900D)
                        .discount(0D)
                        .total(2900D)
                        .build()
                )
            )
            .address(
                ResponseOrderAddressDTO.builder()
                    .address("CL 1 No 90 - 40")
                    .notes("Edificio Donde Vivo , Apto 009")
                    .latitude("4.666378")
                    .longitude("-74.057391")
                    .zipCode("4324435")
                    .build()
            )
            .brands(
                List.of(
                    ResponseBrandDTO.builder()
                        .id(1L)
                        .name("MUY")
                        .image(StringUtils.EMPTY)
                        .build()
                )
            )
            .products(
                List.of(
                    ResponseFinalProductDTO.builder()
                        .id(1L)
                        .name("Test product")
                        .value(8900D)
                        .co2Total(BigDecimal.ZERO)
                        .price(new BigDecimal("8900.0"))
                        .image(StringUtils.EMPTY)
                        .brandId(1L)
                        .groups(
                            List.of(
                                ResponseFinalProductGroupDTO.builder()
                                    .id(1L)
                                    .name("Test group")
                                    .build()
                            )
                        )
                        .build()
                )
            )
            .build();
    }

}
