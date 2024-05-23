package com.robinfood.app.usecases.getordertotaldailysalesbyparams;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.GetOrderTotalDailySalesDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderPaymentEntity;
import com.robinfood.core.entities.OriginEntity;
import com.robinfood.core.entities.PaymentMethodEntity;
import com.robinfood.repository.orderpayment.IOrderPaymentRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.origin.IOriginRepository;
import com.robinfood.repository.paymentmethod.IPaymentMethodRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetOrdersTotalDailySalesByParamsUseCaseTest {

    @Mock
    private IOrdersRepository mockOrdersRepository;

    @Mock
    private IPaymentMethodRepository mockPaymentMethodRepository;

    @Mock
    private IOrderPaymentRepository mockOrderPaymentRepository;

    @Mock
    private IOriginRepository mockOriginRepository;

    @InjectMocks
    private GetOrdersTotalDailySalesByParamsUseCase mockUseCase;

    private final List<PaymentMethodEntity> paymentMethodList = new ArrayList<>(Arrays.asList(
        new PaymentMethodEntity(2L,
            1,
            1,
            1,
            "pos_debit_card",
            "",
            "",
            LocalDateTime.now(),
            LocalDateTime.now()),
        new PaymentMethodEntity(15L,
            1,
            1,
            1,
            "Rappi",
            "",
            "",
            LocalDateTime.now(),
            LocalDateTime.now()),
        new PaymentMethodEntity(4L,
            1,
            1,
            1,
            "self_debit_card",
            "",
            "",
            LocalDateTime.now(),
            LocalDateTime.now()),
        new PaymentMethodEntity(27L,
            6,
            0,
            1,
            "pos_nequi",
            "",
            "",
            LocalDateTime.now(),
            LocalDateTime.now()),
        new PaymentMethodEntity(28L,
            6,
            0,
            1,
            "pos_daviplata",
            "",
            "",
            LocalDateTime.now(),
            LocalDateTime.now())
    ));

    private final List<PaymentMethodEntity> paymentMethodDifList = new ArrayList<>(Arrays.asList(
        new PaymentMethodEntity(2L,
            1,
            1,
            1,
            "Didi",
            "",
            "",
            LocalDateTime.now(),
            LocalDateTime.now())
    ));

    private final List<OrderPaymentEntity> orderPaymentEntityList = new ArrayList<>(Arrays.asList(
        new OrderPaymentEntity(LocalDateTime.now(),
            2.0,
            1L,
            1L,
            1L,
            15L,
            2.0,
            2.0,
            LocalDateTime.now(),
            2.0)
    ));

    private final List<OrderEntity> orderEntities = new ArrayList<>(Arrays.asList(
        new OrderEntity(
            1L,
            1L,
            "Brand Name",
            1L,
            LocalDateTime.of(2021, 10, 11, 10, 10, 10),
            "CO",
            1L,
            0.0,
            1,
            1L,
            LocalDate.of(2021, 10, 11),
            LocalTime.of(10, 10, 10),
            1,
            LocalDate.of(2021, 10, 11),
            "476383",
            "234234",
            15L,
            "Origin Name",
            Boolean.TRUE,
            15L,
            "Pickup Time",
            1L,
            Boolean.TRUE,
            1L,
            1L,
            "Store Name",
            0.0,
            0.0,
            1L,
            BigDecimal.valueOf(0.0),
            0.0,
            "uid",
            UUID.randomUUID().toString(),
            LocalDateTime.now(),
            1L,
            1L
        )
    ));

    private final List<OrderPaymentEntity> orderPaymentEntityIntegrationsList = new ArrayList<>(Arrays.asList(
        new OrderPaymentEntity(LocalDateTime.now(),
            2.0,
            1L,
            1L,
            1L,
            8L,
            2.0,
            2.0,
            LocalDateTime.now(),
            2.0),
        new OrderPaymentEntity(LocalDateTime.now(),
            2.0,
            2L,
            1L,
            1L,
            2L,
            2.0,
            2.0,
            LocalDateTime.now(),
            2.0),
        new OrderPaymentEntity(LocalDateTime.now(),
            2.0,
            3L,
            1L,
            1L,
            3L,
            2.0,
            2.0,
            LocalDateTime.now(),
            2.0),
        new OrderPaymentEntity(LocalDateTime.now(),
            2.0,
            4L,
            1L,
            1L,
            4L,
            2.0,
            2.0,
            LocalDateTime.now(),
            2.0),
        new OrderPaymentEntity(LocalDateTime.now(),
            2.0,
            5L,
            1L,
            1L,
            5L,
            2.0,
            2.0,
            LocalDateTime.now(),
            2.0),
        new OrderPaymentEntity(LocalDateTime.now(),
            2.0,
            1L,
            1L,
            1L,
            27L,
            2.0,
            2.0,
            LocalDateTime.now(),
            2.0),
        new OrderPaymentEntity(LocalDateTime.now(),
            2.0,
            1L,
            1L,
            1L,
            28L,
            2.0,
            2.0,
            LocalDateTime.now(),
            2.0),
        new OrderPaymentEntity(LocalDateTime.now(),
            2.0,
            1L,
            1L,
            1L,
            10L,
            2.0,
            2.0,
            LocalDateTime.now(),
            2.0),
        new OrderPaymentEntity(LocalDateTime.now(),
            2.0,
            1L,
            1L,
            1L,
            11L,
            2.0,
            2.0,
            LocalDateTime.now(),
            2.0)
    ));

    private final List<OrderEntity> orderEntitiesIntegrations = new ArrayList<>(Arrays.asList(
        new OrderEntity(
            1L,
            1L,
            "Brand Name",
            1L,
            LocalDateTime.of(2021, 10, 11, 10, 10, 10),
            "CO",
            1L,
            0.0,
            1,
            1L,
            LocalDate.of(2021, 10, 11),
            LocalTime.of(10, 10, 10),
            1,
            LocalDate.of(2021, 10, 11),
            "476383",
            "234234",
            1L,
            "Origin Name",
            Boolean.TRUE,
            8L,
            "Pickup Time",
            1L,
            Boolean.TRUE,
            1L,
            1L,
            "Store Name",
            0.0,
            0.0,
            1L,
            BigDecimal.valueOf(0.0),
            0.0,
            "uid",
            "50eaf34f-7252-46ef-9a69-2225b06e14be",
            LocalDateTime.now(),
            1L,
            1L
        ),
        new OrderEntity(
            1L,
            1L,
            "Brand Name",
            1L,
            LocalDateTime.of(2021, 10, 11, 10, 10, 10),
            "CO",
            1L,
            0.0,
            1,
            2L,
            LocalDate.of(2021, 10, 11),
            LocalTime.of(10, 10, 10),
            1,
            LocalDate.of(2021, 10, 11),
            "476383",
            "234234",
            2L,
            "Origin Name",
            Boolean.TRUE,
            2L,
            "Pickup Time",
            1L,
            Boolean.TRUE,
            1L,
            1L,
            "Store Name",
            0.0,
            0.0,
            1L,
            BigDecimal.valueOf(0.0),
            0.0,
            "uid",
            "50eaf34f-7252-46ef-9a69-2225b06e14be",
            LocalDateTime.now(),
            1L,
            1L
        ),
        new OrderEntity(
            1L,
            1L,
            "Brand Name",
            1L,
            LocalDateTime.of(2021, 10, 11, 10, 10, 10),
            "CO",
            1L,
            0.0,
            1,
            3L,
            LocalDate.of(2021, 10, 11),
            LocalTime.of(10, 10, 10),
            1,
            LocalDate.of(2021, 10, 11),
            "476383",
            "234234",
            2L,
            "Origin Name",
            Boolean.TRUE,
            3L,
            "Pickup Time",
            1L,
            Boolean.TRUE,
            1L,
            1L,
            "Store Name",
            0.0,
            0.0,
            1L,
            BigDecimal.valueOf(0.0),
            0.0,
            "uid",
            "50eaf34f-7252-46ef-9a69-2225b06e14be",
            LocalDateTime.now(),
            1L,
            1L
        ),
        new OrderEntity(
            1L,
            1L,
            "Brand Name",
            1L,
            LocalDateTime.of(2021, 10, 11, 10, 10, 10),
            "CO",
            1L,
            0.0,
            1,
            4L,
            LocalDate.of(2021, 10, 11),
            LocalTime.of(10, 10, 10),
            1,
            LocalDate.of(2021, 10, 11),
            "476383",
            "234234",
            2L,
            "Origin Name",
            Boolean.TRUE,
            4L,
            "Pickup Time",
            1L,
            Boolean.TRUE,
            1L,
            1L,
            "Store Name",
            0.0,
            0.0,
            1L,
            BigDecimal.valueOf(0.0),
            0.0,
            "uid",
            "50eaf34f-7252-46ef-9a69-2225b06e14be",
            LocalDateTime.now(),
            1L,
            1L
        ),
        new OrderEntity(
            1L,
            1L,
            "Brand Name",
            1L,
            LocalDateTime.of(2021, 10, 11, 10, 10, 10),
            "CO",
            1L,
            0.0,
            1,
            5L,
            LocalDate.of(2021, 10, 11),
            LocalTime.of(10, 10, 10),
            1,
            LocalDate.of(2021, 10, 11),
            "476383",
            "234234",
            2L,
            "Origin Name",
            Boolean.TRUE,
            5L,
            "Pickup Time",
            1L,
            Boolean.TRUE,
            1L,
            1L,
            "Store Name",
            0.0,
            0.0,
            1L,
            BigDecimal.valueOf(0.0),
            0.0,
            "uid",
            "50eaf34f-7252-46ef-9a69-2225b06e14be",
            LocalDateTime.now(),
            1L,
            1L
        ),
        new OrderEntity(
            1L,
            1L,
            "Brand Name",
            1L,
            LocalDateTime.of(2021, 10, 11, 10, 10, 10),
            "CO",
            1L,
            0.0,
            1,
            6L,
            LocalDate.of(2021, 10, 11),
            LocalTime.of(10, 10, 10),
            1,
            LocalDate.of(2021, 10, 11),
            "476383",
            "234234",
            10L,
            "Origin Name",
            Boolean.TRUE,
            5L,
            "Pickup Time",
            1L,
            Boolean.TRUE,
            1L,
            1L,
            "Store Name",
            0.0,
            0.0,
            1L,
            BigDecimal.valueOf(0.0),
            0.0,
            "uid",
            "50eaf34f-7252-46ef-9a69-2225b06e14be",
            LocalDateTime.now(),
            1L,
            1L
        ),
        new OrderEntity(
            1L,
            1L,
            "Brand Name",
            1L,
            LocalDateTime.of(2021, 10, 11, 10, 10, 10),
            "CO",
            1L,
            0.0,
            1,
            7L,
            LocalDate.of(2021, 10, 11),
            LocalTime.of(10, 10, 10),
            1,
            LocalDate.of(2021, 10, 11),
            "476383",
            "234234",
            10L,
            "Origin Name",
            Boolean.TRUE,
            5L,
            "Pickup Time",
            1L,
            Boolean.TRUE,
            1L,
            1L,
            "Store Name",
            0.0,
            0.0,
            1L,
            BigDecimal.valueOf(0.0),
            0.0,
            "uid",
            "50eaf34f-7252-46ef-9a69-2225b06e14be",
            LocalDateTime.now(),
            1L,
            1L
        ),
        new OrderEntity(
            1L,
            1L,
            "Brand Name",
            1L,
            LocalDateTime.of(2021, 10, 11, 10, 10, 10),
            "CO",
            1L,
            0.0,
            1,
            6L,
            LocalDate.of(2021, 10, 11),
            LocalTime.of(10, 10, 10),
            1,
            LocalDate.of(2021, 10, 11),
            "476383",
            "234234",
            10L,
            "Origin Name",
            Boolean.TRUE,
            5L,
            "Pickup Time",
            1L,
            Boolean.TRUE,
            1L,
            1L,
            "Store Name",
            0.0,
            0.0,
            1L,
            BigDecimal.valueOf(0.0),
            0.0,
            "uid",
            "50eaf34f-7252-46ef-9a69-2225b06e14be",
            LocalDateTime.now(),
            1L,
            1L
        ),
        new OrderEntity(
            1L,
            1L,
            "Brand Name",
            1L,
            LocalDateTime.of(2021, 10, 11, 10, 10, 10),
            "CO",
            1L,
            0.0,
            1,
            6L,
            LocalDate.of(2021, 10, 11),
            LocalTime.of(10, 10, 10),
            1,
            LocalDate.of(2021, 10, 11),
            "476383",
            "234234",
            10L,
            "Origin Name",
            Boolean.TRUE,
            5L,
            "Pickup Time",
            1L,
            Boolean.TRUE,
            1L,
            1L,
            "Store Name",
            0.0,
            0.0,
            1L,
            BigDecimal.valueOf(0.0),
            0.0,
            "uid",
            "50eaf34f-7252-46ef-9a69-2225b06e14be",
            LocalDateTime.now(),
            1L,
            1L
        )
    ));

    private final List<OriginEntity> originsList = new ArrayList<>(Arrays.asList(
        new OriginEntity("Rappi",
            1L,
            "Rappi",
            LocalDateTime.now(),
            LocalDateTime.now()),
        new OriginEntity("DiDi",
            2L,
            "DiDi",
            LocalDateTime.now(),
            LocalDateTime.now()),
        new OriginEntity("iFood",
            3L,
            "iFood",
            LocalDateTime.now(),
            LocalDateTime.now()),
        new OriginEntity("Autogestion",
            4L,
            "Autogestion",
            LocalDateTime.now(),
            LocalDateTime.now()),
        new OriginEntity("Autogestion 2",
            5L,
            "Autogestion 2",
            LocalDateTime.now(),
            LocalDateTime.now()),
        new OriginEntity("Uber",
            6L,
            "Uber",
            LocalDateTime.now(),
            LocalDateTime.now())
    ));

    private final List<GetOrderTotalDailySalesDTO> totalDailySalesIntegrationsDTO = new ArrayList<>(Arrays.asList(
        new GetOrderTotalDailySalesDTO(2.0,8L,"Rappi",1),
        new GetOrderTotalDailySalesDTO(8.0,2L,"pos_debit_card",4),
        new GetOrderTotalDailySalesDTO(8.0,4L,"self_debit_card",4)
    ));

    private final List<GetOrderTotalDailySalesDTO> totalDailySalesDTO = new ArrayList<>(Arrays.asList(
        new GetOrderTotalDailySalesDTO(2.0,15l,"Rappi",1)
    ));

    private final List<GetOrderTotalDailySalesDTO> totalDailySalesNotOriginNameDTO = new ArrayList<>(Arrays.asList(
        new GetOrderTotalDailySalesDTO(2.0,15l,"",1)
    ));

    @Test
    void test_When_Get_Orders_Total_Daily_sales_integration_OK() {

        when(mockOriginRepository.findAllById(anyList())).thenReturn(originsList);

        when(mockPaymentMethodRepository.findAll()).thenReturn(paymentMethodList);

        when(mockOrdersRepository
            .findAllByStoreIdAndOperationDateAndPaidAndStatusIdNotIn(anyLong(), any(), any(), anyList()))
            .thenReturn(orderEntitiesIntegrations);

        when(mockOrderPaymentRepository.findOrderPaymentEntitiesByOrderIdIn(anyList()))
            .thenReturn(orderPaymentEntityIntegrationsList);

        List<GetOrderTotalDailySalesDTO> result = mockUseCase.invoke(27L, LocalDate.now());

        Assertions.assertEquals(totalDailySalesIntegrationsDTO, result);
    }

    @Test
    void test_When_Get_Orders_Total_Daily_sales_OK() {

        when(mockPaymentMethodRepository.findAll()).thenReturn(paymentMethodList);

        when(mockOrdersRepository
            .findAllByStoreIdAndOperationDateAndPaidAndStatusIdNotIn(anyLong(), any(), any(), anyList()))
            .thenReturn(orderEntities);

        when(mockOrderPaymentRepository.findOrderPaymentEntitiesByOrderIdIn(anyList()))
            .thenReturn(orderPaymentEntityList);

        List<GetOrderTotalDailySalesDTO> result = mockUseCase.invoke(27L, LocalDate.now());

        Assertions.assertEquals(totalDailySalesDTO, result);
    }

    @Test
    void test_When_Get_Orders_Total_Daily_sales_Not_Origin() {

        when(mockOriginRepository.findAllById(anyList())).thenReturn(originsList);
        when(mockPaymentMethodRepository.findAll()).thenReturn(paymentMethodDifList);

        when(mockOrdersRepository
            .findAllByStoreIdAndOperationDateAndPaidAndStatusIdNotIn(anyLong(), any(), any(), anyList()))
            .thenReturn(orderEntities);

        when(mockOrderPaymentRepository.findOrderPaymentEntitiesByOrderIdIn(anyList()))
            .thenReturn(orderPaymentEntityList);

        List<GetOrderTotalDailySalesDTO> result = mockUseCase.invoke(27L, LocalDate.now());

        Assertions.assertEquals(totalDailySalesNotOriginNameDTO, result);
    }

    @Test
    void test_When_Get_Orders_Total_Daily_sales_Not_OrderPayment() {

        when(mockOrdersRepository
            .findAllByStoreIdAndOperationDateAndPaidAndStatusIdNotIn(anyLong(), any(), any(), anyList()))
            .thenReturn(new ArrayList<>());

        when(mockOrderPaymentRepository.findOrderPaymentEntitiesByOrderIdIn(anyList()))
            .thenReturn(new ArrayList<>());

        List<GetOrderTotalDailySalesDTO> result = mockUseCase.invoke(27L, LocalDate.now());

        Assertions.assertEquals(new ArrayList<>(), result);
    }
}
