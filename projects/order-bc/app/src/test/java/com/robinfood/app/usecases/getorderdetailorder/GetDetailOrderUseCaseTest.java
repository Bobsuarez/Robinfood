package com.robinfood.app.usecases.getorderdetailorder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.app.datamocks.dto.core.GetDataElectronicBillingDTOMock;
import com.robinfood.app.datamocks.dto.core.UserDataDTOMock;
import com.robinfood.app.datamocks.dto.input.OrderFiscalIdentifierDTODataMock;
import com.robinfood.app.datamocks.dto.core.GetOrderThirdPartyDTOMock;
import com.robinfood.app.usecases.getorderdetailbyids.IGetOrderDetailByIdsUseCase;
import com.robinfood.app.usecases.getorderdetaildiscountbyorderids.IGroupOrderDetailDiscountByOrderIdsUseCase;
import com.robinfood.app.usecases.getorderdetailfinalproduct.IGroupOrderDetailProductsUseCase;
import com.robinfood.app.usecases.getorderdetailpaymentmethodbyorderids.IGroupOrderDetailPaymentMethodsByOrderIds;
import com.robinfood.app.usecases.getorderdieductionbyfinalproductids.IGetOrderDeductionsByFinalProductIdsUseCase;
import com.robinfood.app.usecases.getorderdiscountbyfinalproductids.IGetOrderDiscountByFinalProductIdsUseCase;
import com.robinfood.app.usecases.getorderdiscountbyorderids.IGetOrderDiscountByOrderIdsUseCase;
import com.robinfood.app.usecases.getorderfiscalidentifierbytransactionidusecase.IGetOrderFiscalIdentifierByTransactionIdUseCase;
import com.robinfood.app.usecases.getorderfoodcoins.IGetOrderFoodCoinsUseCase;
import com.robinfood.app.usecases.getorderintegration.IGetOrderIntegrationUseCase;
import com.robinfood.app.usecases.getorderisintegration.IGetOrderIsIntegrationUseCase;
import com.robinfood.app.usecases.getorderpaymentbyorderids.IGetOrderPaymentByOrderIdsUseCase;
import com.robinfood.app.usecases.getorderproducttaxesbyfinalproductids.IGetOrderProductTaxesByFinalProductIdsUseCase;
import com.robinfood.app.usecases.getstatusbylistid.GetStatusByListIdUseCase;
import com.robinfood.app.usecases.gettransactioninfo.IGetTransactionInfoUseCase;
import com.robinfood.app.usecases.getuserdatabyorderids.IGetUserDataByOrderIdsUseCase;
import com.robinfood.core.dtos.CouponsDTO;
import com.robinfood.core.dtos.DataElectronicBillingDTO;
import com.robinfood.core.dtos.ElectronicBillDTO;
import com.robinfood.core.dtos.GetOrderDetailDTO;
import com.robinfood.core.dtos.GetOrderDetailDiscountDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductDTO;
import com.robinfood.core.dtos.GetOrderDetailPaymentMethodDTO;
import com.robinfood.core.dtos.OrderDeductionFinalProductDTO;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.core.dtos.OrderIntegrationDTO;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.OrderProductTaxDTO;
import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.dtos.OrderThirdPartyDTO;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.entities.OrderCouponEntity;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderFinalProductEntity;
import com.robinfood.core.entities.TransactionFlowEntity;
import com.robinfood.core.entities.OrderThirdPartyEntity;
import com.robinfood.repository.ordercoupons.IOrderCouponRepository;
import com.robinfood.repository.orderdeduction.IOrderDeductionRepository;
import com.robinfood.repository.orderelectronicbillings.IOrderElectronicBillingsRepository;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.transaction.ITransactionCRUDRepository;
import com.robinfood.repository.transactionflow.ITransactionFlowRepository;
import com.robinfood.repository.orderthirdparties.IOrderThirdPartiesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.robinfood.core.constants.GlobalConstants.BASIC_DATA_OBJECT_IN_JSON_PAYLOAD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetDetailOrderUseCaseTest {

    @Mock
    private IGroupOrderDetailProductsUseCase getOrderFinalProductUseCase;

    @Mock
    private IGetOrderPaymentByOrderIdsUseCase getOrderPaymentByOrderIdsUseCase;

    @Mock
    private IGroupOrderDetailPaymentMethodsByOrderIds getDetailOrderPaymentMethodsByOrderIds;

    @Mock
    private IGetUserDataByOrderIdsUseCase getUserDataByOrderIdsUseCase;

    @Mock
    private ITransactionCRUDRepository transactionCRUDRepository;

    @Mock
    private IGetOrderDetailByIdsUseCase getOrderDetailByIdsUseCase;

    @Mock
    private IGetOrderDiscountByOrderIdsUseCase getOrderDiscountByOrderIdsUseCase;

    @Mock
    private IGroupOrderDetailDiscountByOrderIdsUseCase getOrderDetailDiscountByOrderIdsUseCase;

    @Mock
    private IGetOrderDiscountByFinalProductIdsUseCase getOrderDiscountByFinalProductIdsUseCase;

    @Mock
    private IGetOrderProductTaxesByFinalProductIdsUseCase getOrderProductTaxesByFinalProductIdsUseCase;

    @Mock
    private IGetOrderIntegrationUseCase getOrderIntegrationUseCase;

    @Mock
    private IGetOrderIsIntegrationUseCase getOrderIsIntegrationUseCase;

    @Mock
    private IGetOrderFiscalIdentifierByTransactionIdUseCase getOrderFiscalIdentifierByTransactionIdUseCase;

    @Mock
    private IOrderFinalProductRepository orderFinalProductRepository;

    @Mock
    private GetStatusByListIdUseCase getStatusByListIdUseCase;

    @Mock
    private IOrdersRepository ordersRepository;

    @Mock
    private IOrderCouponRepository orderCouponRepository;

    @Mock
    private ITransactionFlowRepository transactionFlowRepository;

    @Mock
    private IGetOrderFoodCoinsUseCase orderFoodCoinsUseCase;

    @Mock
    private IGetOrderDeductionsByFinalProductIdsUseCase getOrderDeductionsByFinalProductIdsUseCase;

    @Mock
    private IOrderDeductionRepository orderDeductionRepository;

    @Mock
    private IGetTransactionInfoUseCase getTransactionInfoUseCase;

    @Mock
    private IOrderThirdPartiesRepository orderThirdPartiesRepository;

    @Mock
    private IOrderElectronicBillingsRepository orderElectronicBillingsRepository;

    @InjectMocks
    private GetOrderDetailOrderUseCase getOrderDetailOrderUseCase;

    private final List<Long> listStatus = Collections.singletonList(1L);

    private final OrderStatusDTO orderStatusDTO = new OrderStatusDTO(
            1L,
            "Pedido"
    );

    private final OrderStatusDTO orderStatusDTO2 = new OrderStatusDTO(
            9L,
            "Pedido"
    );

    private final List<Long> orderIds = new ArrayList<>(Collections.singletonList(1L));

    private final List<Long> orderFinalProductIds = new ArrayList<>(Collections.singletonList(1L));

    private final List<OrderDiscountDTO> orderDiscountDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDiscountDTO(
                            1L,
                            0.0,
                            1L,
                            1L,
                            1L,
                            1L
                    )
            )
    );


    private final List<OrderProductTaxDTO> orderProductTaxDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderProductTaxDTO(
                            1L,
                            1L,
                            1L,
                            1L,
                            1L,
                            1L,
                            1L,
                            0.0,
                            1L,
                            "IMPOCONSUMO",
                            0.0
                    )
            )
    );

    private final List<OrderDeductionFinalProductDTO> orderDeductionFinalProductDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDeductionFinalProductDTO(
                            1L,
                            1L,
                            1L,
                            BigDecimal.ZERO
                    )
            )
    );

    private final Map<Long, List<GetOrderDetailFinalProductDTO>> getOrderDetailFinalProductDTOSMap = new HashMap<>();

    private final OrderThirdPartyDTO orderThirdPartyDTO = GetOrderThirdPartyDTOMock.getDataDefault();

    private final DataElectronicBillingDTO dataElectronicBillingDTO = GetDataElectronicBillingDTOMock.getDataDefault();

    private final ElectronicBillDTO electronicBillDTO = ElectronicBillDTO.builder()
            .orderElectronicBilling(dataElectronicBillingDTO)
            .orderThirdParty(orderThirdPartyDTO).build();

    private final List<GetOrderDetailFinalProductDTO> getOrderDetailFinalProductDTOS = List.of(
                    GetOrderDetailFinalProductDTO.builder()
                            .articleId(1L)
                            .articleTypeId(1L)
                            .basePrice(new BigDecimal("8900.0"))
                            .brandId(1L)
                            .brandName("muy")
                            .categoryId(1L)
                            .categoryName("Category")
                            .id(1L)
                            .image("image.png")
                            .brandMenuId(1L)
                            .name("paisa")
                            .quantity(1)
                            .unitPrice(new BigDecimal("8900.0"))
                            .sizeId(1L)
                            .sizeName("muy")
                            .sku("sku")
                            .discount(BigDecimal.ZERO)
                            .deduction(BigDecimal.valueOf(8900.0))
                            .orderId(1L)
                            .co2Total(BigDecimal.ZERO)
                            .total(0.0)
                            .build()
            );

    private final List<OrderPaymentDTO> orderPaymentDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderPaymentDTO(
                            null,
                            0.0,
                            1L,
                            1L,
                            1L,
                            1L,
                            8900.0,
                            0.0,
                            8900.0
                    )
            )
    );

    private final Map<Long, List<GetOrderDetailPaymentMethodDTO>> getOrderDetailPaymentMethodDTOMap = new HashMap<>();

    private final List<GetOrderDetailPaymentMethodDTO> getOrderDetailPaymentMethodDTOS = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailPaymentMethodDTO(
                            0.0,
                            1L,
                            8900.0,
                            1L,
                            1L,
                            0.0,
                            8900.0
                    )
            )
    );

    private final UserDataDTO userDataDTO = UserDataDTOMock.getDataDefault();

    private final List<OrderDiscountDTO> orderDiscountDTOSByOrderIds = new ArrayList<>(
            Collections.singletonList(
                    new OrderDiscountDTO(
                            1L,
                            0.0,
                            1L,
                            1L,
                            1L,
                            null
                    )
            ));

    private final List<OrderDiscountDTO> orderDiscountDTOSByProductIds = new ArrayList<>(
            Collections.singletonList(
                    new OrderDiscountDTO(
                            1L,
                            0.0,
                            1L,
                            1L,
                            1L,
                            1L
                    )
            ));

    private final List<GetOrderDetailDiscountDTO> getOrderDetailDiscountDTOSByOrderId = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailDiscountDTO(
                            1L,
                            1L,
                            null,
                            1L,
                            5000.0
                    )
            ));

    private final Map<Long, List<GetOrderDetailDiscountDTO>> getDetailOrderDiscountByOrderIdsDTOSMap = new HashMap<>() {{
        put(1L, getOrderDetailDiscountDTOSByOrderId);
    }};

    private final List<OrderFinalProductEntity> orderFinalProductEntities = new ArrayList<>(
            Collections.singletonList(
                    new OrderFinalProductEntity(
                            1L,
                            1L,
                            1L,
                            1L,
                            "muy",
                            new BigDecimal("8900.0"),
                            1L,
                            8900.0,
                            1L,
                            "sugerido",
                            1L,
                            "paisa",
                            "image.png",
                            1L,
                            1L,
                            0.0,
                            1,
                            1L,
                            "muy",
                            0.0,
                            8900.0,
                            0.0
                    )
            )
    );

    private final Iterable<OrderEntity> orderEntities = new ArrayList<>(
            Collections.singletonList(
                    new OrderEntity(
                            1L,
                            1L,
                            "muy",
                            1L,
                            LocalDateTime.now(),
                            "cop",
                            1L,
                            0.0,
                            1,
                            1L,
                            null,
                            null,
                            1,
                            LocalDate.parse("2022-06-15"),
                            "",
                            "2005",
                            1L,
                            "pos",
                            true,
                            1L,
                            "00:00:00",
                            1L,
                            false,
                            1L,
                            1L,
                            "muy 79",
                            8900.0,
                            0.0,
                            1L,
                            BigDecimal.ZERO,
                            8900.0,
                            "3483eghf",
                            UUID.randomUUID().toString(),
                            LocalDateTime.now(),
                            1L,
                            1L
                    )
            )
    );

    private final List<OrderEntity> orderEntitiesList = new ArrayList<>(
            Collections.singletonList(
                    new OrderEntity(
                            1L,
                            1L,
                            "muy",
                            1L,
                            LocalDateTime.now(),
                            "cop",
                            1L,
                            0.0,
                            1,
                            1L,
                            null,
                            null,
                            1,
                            LocalDate.parse("2022-06-15"),
                            "",
                            "2005",
                            1L,
                            "pos",
                            true,
                            1L,
                            "00:00:00",
                            1L,
                            false,
                            1L,
                            1L,
                            "muy 79",
                            8900.0,
                            0.0,
                            1L,
                            BigDecimal.ZERO,
                            8900.0,
                            "3483eghf",
                            "bda0d95a-0c5f-4fc8-8913-1d395107e418",
                            LocalDateTime.now(),
                            1L,
                            1L
                    )
            )
    );

    private final List<CouponsDTO> coupons = new ArrayList<>(Arrays.asList(CouponsDTO.builder()
            .code("TESTCOUPON")
            .value(BigDecimal.valueOf(2500))
            .build()));

    private final List<GetOrderDetailDTO> getOrderDetailDTOS = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailDTO(
                            1L,
                            "muy",
                            1L,
                            BigDecimal.ZERO,
                            coupons,
                            "cop",
                            0.0,
                            getOrderDetailDiscountDTOSByOrderId,
                            BigDecimal.ZERO,
                            1L,
                            electronicBillDTO,
                            1L,
                            null,
                            1L,
                            "",
                            "Notes",
                            LocalDate.parse("2022-06-15"),
                            1L,
                            "pos",
                            "2005",
                            "bda0d95a-0c5f-4fc8-8913-1d395107e418",
                            "3483eghf",
                            "test 1",
                            true,
                            "0000",
                            new OrderFiscalIdentifierDTODataMock().getDataDefault(),
                            getOrderDetailPaymentMethodDTOS,
                            1L,
                            false,
                            getOrderDetailFinalProductDTOS,
                            1L,
                            "Pedido",
                            1L,
                            "muy 79",
                            8900.0,
                            0.0,
                            8900.0,
                            1L,
                            null,
                            "3483eghf",
                            userDataDTO
                    )
            )
    );

    private final List<GetOrderDetailDTO> getOrderDetailDTOSFalse = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailDTO(
                            1L,
                            "muy",
                            1L,
                            BigDecimal.ZERO,
                            coupons,
                            "cop",
                            0.0,
                            getOrderDetailDiscountDTOSByOrderId,
                            BigDecimal.ZERO,
                            1L,
                            electronicBillDTO,
                            0L,
                            null,
                            1L,
                            "",
                            "Notes",
                            LocalDate.parse("2022-06-15"),
                            1L,
                            "pos",
                            "2005",
                            "bda0d95a-0c5f-4fc8-8913-1d395107e418",
                            null,
                            null,
                            false,
                            null,
                            new OrderFiscalIdentifierDTODataMock().getDataDefault(),
                            getOrderDetailPaymentMethodDTOS,
                            1L,
                            false,
                            getOrderDetailFinalProductDTOS,
                            1L,
                            "Pedido",
                            1L,
                            "muy 79",
                            8900.0,
                            0.0,
                            8900.0,
                            1L,
                            null,
                            "3483eghf",
                            userDataDTO
                    )
            )
    );

    private final OrderIntegrationDTO orderIntegrationDTO = new OrderIntegrationDTO(
            "carrera",
            "edificio",
            Date.valueOf("2020-10-23"),
            Time.valueOf("11:10:00"),
            1L,
            "MUY",
            "0000",
            "3483eghf",
            "Test",
            102L,
            1L,
            "Caja",
            1,
            "Efectivo",
            1L,
            "MUY 79",
            8900.0,
            100.0,
            0.0,
            1.0,
            "test 1",
            "300201"
    );

    private final List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>(Arrays.asList(
            new OrderDetailDTO(
                    0.0,
                    true,
                    1L,
                    "34343434",
                    "Notes"
            )
    ));

    private final List<OrderCouponEntity> couponEntity = new ArrayList<>(Arrays.asList(
            new OrderCouponEntity(
                    "TESTCOUPON",
                    1L,
                    null,
                    1,
                    "1234",
                    1L,
                    null,
                    BigDecimal.valueOf(2500)
            )
    ));

    private final TransactionFlowEntity transactionFlowEntity = new TransactionFlowEntity(
            null,
            1L,
            1L,
            1L,
            null
    );

    private final OrderThirdPartyEntity orderThirdPartyEntity = OrderThirdPartyEntity.builder()
            .createdAt(null)
            .deletedAt(null)
            .documentNumber("22675323")
            .documentType(1L)
            .email("pedro@gmail.com")
            .fullName("Pedro Jose")
            .id(1L)
            .orderId(1L)
            .phone("3113673398")
            .updatedAt(null).build();


    private String getJsonResponsePayload(){
        DataElectronicBillingDTO dataElectronicBillingDTO = GetDataElectronicBillingDTOMock.getDataDefault();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(dataElectronicBillingDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Test
    void test_GetOrderDetailOrderUseCase_Returns_Correctly() {

        orderFinalProductEntities.get(0).setId(1L);

        getOrderDetailFinalProductDTOSMap.put(1L, getOrderDetailFinalProductDTOS);

        getOrderDetailPaymentMethodDTOMap.put(1L, getOrderDetailPaymentMethodDTOS);

        getDetailOrderDiscountByOrderIdsDTOSMap.put(1L, getOrderDetailDiscountDTOSByOrderId);

        when(orderFinalProductRepository.findAllByOrderIdIn(
                orderIds
        )).thenReturn(orderFinalProductEntities);

        when(getOrderDiscountByFinalProductIdsUseCase.invoke(
                orderFinalProductIds
        )).thenReturn(orderDiscountDTOSByProductIds);

        when(getOrderProductTaxesByFinalProductIdsUseCase.invoke(
                orderFinalProductIds
        )).thenReturn(orderProductTaxDTOS);

        when(getOrderIsIntegrationUseCase.invoke(1L))
                .thenReturn(true);

        when(getOrderIntegrationUseCase.invoke(1L))
                .thenReturn(orderIntegrationDTO);

        when(getOrderFinalProductUseCase.invoke(
                eq(orderIds),
                eq(orderFinalProductIds),
                eq(orderDiscountDTOS),
                eq(orderProductTaxDTOS),
                anyList()
        )).thenReturn(getOrderDetailFinalProductDTOSMap);

        when(ordersRepository.findAllById(
                orderIds
        )).thenReturn(orderEntitiesList);

        when(getUserDataByOrderIdsUseCase.invoke(
                orderIds
        )).thenReturn(Collections.singletonList(userDataDTO));

        when(getOrderPaymentByOrderIdsUseCase.invoke(
                orderIds
        )).thenReturn(orderPaymentDTOS);

        when(getDetailOrderPaymentMethodsByOrderIds.invoke(
                orderPaymentDTOS
        )).thenReturn(getOrderDetailPaymentMethodDTOMap);

        when(getOrderDiscountByOrderIdsUseCase.invoke(
                orderIds
        )).thenReturn(orderDiscountDTOSByOrderIds);

        when(getOrderDetailDiscountByOrderIdsUseCase.invoke(
                orderDiscountDTOSByOrderIds
        )).thenReturn(getDetailOrderDiscountByOrderIdsDTOSMap);

        when(getStatusByListIdUseCase.invoke(
                listStatus
        )).thenReturn(Collections.singletonList(orderStatusDTO));

        when(getOrderDetailByIdsUseCase.invoke(orderIds)).thenReturn(orderDetailDTOS);

        when(orderCouponRepository.findOrderCouponEntityByTransactionId(1L)).thenReturn(couponEntity);

        when(getOrderFiscalIdentifierByTransactionIdUseCase.invoke(anyLong())).thenReturn(
                new OrderFiscalIdentifierDTODataMock().getDataDefault()
        );

        when(orderThirdPartiesRepository.findByOrderId(1L)).thenReturn(
                Optional.ofNullable(orderThirdPartyEntity)
        );

        when(orderElectronicBillingsRepository.findByOrderIdAndStatusCodeAccepted(1L,
                BASIC_DATA_OBJECT_IN_JSON_PAYLOAD, 202)).thenReturn(
                Optional.ofNullable(getJsonResponsePayload())
        );

        when(getOrderDeductionsByFinalProductIdsUseCase.invoke(anyList())).thenReturn(
                List.of(
                        OrderDeductionFinalProductDTO.builder()
                                .id(1L)
                                .orderId(1l)
                                .productFinalId(1L)
                                .value(BigDecimal.TEN)
                                .build()
                )
        );
        Map<String,String> mapTransaction = new HashMap<>();
        mapTransaction.put("FLOW_ID", "1");

        when(getTransactionInfoUseCase.invoke(anyLong())).thenReturn(mapTransaction);

        final List<GetOrderDetailDTO> result = getOrderDetailOrderUseCase.invoke(
                orderIds
        );
        assertEquals(getOrderDetailDTOS, result);
    }

    @Test
    void test_GetOrderDetailOrderUseCase_With_Empty_Entities_Failure() {

        try {
            orderFinalProductEntities.get(0).setId(1L);

            getOrderDetailFinalProductDTOSMap.put(1L, getOrderDetailFinalProductDTOS);

            getOrderDetailPaymentMethodDTOMap.put(1L, getOrderDetailPaymentMethodDTOS);

            getDetailOrderDiscountByOrderIdsDTOSMap.put(1L, getOrderDetailDiscountDTOSByOrderId);

            when(ordersRepository.findAllById(
                    orderIds
            )).thenReturn(Collections.emptyList());

            lenient().when(getOrderDeductionsByFinalProductIdsUseCase.invoke(anyList())).thenReturn(
                    List.of(
                            OrderDeductionFinalProductDTO.builder()
                                    .id(1L)
                                    .orderId(1L)
                                    .productFinalId(1L)
                                    .value(BigDecimal.TEN)
                                    .build()
                    )
            );

            getOrderDetailOrderUseCase.invoke(
                    orderIds
            );

        } catch (ResponseStatusException exception) {
            assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        }
    }

    @Test
    void test_GetOrderDetailOrderUseCase_With_FlowId_IsNull() {

        orderFinalProductEntities.get(0).setId(1L);

        getOrderDetailFinalProductDTOSMap.put(1L, getOrderDetailFinalProductDTOS);

        getOrderDetailPaymentMethodDTOMap.put(1L, getOrderDetailPaymentMethodDTOS);

        getDetailOrderDiscountByOrderIdsDTOSMap.put(1L, getOrderDetailDiscountDTOSByOrderId);

        getOrderDetailDTOS.get(0).setFlowId(0L);

        when(orderFinalProductRepository.findAllByOrderIdIn(
                orderIds
        )).thenReturn(orderFinalProductEntities);

        when(getOrderDiscountByFinalProductIdsUseCase.invoke(
                orderFinalProductIds
        )).thenReturn(orderDiscountDTOSByProductIds);

        when(getOrderProductTaxesByFinalProductIdsUseCase.invoke(
                orderFinalProductIds
        )).thenReturn(orderProductTaxDTOS);

        when(getOrderIsIntegrationUseCase.invoke(1L))
                .thenReturn(true);

        when(getOrderIntegrationUseCase.invoke(1L))
                .thenReturn(orderIntegrationDTO);

        when(getOrderFinalProductUseCase.invoke(
                eq(orderIds),
                eq(orderFinalProductIds),
                eq(orderDiscountDTOS),
                eq(orderProductTaxDTOS),
                anyList()
        )).thenReturn(getOrderDetailFinalProductDTOSMap);

        when(ordersRepository.findAllById(
                orderIds
        )).thenReturn(orderEntitiesList);

        when(getUserDataByOrderIdsUseCase.invoke(
                orderIds
        )).thenReturn(Collections.singletonList(userDataDTO));

        when(getOrderPaymentByOrderIdsUseCase.invoke(
                orderIds
        )).thenReturn(orderPaymentDTOS);

        when(getDetailOrderPaymentMethodsByOrderIds.invoke(
                orderPaymentDTOS
        )).thenReturn(getOrderDetailPaymentMethodDTOMap);

        when(getOrderDiscountByOrderIdsUseCase.invoke(
                orderIds
        )).thenReturn(orderDiscountDTOSByOrderIds);

        when(getOrderDetailDiscountByOrderIdsUseCase.invoke(
                orderDiscountDTOSByOrderIds
        )).thenReturn(getDetailOrderDiscountByOrderIdsDTOSMap);

        when(getStatusByListIdUseCase.invoke(
                listStatus
        )).thenReturn(Collections.singletonList(orderStatusDTO));

        when(getOrderDetailByIdsUseCase.invoke(orderIds)).thenReturn(orderDetailDTOS);

        when(orderCouponRepository.findOrderCouponEntityByTransactionId(1L)).thenReturn(couponEntity);

        when(getOrderFiscalIdentifierByTransactionIdUseCase.invoke(anyLong())).thenReturn(
                new OrderFiscalIdentifierDTODataMock().getDataDefault()
        );

        when(orderThirdPartiesRepository.findByOrderId(1L)).thenReturn(
                Optional.ofNullable(orderThirdPartyEntity)
        );

        when(orderElectronicBillingsRepository.findByOrderIdAndStatusCodeAccepted(1L,
                BASIC_DATA_OBJECT_IN_JSON_PAYLOAD, 202)).thenReturn(
                Optional.ofNullable(getJsonResponsePayload())
        );


        when(getOrderDeductionsByFinalProductIdsUseCase.invoke(anyList())).thenReturn(
                List.of(
                        OrderDeductionFinalProductDTO.builder()
                                .id(1L)
                                .orderId(1l)
                                .productFinalId(1L)
                                .value(BigDecimal.TEN)
                                .build()
                )
        );

        Map<String,String> mapTransaction = new HashMap<>();
        mapTransaction.put("FLOW_ID", "0");

        when(getTransactionInfoUseCase.invoke(anyLong())).thenReturn(mapTransaction);

        final List<GetOrderDetailDTO> result = getOrderDetailOrderUseCase.invoke(
                orderIds
        );
        assertEquals(getOrderDetailDTOS, result);
    }
    @Test
    void test_GetOrderDetailOrderUseCase_With_isIntegrated_False_IsNull() {

        orderFinalProductEntities.get(0).setId(1L);

        getOrderDetailFinalProductDTOSMap.put(1L, getOrderDetailFinalProductDTOS);

        getOrderDetailPaymentMethodDTOMap.put(1L, getOrderDetailPaymentMethodDTOS);

        getDetailOrderDiscountByOrderIdsDTOSMap.put(1L, getOrderDetailDiscountDTOSByOrderId);

        getOrderDetailDTOS.get(0).setFlowId(0L);

        when(orderFinalProductRepository.findAllByOrderIdIn(
                orderIds
        )).thenReturn(orderFinalProductEntities);

        when(getOrderDiscountByFinalProductIdsUseCase.invoke(
                orderFinalProductIds
        )).thenReturn(orderDiscountDTOSByProductIds);

        when(getOrderProductTaxesByFinalProductIdsUseCase.invoke(
                orderFinalProductIds
        )).thenReturn(orderProductTaxDTOS);

        when(getOrderIsIntegrationUseCase.invoke(1L))
                .thenReturn(false);

        when(getOrderFinalProductUseCase.invoke(
                eq(orderIds),
                eq(orderFinalProductIds),
                eq(orderDiscountDTOS),
                eq(orderProductTaxDTOS),
                anyList()
        )).thenReturn(getOrderDetailFinalProductDTOSMap);

        when(ordersRepository.findAllById(
                orderIds
        )).thenReturn(orderEntitiesList);

        when(getUserDataByOrderIdsUseCase.invoke(
                orderIds
        )).thenReturn(Collections.singletonList(userDataDTO));

        when(getOrderPaymentByOrderIdsUseCase.invoke(
                orderIds
        )).thenReturn(orderPaymentDTOS);

        when(getDetailOrderPaymentMethodsByOrderIds.invoke(
                orderPaymentDTOS
        )).thenReturn(getOrderDetailPaymentMethodDTOMap);

        when(getOrderDiscountByOrderIdsUseCase.invoke(
                orderIds
        )).thenReturn(orderDiscountDTOSByOrderIds);

        when(getOrderDetailDiscountByOrderIdsUseCase.invoke(
                orderDiscountDTOSByOrderIds
        )).thenReturn(getDetailOrderDiscountByOrderIdsDTOSMap);

        when(getStatusByListIdUseCase.invoke(
                listStatus
        )).thenReturn(Collections.singletonList(orderStatusDTO));

        when(getOrderDetailByIdsUseCase.invoke(orderIds)).thenReturn(orderDetailDTOS);

        when(orderCouponRepository.findOrderCouponEntityByTransactionId(1L)).thenReturn(couponEntity);

        when(getOrderFiscalIdentifierByTransactionIdUseCase.invoke(anyLong())).thenReturn(
                new OrderFiscalIdentifierDTODataMock().getDataDefault()
        );

        when(getOrderDeductionsByFinalProductIdsUseCase.invoke(anyList())).thenReturn(
                List.of(
                        OrderDeductionFinalProductDTO.builder()
                                .id(1L)
                                .orderId(1l)
                                .productFinalId(1L)
                                .value(BigDecimal.TEN)
                                .build()
                )
        );

        when(orderThirdPartiesRepository.findByOrderId(1L)).thenReturn(
                Optional.ofNullable(orderThirdPartyEntity)
        );

        when(orderElectronicBillingsRepository.findByOrderIdAndStatusCodeAccepted(1L,
                BASIC_DATA_OBJECT_IN_JSON_PAYLOAD, 202)).thenReturn(
                Optional.ofNullable(getJsonResponsePayload())
        );

        Map<String,String> mapTransaction = new HashMap<>();
        mapTransaction.put("FLOW_ID", "0");

        when(getTransactionInfoUseCase.invoke(anyLong())).thenReturn(mapTransaction);

        final List<GetOrderDetailDTO> result = getOrderDetailOrderUseCase.invoke(
                orderIds
        );

        assertEquals(getOrderDetailDTOSFalse, result);
    }
}
