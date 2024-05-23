package com.robinfood.app.usecases.createorder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;

import com.robinfood.app.datamocks.dto.input.DeliveryDTODataMock;
import com.robinfood.app.datamocks.dto.input.FinalProductTaxDTODataMock;
import com.robinfood.app.datamocks.dto.input.OrderDTODataMock;
import com.robinfood.app.datamocks.dto.input.OrderFiscalIdentifierDTODataMock;
import com.robinfood.app.datamocks.dto.input.RequestPaymentDetailDTODataMock;
import com.robinfood.app.datamocks.dto.input.UserDataDTODataMock;
import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.usecases.createdorderdeduction.ICreateOrderDeductionsUseCase;
import com.robinfood.app.usecases.createorderfinalproduct.CreateOrderFinalProductUseCase;
import com.robinfood.app.usecases.getstatusbyid.IGetStatusByIdUseCase;
import com.robinfood.app.usecases.getsumdiscountprice.IGetSumDiscountPriceUseCase;
import com.robinfood.app.usecases.getsumproducttaxesprice.IGetSumProductTaxPriceUseCase;
import com.robinfood.core.dtos.OrderCouponDTO;
import com.robinfood.core.dtos.request.order.FinalProductTaxDTO;
import com.robinfood.core.dtos.request.transaction.RequestCompanyDTO;
import com.robinfood.core.dtos.request.transaction.RequestDeviceDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO;
import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.exceptions.CannotDivideByZeroException;
import com.robinfood.core.helpers.UuidHelper;
import com.robinfood.repository.orders.IOrdersRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {

    private final OrderEntityMock orderEntityMock = new OrderEntityMock();

    private final OrderDTODataMock orderDTOMock = new OrderDTODataMock();

    private final FinalProductTaxDTODataMock finalProductTaxDTOMock = new FinalProductTaxDTODataMock();

    private final List<FinalProductTaxDTO> finalProductTaxDTOList = finalProductTaxDTOMock.getDataDefaultList();

    @Mock
    private IGetStatusByIdUseCase mockGetStatusByIdUseCase;

    @Mock
    private IGetSumProductTaxPriceUseCase mockGetSumProductTaxPriceUseCase;

    @Mock
    private IGetSumDiscountPriceUseCase mockGetSumDiscountPriceUseCase;

    @Mock
    private IOrdersRepository mockOrdersRepository;

    @Mock
    private ICreateOrderDeductionsUseCase mockCreateOrderDeduction;

    @Mock
    private EntityManager entityManager;

    @Mock
    private CreateOrderFinalProductUseCase createOrderFinalProductUseCase;

    @InjectMocks
    private CreateOrderUseCase createOrderUseCase;


    private final OrderDTODataMock orderDTODataMock = new OrderDTODataMock();
    private final RequestPaymentDetailDTODataMock paymentDetailDTODataMock = new RequestPaymentDetailDTODataMock();
    private final UserDataDTODataMock userDataDTODataMock = new UserDataDTODataMock();

    private final List<RequestPaymentMethodDTO> requestPaymentMethodDTOList = new ArrayList<>(Collections.singletonList(
            new RequestPaymentMethodDTO(
                    paymentDetailDTODataMock.getDataDefault(),
                    1L,
                    1L,
                    100.0
            )
    ));

    private final List<RequestPaymentMethodDTO> requestPaymentsPaidDTOList = new ArrayList<>(Collections.singletonList(
            new RequestPaymentMethodDTO(
                    paymentDetailDTODataMock.getDataDefault(),
                    7L,
                    4L,
                    1000.0
            )
    ));

    private final RequestOrderTransactionDTO inputOrderTransactionValidatedDTO = new RequestOrderTransactionDTO(
            true,
            new RequestCompanyDTO("COP", 1L),
            List.of(OrderCouponDTO.builder()
                    .code("COUPONOK")
                    .value(BigDecimal.valueOf(1000L))
                    .redeemedId("1234")
                    .transactionId(1L)
                    .couponType(1L)
                    .build()),
            new DeliveryDTODataMock().getDataDefault(),
            new RequestDeviceDTO("172.80.4.207", 1L, "America/Bogota", "1.5"),
            3L,
            orderDTODataMock.getDataDefaultList(),
            new OrderFiscalIdentifierDTODataMock().getDataDefault(),
            true,
            requestPaymentMethodDTOList,
            requestPaymentsPaidDTOList,
            BigDecimal.ZERO,
            100.0,
            userDataDTODataMock.getDataDefault(),
            1L,
            UUID.randomUUID()
    );
    private final RequestOrderTransactionDTO inputOrderTransactionBrasilValidatedDTO = new RequestOrderTransactionDTO(
            Boolean.TRUE,
            new RequestCompanyDTO("BRL", 1L),
            List.of(),
            new DeliveryDTODataMock().getDataDefault(),
            new RequestDeviceDTO("172.80.4.207", 1L, "America/Bogota", "1.5"),
            3L,
            orderDTODataMock.getDataDefaultList(),
            new OrderFiscalIdentifierDTODataMock().getDataDefault(),
            true,
            requestPaymentMethodDTOList,
            requestPaymentsPaidDTOList,
            BigDecimal.valueOf(100),
            100.0,
            userDataDTODataMock.getDataDefault(),
            1L,
            UUID.randomUUID()
    );

    @Test
    void test_CreateOrder_When_Save_Success_Paid() throws CannotDivideByZeroException {
        final List<OrderEntity> orderEntities = orderEntityMock.getDataDefaultListTwo();
        orderEntities.get(0).setDeliveryTypeId(3L);

        final Long posId = orderEntities.get(0).getPosId();
        final String expectedUid = orderEntities.get(0).getUid();

        Assertions.assertNotEquals(UuidHelper.getByLong(posId), orderEntities.get(0).getUid());

        try (MockedStatic<UuidHelper> mockUuidHelper = Mockito.mockStatic(UuidHelper.class)) {
            mockUuidHelper.when(() -> UuidHelper.getByLong(posId)).thenReturn(expectedUid);

            Mockito.when(mockOrdersRepository.saveAll(any())).thenReturn(orderEntities);
            Mockito.when(mockGetStatusByIdUseCase.invoke(any())).thenReturn(null);

            Mockito.when(mockOrdersRepository.findAllById(any()))
                    .thenReturn(orderEntities);


            lenient().when(createOrderFinalProductUseCase.invoke(
                    orderDTOMock.getFinalProductDTOList(),
                    1L,
                    1L,
                    1L,
                    true
            )).thenReturn(CompletableFuture.completedFuture(true));

            final List<ResponseCreatedOrderDTO> result = createOrderUseCase
                    .invoke(
                            inputOrderTransactionValidatedDTO,
                            orderDTOMock.getDataWithDeliveryTypeIdThree(),
                            1L, 10000.0
                    );

            mockUuidHelper.verify(
                    () -> UuidHelper.getByLong(posId), times(1)
            );

            final List<ResponseCreatedOrderDTO> expectedResponse = Collections.singletonList(
                    orderDTOMock.getResponseCreatedOrder()
            );

            assertEquals(expectedResponse, result);
        }

        Assertions.assertNotEquals(UuidHelper.getByLong(posId), orderEntities.get(0).getUid());

    }

    @Test
    void test_CreateOrder_When_Save_Success_Not_Paid() throws CannotDivideByZeroException {
        final List<OrderEntity> orderEntities = orderEntityMock.getDataDefaultListThree();
        final Long posId = orderEntities.get(0).getPosId();
        final String expectedUid = orderEntities.get(0).getUid();

        Assertions.assertNotEquals(UuidHelper.getByLong(posId), orderEntities.get(0).getUid());

        try (MockedStatic<UuidHelper> mockUuidHelper = Mockito.mockStatic(UuidHelper.class)) {
            mockUuidHelper.when(() -> UuidHelper.getByLong(posId)).thenReturn(expectedUid);

            Mockito.when(mockOrdersRepository.saveAll(any())).thenReturn(orderEntities);
            Mockito.when(mockGetStatusByIdUseCase.invoke(any())).thenReturn(null);

            Mockito.when(mockOrdersRepository.findAllById(any()))
                    .thenReturn(orderEntities);

            lenient().when(createOrderFinalProductUseCase.invoke(
                    orderDTOMock.getFinalProductDTOList(),
                    1L,
                    1L,
                    1L,
                    false
            )).thenReturn(CompletableFuture.completedFuture(true));

            final List<ResponseCreatedOrderDTO> result = createOrderUseCase
                    .invoke(
                            inputOrderTransactionValidatedDTO,
                            orderDTOMock.getDataDefaultListNotPaid(),
                            1L, 10000.0
                    );

            mockUuidHelper.verify(
                    () -> UuidHelper.getByLong(posId),
                    times(1)
            );

            final List<ResponseCreatedOrderDTO> expectedResponse = Collections.singletonList(
                    orderDTOMock.getResponseCreatedOrder()
            );

            assertEquals(expectedResponse, result);
        }

        Assertions.assertNotEquals(UuidHelper.getByLong(posId), orderEntities.get(0).getUid());

    }

    @Test
    void test_CreateOrder_When_Save_Success_Paid_And_Enabled_Trigger() throws CannotDivideByZeroException {
        final List<OrderEntity> orderEntities = orderEntityMock.getDataDefaultListTwo();
        orderEntities.get(0).setDeliveryTypeId(3L);

        final Long posId = orderEntities.get(0).getPosId();
        final String expectedUid = orderEntities.get(0).getUid();

        Assertions.assertNotEquals(UuidHelper.getByLong(posId), orderEntities.get(0).getUid());

        try (MockedStatic<UuidHelper> mockUuidHelper = Mockito.mockStatic(UuidHelper.class)) {
            mockUuidHelper.when(() -> UuidHelper.getByLong(posId)).thenReturn(expectedUid);


            Mockito.when(mockOrdersRepository.saveAll(any())).thenReturn(orderEntities);
            Mockito.when(mockGetStatusByIdUseCase.invoke(any())).thenReturn(null);

            Mockito.when(mockOrdersRepository.findAllById(any()))
                    .thenReturn(orderEntities);

            lenient().when(createOrderFinalProductUseCase.invoke(
                    orderDTOMock.getFinalProductDTOList(),
                    1L,
                    1L,
                    1L,
                    true
            )).thenReturn(CompletableFuture.completedFuture(true));

            final List<ResponseCreatedOrderDTO> result = createOrderUseCase
                    .invoke(
                            inputOrderTransactionValidatedDTO,
                            orderDTOMock.getDataWithDeliveryTypeIdThree(),
                            1L, 10000.0
                    );

            mockUuidHelper.verify(
                    () -> UuidHelper.getByLong(posId),
                    times(1)

            );

            final List<ResponseCreatedOrderDTO> expectedResponse = Collections.singletonList(
                    orderDTOMock.getResponseCreatedOrder()
            );

            assertEquals(expectedResponse, result);
        }

        Assertions.assertNotEquals(UuidHelper.getByLong(posId), orderEntities.get(0).getUid());

    }

    @Test
    void test_CreateOrder_When_Save_No_Discount_Product() throws CannotDivideByZeroException {

        final List<OrderEntity> orderEntities = orderEntityMock.getDataDefaultListTwo();
        orderEntities.get(0).setDeliveryTypeId(3L);

        final Long posId = orderEntities.get(0).getPosId();
        final String expectedUid = orderEntities.get(0).getUid();

        Assertions.assertNotEquals(UuidHelper.getByLong(posId), orderEntities.get(0).getUid());

        try (MockedStatic<UuidHelper> mockUuidHelper = Mockito.mockStatic(UuidHelper.class)) {

            mockUuidHelper.when(() -> UuidHelper.getByLong(posId)).thenReturn(expectedUid);

            Mockito.when(mockOrdersRepository.saveAll(any())).thenReturn(orderEntities);
            Mockito.when(mockGetStatusByIdUseCase.invoke(any())).thenReturn(null);

            Mockito.when(mockOrdersRepository.findAllById(any()))
                    .thenReturn(orderEntities);

            lenient().when(createOrderFinalProductUseCase.invoke(
                    orderDTOMock.getFinalProductDTOList(),
                    1L,
                    1L,
                    1L,
                    true
            )).thenReturn(CompletableFuture.completedFuture(true));

            final List<ResponseCreatedOrderDTO> result = createOrderUseCase
                    .invoke(
                            inputOrderTransactionValidatedDTO,
                            orderDTOMock.getData_No_Product_Discount(),
                            1L, 10000.0
                    );

            mockUuidHelper.verify(
                    () -> UuidHelper.getByLong(posId),
                    times(1)

            );

            final List<ResponseCreatedOrderDTO> expectedResponse = Collections.singletonList(
                    orderDTOMock.getResponseCreatedOrder()
            );

            assertEquals(expectedResponse, result);
        }

        Assertions.assertNotEquals(UuidHelper.getByLong(posId), orderEntities.get(0).getUid());

    }

    @Test
    void test_CreateOrder_When_Save_With_Discount_Product() throws CannotDivideByZeroException {

        final List<OrderEntity> orderEntities = orderEntityMock.getDataDefaultListTwo();
        orderEntities.get(0).setDeliveryTypeId(3L);

        final Long posId = orderEntities.get(0).getPosId();
        final String expectedUid = orderEntities.get(0).getUid();

        Assertions.assertNotEquals(UuidHelper.getByLong(posId), orderEntities.get(0).getUid());

        try (MockedStatic<UuidHelper> mockUuidHelper = Mockito.mockStatic(UuidHelper.class)) {

            mockUuidHelper.when(() -> UuidHelper.getByLong(posId)).thenReturn(expectedUid);

            Mockito.when(mockOrdersRepository.saveAll(any())).thenReturn(orderEntities);
            Mockito.when(mockGetStatusByIdUseCase.invoke(any())).thenReturn(null);

            Mockito.when(mockOrdersRepository.findAllById(any()))
                    .thenReturn(orderEntities);

            lenient().when(createOrderFinalProductUseCase.invoke(
                    orderDTOMock.getFinalProductDTOList(),
                    1L,
                    1L,
                    1L,
                    true
            )).thenReturn(CompletableFuture.completedFuture(true));

            final List<ResponseCreatedOrderDTO> result = createOrderUseCase
                    .invoke(
                            inputOrderTransactionValidatedDTO,
                            orderDTOMock.getData_with_Product_Discount(),
                            1L, 10000.0
                    );

            mockUuidHelper.verify(
                    () -> UuidHelper.getByLong(posId),
                    times(1)

            );

            final List<ResponseCreatedOrderDTO> expectedResponse = Collections.singletonList(
                    orderDTOMock.getResponseCreatedOrder()
            );

            assertEquals(expectedResponse, result);
        }

        Assertions.assertNotEquals(UuidHelper.getByLong(posId), orderEntities.get(0).getUid());

    }

}
