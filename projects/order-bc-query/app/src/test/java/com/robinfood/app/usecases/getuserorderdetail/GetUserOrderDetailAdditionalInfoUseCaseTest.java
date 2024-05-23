package com.robinfood.app.usecases.getuserorderdetail;

import com.robinfood.app.datamocks.dto.core.OrderDiscountDTOMock;
import com.robinfood.app.datamocks.entity.OrderFinalProductEntityMock;
import com.robinfood.app.datamocks.entity.OrderFinalProductPortionEntityMock;
import com.robinfood.app.usecases.getorderaddressbyorderid.IGetOrderAddressByOrderIdUseCase;
import com.robinfood.app.usecases.getorderdiscountbyorderids.IGetOrderDiscountsByOrderIdUseCase;
import com.robinfood.app.usecases.getorderpaymentbyorderids.IGetOrderPaymentByOrderIdUseCase;
import com.robinfood.app.usecases.getresponsepaymentmethodbyorder.IGetResponsePaymentMethodByOrderUseCase;
import com.robinfood.app.usecases.getresponseservicebyorder.IGetResponseServiceByOrderUseCase;
import com.robinfood.core.dtos.OrderAddressDTO;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDetailDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderStatusDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderTraceDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOriginDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponsePaymentDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponsePaymentMethodDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseServiceDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseStoreDTO;
import com.robinfood.core.entities.OrderFinalProductEntity;
import com.robinfood.core.entities.OrderFinalProductPortionEntity;
import com.robinfood.core.entities.OrderServicesEntity;
import com.robinfood.repository.orderfinalproductportions.IOrderFinalProductPortionRepository;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import com.robinfood.repository.orderservices.IOrderServiceRepository;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Test of GetUserOrderDetailAdditionalInfoUseCase
 */
@ExtendWith(MockitoExtension.class)
public class GetUserOrderDetailAdditionalInfoUseCaseTest {

    @Mock
    private IOrderFinalProductRepository orderFinalProductRepository;
    @Mock
    private IOrderFinalProductPortionRepository orderFinalProductPortionRepository;
    @Mock
    private IOrderServiceRepository orderServiceRepository;
    @Mock
    private IGetResponseServiceByOrderUseCase getServiceByIdUseCase;
    @Mock
    private IGetResponsePaymentMethodByOrderUseCase getPaymentMethodByIdUseCase;
    @Mock
    private IGetOrderDiscountsByOrderIdUseCase getOrderDiscountsByOrderIdUseCase;
    @Mock
    private IGetOrderPaymentByOrderIdUseCase getOrderPaymentByOrderIdUseCase;
    @Mock
    private IGetOrderAddressByOrderIdUseCase getOrderAddressByOrderIdUseCase;
    @InjectMocks
    private GetUserOrderDetailAdditionalInfoUseCase useCase;

    @Test
    void testGetOrderDetailByUserIdWithResultShouldBeOk() {

        List<OrderFinalProductEntity> finalProducts = new OrderFinalProductEntityMock()
            .getDataDefaultListWithId();
        List<OrderFinalProductPortionEntity> finalProductPortions = new OrderFinalProductPortionEntityMock()
            .getDataDefaultList();

        when(
            orderFinalProductRepository.findAllByOrderId(1L)
        ).thenReturn(
            finalProducts
        );

        when(
            orderServiceRepository.findAllByOrderId(1L)
        ).thenReturn(
            List.of(getOrderServiceEntity())
        );

        when(
            getServiceByIdUseCase.invoke(any(OrderServicesEntity.class))
        ).thenReturn(
            Optional.of(getResponseService())
        );

        when(
            getOrderDiscountsByOrderIdUseCase.invoke(1L)
        ).thenReturn(
            List.of(OrderDiscountDTOMock.build())
        );

        when(
            getOrderPaymentByOrderIdUseCase.invoke(1L)
        ).thenReturn(
            List.of(getOrderPaymentDefault())
        );

        when(
            getPaymentMethodByIdUseCase.invoke(any(OrderPaymentDTO.class))
        ).thenReturn(
            Optional.of(getResponsePaymentMethod())
        );

        when(
            orderFinalProductPortionRepository
                .findOrderFinalProductPortionEntityByOrderFinalProductId(1L)
        ).thenReturn(
            finalProductPortions
        );

        when(
            getOrderAddressByOrderIdUseCase
                .invoke(1L)
        ).thenReturn(
            getOrderAddress()
        );

        ResponseOrderDetailDTO response = useCase.invoke(getInitialResponseOrderDetail());
        assertNotNull(response);
        assertNotNull(response.getOrigin());
        assertNotNull(response.getServices());
        assertNotNull(response.getProducts());
        assertNotNull(response.getBrands());
        assertNotNull(response.getAddress());
        assertThat(response.getServices().size(), is(equalTo(1)));
        assertThat(response.getProducts().size(), is(equalTo(1)));
        assertThat(response.getBrands().size(), is(equalTo(1)));
    }

    @Test
    void testGetOrderDetailByUserIdWithoutValidServiceShouldBeOk() {

        List<OrderFinalProductEntity> finalProducts = new OrderFinalProductEntityMock()
            .getDataDefaultListWithId();
        List<OrderFinalProductPortionEntity> finalProductPortions = new OrderFinalProductPortionEntityMock()
            .getDataDefaultList();

        when(
            orderFinalProductRepository.findAllByOrderId(1L)
        ).thenReturn(
            finalProducts
        );

        when(
            orderServiceRepository.findAllByOrderId(1L)
        ).thenReturn(
            List.of(getOrderServiceEntity())
        );

        when(
            getServiceByIdUseCase.invoke(any(OrderServicesEntity.class))
        ).thenReturn(Optional.empty());

        when(
            getOrderDiscountsByOrderIdUseCase.invoke(1L)
        ).thenReturn(
            List.of(OrderDiscountDTOMock.build())
        );

        when(
            getOrderPaymentByOrderIdUseCase.invoke(1L)
        ).thenReturn(
            List.of(getOrderPaymentDefault())
        );

        when(
            getPaymentMethodByIdUseCase.invoke(any(OrderPaymentDTO.class))
        ).thenReturn(
            Optional.of(getResponsePaymentMethod())
        );

        when(
            orderFinalProductPortionRepository
                .findOrderFinalProductPortionEntityByOrderFinalProductId(1L)
        ).thenReturn(
            finalProductPortions
        );

        when(
            getOrderAddressByOrderIdUseCase
                .invoke(1L)
        ).thenReturn(
            getOrderAddress()
        );

        ResponseOrderDetailDTO response = useCase.invoke(getInitialResponseOrderDetail());
        assertNotNull(response);
        assertNotNull(response.getOrigin());
        assertNotNull(response.getServices());
        assertNotNull(response.getProducts());
        assertNotNull(response.getBrands());
        assertNotNull(response.getAddress());
        assertTrue(response.getServices().isEmpty());
        assertThat(response.getProducts().size(), is(equalTo(1)));
        assertThat(response.getBrands().size(), is(equalTo(1)));
    }

    @Test
    void testGetOrderDetailByUserIdWithoutValidPaymentMethodShouldBeOk() {

        List<OrderFinalProductEntity> finalProducts = new OrderFinalProductEntityMock()
            .getDataDefaultListWithId();
        List<OrderFinalProductPortionEntity> finalProductPortions = new OrderFinalProductPortionEntityMock()
            .getDataDefaultList();

        when(
            orderFinalProductRepository.findAllByOrderId(1L)
        ).thenReturn(
            finalProducts
        );

        when(
            orderServiceRepository.findAllByOrderId(1L)
        ).thenReturn(
            List.of(getOrderServiceEntity())
        );

        when(
            getServiceByIdUseCase.invoke(any(OrderServicesEntity.class))
        ).thenReturn(
            Optional.of(getResponseService())
        );

        when(
            getOrderDiscountsByOrderIdUseCase.invoke(1L)
        ).thenReturn(
            List.of(OrderDiscountDTOMock.build())
        );

        when(
            getOrderPaymentByOrderIdUseCase.invoke(1L)
        ).thenReturn(
            List.of(getOrderPaymentDefault())
        );

        when(
            getPaymentMethodByIdUseCase.invoke(any(OrderPaymentDTO.class))
        ).thenReturn(
            Optional.empty()
        );

        when(
            orderFinalProductPortionRepository
                .findOrderFinalProductPortionEntityByOrderFinalProductId(1L)
        ).thenReturn(
            finalProductPortions
        );

        when(
            getOrderAddressByOrderIdUseCase
                .invoke(1L)
        ).thenReturn(
            getOrderAddress()
        );

        ResponseOrderDetailDTO response = useCase.invoke(getInitialResponseOrderDetail());
        assertNotNull(response);
        assertNotNull(response.getOrigin());
        assertNotNull(response.getServices());
        assertNotNull(response.getProducts());
        assertNotNull(response.getBrands());
        assertNotNull(response.getAddress());
        assertNotNull(response.getPayment().getMethods());
        assertTrue(response.getPayment().getMethods().isEmpty());
        assertThat(response.getServices().size(), is(equalTo(1)));
        assertThat(response.getProducts().size(), is(equalTo(1)));
        assertThat(response.getBrands().size(), is(equalTo(1)));
    }

    @Test
    void testGetOrderDetailByUserIdWithoutAddressShouldBeOk() {

        List<OrderFinalProductEntity> finalProducts = new OrderFinalProductEntityMock()
            .getDataDefaultListWithId();
        List<OrderFinalProductPortionEntity> finalProductPortions = new OrderFinalProductPortionEntityMock()
            .getDataDefaultList();

        when(
            orderFinalProductRepository.findAllByOrderId(1L)
        ).thenReturn(
            finalProducts
        );

        when(
            orderServiceRepository.findAllByOrderId(1L)
        ).thenReturn(
            List.of(getOrderServiceEntity())
        );

        when(
            getServiceByIdUseCase.invoke(any(OrderServicesEntity.class))
        ).thenReturn(
            Optional.of(getResponseService())
        );

        when(
            getOrderDiscountsByOrderIdUseCase.invoke(1L)
        ).thenReturn(
            List.of(OrderDiscountDTOMock.build())
        );

        when(
            getOrderPaymentByOrderIdUseCase.invoke(1L)
        ).thenReturn(
            List.of(getOrderPaymentDefault())
        );

        when(
            getPaymentMethodByIdUseCase.invoke(any(OrderPaymentDTO.class))
        ).thenReturn(
            Optional.of(getResponsePaymentMethod())
        );

        when(
            orderFinalProductPortionRepository
                .findOrderFinalProductPortionEntityByOrderFinalProductId(1L)
        ).thenReturn(
            finalProductPortions
        );

        when(
            getOrderAddressByOrderIdUseCase.invoke(1L)
        ).thenReturn(
            null
        );

        ResponseOrderDetailDTO response = useCase.invoke(getInitialResponseOrderDetail());
        assertNotNull(response);
        assertNotNull(response.getOrigin());
        assertNotNull(response.getServices());
        assertNotNull(response.getProducts());
        assertNotNull(response.getBrands());
        assertNull(response.getAddress());
        assertThat(response.getServices().size(), is(equalTo(1)));
        assertThat(response.getProducts().size(), is(equalTo(1)));
        assertThat(response.getBrands().size(), is(equalTo(1)));
    }

    private OrderServicesEntity getOrderServiceEntity() {
        return new OrderServicesEntity(
            LocalDateTime.now(),
            0.0,
            1L,
            1L,
            1000.0,
            1L,
            0.0,
            0.0,
            1000.0
        );
    }

    private ResponseServiceDTO getResponseService() {
        return ResponseServiceDTO.builder()
            .id(1L)
            .name("services")
            .subtotal(1000.0)
            .tax(0.0)
            .total(1000.0)
            .discount(0.0)
            .build();
    }

    private OrderPaymentDTO getOrderPaymentDefault() {
        return new OrderPaymentDTO(
            null,
            0.0,
            1L,
            1L,
            1L,
            1L,
            8900.0,
            0.0,
            8900.0
        );
    }

    private ResponsePaymentMethodDTO getResponsePaymentMethod() {
        return ResponsePaymentMethodDTO.builder()
            .id(1L)
            .name("cash")
            .value(1000.0)
            .build();
    }

    private ResponseOrderDetailDTO getInitialResponseOrderDetail() {
        return ResponseOrderDetailDTO.builder()
            .id(1L)
            .paid(true)
            .flowId(1L)
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

    private OrderAddressDTO getOrderAddress() {
        return OrderAddressDTO.builder()
            .address("CL 1 No 90 - 40")
            .notes("Edificio Donde Vivo , Apto 009")
            .latitude("4.666378")
            .longitude("-74.057391")
            .zipCode("4324435")
            .build();
    }

}
