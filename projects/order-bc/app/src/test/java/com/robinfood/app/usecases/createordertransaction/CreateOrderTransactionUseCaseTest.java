package com.robinfood.app.usecases.createordertransaction;

import com.robinfood.app.datamocks.dto.input.DeliveryDTODataMock;
import com.robinfood.app.datamocks.dto.input.OrderDTODataMock;
import com.robinfood.app.datamocks.dto.input.OrderFiscalIdentifierDTODataMock;
import com.robinfood.app.datamocks.dto.input.RequestOrderTransactionDTOMock;
import com.robinfood.app.datamocks.dto.output.OutputCreatedOrderDTODataMock;
import com.robinfood.app.datamocks.dto.output.OutputCreatedOrderTransactionDTODataMock;
import com.robinfood.app.services.orderdiscarded.IOrderDiscardedService;
import com.robinfood.app.usecases.createdorderdeduction.ICreateOrderDeductionsUseCase;
import com.robinfood.app.usecases.createfoodcoins.ICreateOrderFoodCoinsUseCase;
import com.robinfood.app.usecases.createorder.ICreateOrderUseCase;
import com.robinfood.app.usecases.createorderaddress.ICreateOrderAddressUseCase;
import com.robinfood.app.usecases.createorderbrandhistory.ICreateOrderBrandHistoryUseCase;
import com.robinfood.app.usecases.createordercoupon.ICreateOrderCouponUseCase;
import com.robinfood.app.usecases.createorderdetail.ICreateOrderDetailUseCase;
import com.robinfood.app.usecases.createorderdevice.ICreateOrderDeviceUseCase;
import com.robinfood.app.usecases.createorderdiscount.ICreateOrderDiscountUseCase;
import com.robinfood.app.usecases.createorderdiscountproduct.ICreateOrderDiscountProductUseCase;
import com.robinfood.app.usecases.createorderexternaldiscount.ICreateOrderExternalDiscountUseCase;
import com.robinfood.app.usecases.createorderfiscalidentifier.ICreateOrderFiscalIdentifierUseCase;
import com.robinfood.app.usecases.createorderflag.ICreateOrderFlagUseCase;
import com.robinfood.app.usecases.createorderflagcorporate.ICreateOrderFlagCorporateUseCase;
import com.robinfood.app.usecases.createorderflagintegrations.ICreateOrderFlagIntegrationUseCase;
import com.robinfood.app.usecases.createorderflagsubmarine.ICreateOrderFlagSubmarineUseCase;
import com.robinfood.app.usecases.createorderflagtogo.ICreateOrderFlagTogoUseCase;
import com.robinfood.app.usecases.createorderhistory.ICreateOrderHistoryUseCase;
import com.robinfood.app.usecases.createorderintegrations.ICreateOrderIntegrationUseCase;
import com.robinfood.app.usecases.createorderpayments.ICreateOrderPaymentUseCase;
import com.robinfood.app.usecases.createorderuserdata.ICreateOrderUserDataUseCase;
import com.robinfood.app.usecases.createpayments.ICreatePaymentsUseCase;
import com.robinfood.app.usecases.createtransaction.ICreateTransactionUseCase;
import com.robinfood.app.usecases.flushordertransaction.IFlushOrderTransactionUseCase;
import com.robinfood.app.usecases.getsumpaymentmethos.IGetSumPaymentMethodsUseCase;
import com.robinfood.app.usecases.saveorderservices.ISaveOrderServiceUseCase;
import com.robinfood.app.usecases.sendordercreatedtoqueue.ISendOrderCreatedToQueueUseCase;
import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.dtos.OrderCouponDTO;
import com.robinfood.core.dtos.request.order.IntermediateOrderFlagDTO;
import com.robinfood.core.dtos.request.order.OrderFlagCorporateDTO;
import com.robinfood.core.dtos.request.order.OrderFlagIntegrationDTO;
import com.robinfood.core.dtos.request.order.OrderFlagSubmarineDTO;
import com.robinfood.core.dtos.request.order.OrderFlagTogoDTO;
import com.robinfood.core.dtos.request.order.OrderIntegrationDTO;
import com.robinfood.core.dtos.request.transaction.AddressDTO;
import com.robinfood.core.dtos.request.transaction.RequestCompanyDTO;
import com.robinfood.core.dtos.request.transaction.RequestDeviceDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.dtos.request.transaction.RequestPaymentDetailDTO;
import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO;
import com.robinfood.core.dtos.request.transaction.RequestTransactionDTO;
import com.robinfood.core.dtos.request.transaction.RequestUserDTO;
import com.robinfood.core.dtos.response.transaction.ResponseCreatedOrderTransactionDTO;
import com.robinfood.core.dtos.response.transaction.ResponseTransactionDTO;
import com.robinfood.core.exceptions.CannotDivideByZeroException;
import com.robinfood.repository.orderdiscount.IOrderDiscountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderTransactionUseCaseTest {

    @Mock
    private IFlushOrderTransactionUseCase flushOrderTransactionUseCase;

    @Mock
    private ICreateOrderDeviceUseCase createOrderDevice;

    @Mock
    private ICreateTransactionUseCase createTransactionUseCase;

    @Mock
    private ICreatePaymentsUseCase createPaymentsUseCase;

    @Mock
    private ICreateOrderCouponUseCase createOrderCouponUseCase;

    @Mock
    private ICreateOrderUseCase createOrderUseCase;

    @Mock
    private ICreateOrderPaymentUseCase createOrderPaymentUseCase;

    @Mock
    private ICreateOrderDetailUseCase createOrderDetailUseCase;

    @Mock
    private ICreateOrderDeductionsUseCase mockCreateOrderDeduction;

    @Mock
    private ICreateOrderHistoryUseCase createOrderHistoryUseCase;

    @Mock
    private ICreateOrderAddressUseCase createOrderAddressUseCase;

    @Mock
    private ICreateOrderBrandHistoryUseCase createOrderBrandHistoryUseCase;

    @Mock
    private ICreateOrderUserDataUseCase createOrderUserDataUseCase;

    @Mock
    private ICreateOrderIntegrationUseCase createOrderIntegrationUseCase;

    @Mock
    private ICreateOrderFlagSubmarineUseCase createOrderFlagSubmarineUseCase;

    @Mock
    private ICreateOrderFlagTogoUseCase createOrderFlagTogoUseCase;

    @Mock
    private ICreateOrderFlagCorporateUseCase createOrderFlagCorporateUseCase;

    @Mock
    private ICreateOrderDiscountUseCase createOrderDiscountUseCase;

    @Mock
    private ICreateOrderDiscountProductUseCase createOrderDiscountProductUseCase;

    @Mock
    private ICreateOrderExternalDiscountUseCase createOrderExternalDiscountUseCase;

    @Mock
    private ICreateOrderFlagIntegrationUseCase createOrderFlagIntegrationsUseCase;

    @Mock
    private ICreateOrderFlagUseCase createOrderFlagUseCase;

    @Mock
    private ICreateOrderFiscalIdentifierUseCase createOrderFiscalIdentifierUseCase;

    @Mock
    private IGetSumPaymentMethodsUseCase getIsPaymentTotalOrder;

    @Mock
    private IOrderDiscountRepository orderDiscountRepository;

    @Mock
    private ISendOrderCreatedToQueueUseCase sendOrderCreatedToQueueUseCase;

    @Mock
    private ICreateOrderFoodCoinsUseCase createOrderFoodCoinsUseCase;

    @Mock
    private ISaveOrderServiceUseCase saveOrderServiceUseCase;

    @Mock
    private IOrderDiscardedService orderDiscardedService;

    @InjectMocks
    private CreateOrderTransactionUseCase createOrderTransactionUseCase;

    private final OrderDTODataMock orderDTODataMock = new OrderDTODataMock();

    private final RequestPaymentDetailDTO requestPaymentDetailDTO = new RequestPaymentDetailDTO(
            "hola",
            "hola",
            "hola",
            "hola",
            1L,
            1L,
            "hola",
            1L,
            "HOLA",
            1L
    );

    private final List<RequestPaymentMethodDTO> requestPaymentMethodDTOList = new ArrayList<>(Collections.singletonList(
            new RequestPaymentMethodDTO(
                    requestPaymentDetailDTO,
                    1L,
                    1L,
                    100.0
            )
    ));

    private final List<RequestPaymentMethodDTO> requestPaymentsPaidDTOList = new ArrayList<>(Collections.singletonList(
            new RequestPaymentMethodDTO(
                    requestPaymentDetailDTO,
                    7L,
                    4L,
                    1000.0
            )
    ));

    private final RequestUserDTO requestUserDTO = new RequestUserDTO(
            "hola",
            1L,
            "David",
            "3006574641",
            "Juan",
            1L,
            "57",
            1L
    );

    private final AddressDTO addresDTO = AddressDTO.builder()
            .address("CR 69")
            .cityId(1)
            .countryId(1)
            .cityId(1)
            .latitude("1")
            .longitude("1")
            .build();

    private final RequestOrderTransactionDTO inputOrderTransactionValidatedDTO = new RequestOrderTransactionDTO(
            false,
            new RequestCompanyDTO("col", 1L),
            List.of(OrderCouponDTO.builder()
                    .code("CODEOK")
                    .redeemedId("1234")
                    .value(BigDecimal.valueOf(1000L))
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
            requestUserDTO,
            1L,
            UUID.fromString("50eaf34f-7252-46ef-9a69-2225b06e14be")
    );

    private final RequestOrderTransactionDTO inputOrderTransactionValidatedDTOWithOrderIdIsNull = new RequestOrderTransactionDTO(
            false,
            new RequestCompanyDTO("col", 1L),
            List.of(OrderCouponDTO.builder()
                    .code("CODEOK")
                    .redeemedId("1234")
                    .value(BigDecimal.valueOf(1000L))
                    .couponType(1L)
                    .build()),
            null,
            new RequestDeviceDTO("172.80.4.207", 1L, "America/Bogota", "1.5"),
            3L,
            List.of(orderDTODataMock.getDataDefaultWithOrderIdIsNull()),
            new OrderFiscalIdentifierDTODataMock().getDataDefault(),
            true,
            requestPaymentMethodDTOList,
            requestPaymentsPaidDTOList,
            BigDecimal.ZERO,
            100.0,
            requestUserDTO,
            1L,
            UUID.fromString("50eaf34f-7252-46ef-9a69-2225b06e14be")
    );
    private final RequestOrderTransactionDTO transactionDTO = RequestOrderTransactionDTOMock.inputOrderTransactionValidatedDTO();
    private final RequestOrderTransactionDTO inputOrderTransactionValidatedDTOWithDataAddress = new RequestOrderTransactionDTO(
            AddressDTO.builder()
                    .address("CR 69")
                    .cityId(1)
                    .countryId(1)
                    .cityId(1)
                    .latitude("1")
                    .longitude("1")
                    .build(),
            true,
            new RequestCompanyDTO("col", 1L),
            List.of(new DeductionDTO(
                    1L,
                    BigDecimal.valueOf(2000)
            )),
            List.of(OrderCouponDTO.builder()
                    .code("CODEOK")
                    .redeemedId("1234")
                    .value(BigDecimal.valueOf(1000L))
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
            requestUserDTO,
            UUID.fromString("50eaf34f-7252-46ef-9a69-2225b06e14be"),
            1L
    );

    private final RequestOrderTransactionDTO inputOrderTransactionNotValidatedDTO = new RequestOrderTransactionDTO(
            false,
            new RequestCompanyDTO("col", 1L),
            List.of(OrderCouponDTO.builder()
                    .code("CODEOK")
                    .redeemedId("1234")
                    .value(BigDecimal.valueOf(1000L))
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
            requestUserDTO,
            1L,
            UUID.fromString("50eaf34f-7252-46ef-9a69-2225b06e14be")
    );

    private final RequestTransactionDTO requestTransactionDTO = new RequestTransactionDTO(
            1L,
            1L,
            true,
            100.0,
            "50eaf34f-7252-46ef-9a69-2225b06e14be",
            1L
    );

    private final ResponseTransactionDTO responseTransactionDTO = new ResponseTransactionDTO(
            null,
            null,
            1L,
            true,
            "50eaf34f-7252-46ef-9a69-2225b06e14be",
            1L,
            100.0
    );

    private final OutputCreatedOrderDTODataMock outputCreatedOrderDTO = new OutputCreatedOrderDTODataMock();

    private final RequestCompanyDTO requestCompanyDTO = new RequestCompanyDTO(
            "col",
            1L
    );

    private final OutputCreatedOrderDTODataMock outputCreatedOrderDTODataMock = new OutputCreatedOrderDTODataMock();

    private final List<RequestUserDTO> requestUserDTOList = new ArrayList<>(Collections.singletonList(
            requestUserDTO
    ));

    private final List<OrderIntegrationDTO> orderIntegrationDTOList = new ArrayList<>(Collections.singletonList(
            new OrderIntegrationDTO(
                    "address City",
                    "addressDescription",
                    Date.valueOf(LocalDate.of(2021, 01, 01)),
                    Time.valueOf(LocalTime.of(12, 20, 01)),
                    1L,
                    "Muy",
                    "code",
                    "1",
                    "notes",
                    1L,
                    10L,
                    "Cajas V2",
                    1,
                    "Efectivo",
                    1L,
                    "MUY 79",
                    10000.0,
                    100.0,
                    100.0,
                    10000.0,
                    "Juan David",
                    "3006574641"
            )
    ));

    private final List<OrderFlagSubmarineDTO> orderFlagSubmarineDTOList = new ArrayList<>(Collections.singletonList(
            new OrderFlagSubmarineDTO(
                    1L,
                    true,
                    1L
            )
    ));

    private final List<OrderFlagTogoDTO> orderFlagTogoDTOList = new ArrayList<>(Collections.emptyList());

    private final List<OrderFlagCorporateDTO> orderFlagCorporateDTOList = new ArrayList<>(Collections.emptyList());

    private final List<OrderFlagIntegrationDTO> orderFlagIntegrationDTOList = new ArrayList<>(Collections.singletonList(
            new OrderFlagIntegrationDTO(
                    null,
                    1L
            )
    ));

    private final List<IntermediateOrderFlagDTO> intermediateOrderFlagDTOList = new ArrayList<>(Arrays.asList(
            new IntermediateOrderFlagDTO(
                    "submarine",
                    null,
                    1L
            )
    ));

    private final List<Long> orderIds = new ArrayList<>(Collections.singletonList(
            1L
    ));

    @Test
    void test_Create_Order_Transaction_Validated() throws CannotDivideByZeroException {

        when(createOrderDevice.invoke(inputOrderTransactionValidatedDTO.getDevice(), orderIds))
                .thenReturn(CompletableFuture.completedFuture(null));

        when(flushOrderTransactionUseCase.invoke(inputOrderTransactionValidatedDTO))
                .thenReturn(CompletableFuture.completedFuture(null));

        when(createTransactionUseCase.invoke(any(RequestTransactionDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(responseTransactionDTO));

        when(createPaymentsUseCase.invoke(requestPaymentMethodDTOList, 1L))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderUseCase.invoke(
                inputOrderTransactionValidatedDTO,
                orderDTODataMock.getDataDefaultList(),
                1L,
                8900.0
        )).thenReturn(outputCreatedOrderDTO.getDataDefaultList());

        when(createOrderPaymentUseCase.invoke(
                requestPaymentMethodDTOList,
                outputCreatedOrderDTODataMock.getDataDefaultList()
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDetailUseCase.invoke(
                orderDTODataMock.getDataDefaultList(),
                outputCreatedOrderDTODataMock.getDataDefaultList()
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderHistoryUseCase.invoke(
                orderDTODataMock.getDataDefault(1L).getNotes(),
                1L,
                8900.0,
                true,
                inputOrderTransactionValidatedDTO,
                8900.0,
                1L

        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderBrandHistoryUseCase.invoke(
                orderDTODataMock.getDataDefault(1L).getFinalProducts(),
                1L,
                8900.0,
                true,
                inputOrderTransactionValidatedDTO,
                8900.0,
                1L
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderUserDataUseCase.invoke(requestUserDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderIntegrationUseCase.invoke(any()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagSubmarineUseCase.invoke(orderFlagSubmarineDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagTogoUseCase.invoke(orderFlagTogoDTOList))
                .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderFlagCorporateUseCase.invoke(orderFlagCorporateDTOList))
                .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderDiscountUseCase.invoke(
                orderDTODataMock.getDataDefaultList(),
                new ArrayList<>(Collections.singletonList(1L)
                ))
        ).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDiscountProductUseCase.invoke(
                orderDTODataMock.getDataDefaultList(),
                new ArrayList<>(Collections.singletonList(1L)
                ))
        ).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagIntegrationsUseCase.invoke(any()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFoodCoinsUseCase.invoke(inputOrderTransactionValidatedDTO, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(saveOrderServiceUseCase.invoke(inputOrderTransactionValidatedDTO, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagUseCase.invoke(intermediateOrderFlagDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(getIsPaymentTotalOrder.invoke(inputOrderTransactionValidatedDTO.getPaymentMethods()))
                .thenReturn(8900.0);

        Mockito.when(mockCreateOrderDeduction.invoke(inputOrderTransactionValidatedDTO, 1L, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        ResponseCreatedOrderTransactionDTO responseCreatedOrderTransactionDTO = createOrderTransactionUseCase
                .invoke(inputOrderTransactionValidatedDTO);

        Assertions.assertEquals(new OutputCreatedOrderTransactionDTODataMock().getDataDefault(), responseCreatedOrderTransactionDTO);
    }

    @Test
    void test_Create_Order_Transaction_Validated_Delivery() throws CannotDivideByZeroException {

        inputOrderTransactionValidatedDTO.setOrders(orderDTODataMock.getDataDefaultDeliveryExternalList());

        when(createOrderDevice.invoke(inputOrderTransactionValidatedDTO.getDevice(), orderIds))
                .thenReturn(CompletableFuture.completedFuture(null));

        when(flushOrderTransactionUseCase.invoke(inputOrderTransactionValidatedDTO))
                .thenReturn(CompletableFuture.completedFuture(null));

        when(createTransactionUseCase.invoke(any(RequestTransactionDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(responseTransactionDTO));

        when(createPaymentsUseCase.invoke(requestPaymentMethodDTOList, 1L))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderUseCase.invoke(
                inputOrderTransactionValidatedDTO,
                orderDTODataMock.getDataDefaultDeliveryExternalList(),
                1L,
                8900.0
        )).thenReturn(outputCreatedOrderDTO.getDataDefaultList());

        when(createOrderPaymentUseCase.invoke(
                requestPaymentMethodDTOList,
                outputCreatedOrderDTODataMock.getDataDefaultList()
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDetailUseCase.invoke(
                orderDTODataMock.getDataDefaultDeliveryExternalList(),
                outputCreatedOrderDTODataMock.getDataDefaultList()
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderHistoryUseCase.invoke(
                orderDTODataMock.getDataDefault(4L).getNotes(),
                1L,
                8900.0,
                true,
                inputOrderTransactionValidatedDTO,
                8900.0,
                1L
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderBrandHistoryUseCase.invoke(
                orderDTODataMock.getDataDefault(4L).getFinalProducts(),
                1L,
                8900.0,
                true,
                inputOrderTransactionValidatedDTO,
                8900.0,
                1L
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderUserDataUseCase.invoke(requestUserDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderIntegrationUseCase.invoke(any()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagSubmarineUseCase.invoke(orderFlagSubmarineDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagTogoUseCase.invoke(orderFlagTogoDTOList))
                .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderFlagCorporateUseCase.invoke(orderFlagCorporateDTOList))
                .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderDiscountUseCase.invoke(
                orderDTODataMock.getDataDefaultDeliveryExternalList(),
                new ArrayList<>(Collections.singletonList(1L)
                ))
        ).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDiscountProductUseCase.invoke(
                orderDTODataMock.getDataDefaultDeliveryExternalList(),
                new ArrayList<>(Collections.singletonList(1L)
                ))
        ).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagIntegrationsUseCase.invoke(any()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFoodCoinsUseCase.invoke(inputOrderTransactionValidatedDTO, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(saveOrderServiceUseCase.invoke(inputOrderTransactionValidatedDTO, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagUseCase.invoke(any()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(getIsPaymentTotalOrder.invoke(inputOrderTransactionValidatedDTO.getPaymentMethods()))
                .thenReturn(8900.0);

        Mockito.when(mockCreateOrderDeduction.invoke(inputOrderTransactionValidatedDTO, 1L, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        ResponseCreatedOrderTransactionDTO responseCreatedOrderTransactionDTO = createOrderTransactionUseCase
                .invoke(inputOrderTransactionValidatedDTO);

        Assertions.assertEquals(new OutputCreatedOrderTransactionDTODataMock().getDataDefault(), responseCreatedOrderTransactionDTO);
    }

    @Test
    void test_Create_Order_Transaction_Validated_Delivery_Type_Pos() throws CannotDivideByZeroException {

        inputOrderTransactionValidatedDTO.setOrders(orderDTODataMock.getDataDefaultDeliveryPosList());

        inputOrderTransactionValidatedDTO.setOrderFiscalIdentifier(null);
        inputOrderTransactionValidatedDTO.getOrders().get(0).getFlags().getCorporate().setIsActive(true);
        inputOrderTransactionValidatedDTO.getOrders().get(0).getFlags().getPits().setIsActive(true);
        inputOrderTransactionValidatedDTO.getOrders().get(0).getFlags().getTogo().setIsActive(true);
        inputOrderTransactionValidatedDTO.getOrders().get(0).getFlags().getSubmarine().setIsActive(true);
        inputOrderTransactionValidatedDTO.getDelivery().setTotalDiscount(0.0);

        when(createOrderDevice.invoke(inputOrderTransactionValidatedDTO.getDevice(), orderIds))
                .thenReturn(CompletableFuture.completedFuture(null));

        when(flushOrderTransactionUseCase.invoke(inputOrderTransactionValidatedDTO))
                .thenReturn(CompletableFuture.completedFuture(null));

        when(createTransactionUseCase.invoke(any(RequestTransactionDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(responseTransactionDTO));

        when(createPaymentsUseCase.invoke(requestPaymentMethodDTOList, 1L))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderUseCase.invoke(
                inputOrderTransactionValidatedDTO,
                orderDTODataMock.getDataDefaultDeliveryPosList(),
                1L,
                8900.0
        )).thenReturn(outputCreatedOrderDTO.getDataDefaultList());

        when(createOrderPaymentUseCase.invoke(
                requestPaymentMethodDTOList,
                outputCreatedOrderDTODataMock.getDataDefaultList()
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDetailUseCase.invoke(
                orderDTODataMock.getDataDefaultDeliveryPosList(),
                outputCreatedOrderDTODataMock.getDataDefaultList()
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderHistoryUseCase.invoke(
                orderDTODataMock.getDataDefault(2L).getNotes(),
                1L,
                8900.0,
                true,
                inputOrderTransactionValidatedDTO,
                8900.0,
                1L
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderBrandHistoryUseCase.invoke(
                orderDTODataMock.getDataDefault(2L).getFinalProducts(),
                1L,
                8900.0,
                true,
                inputOrderTransactionValidatedDTO,
                8900.0,
                1L
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderUserDataUseCase.invoke(requestUserDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderIntegrationUseCase.invoke(orderIntegrationDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagSubmarineUseCase.invoke(orderFlagSubmarineDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagTogoUseCase.invoke(
                List.of(
                        inputOrderTransactionValidatedDTO.getOrders().get(0)
                                .getFlags().getTogo()
                )
        ))
                .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderFlagCorporateUseCase.invoke(
                List.of(
                        inputOrderTransactionValidatedDTO.getOrders().get(0)
                                .getFlags().getCorporate()
                )
        ))
                .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderDiscountUseCase.invoke(
                orderDTODataMock.getDataDefaultDeliveryPosList(),
                new ArrayList<>(Collections.singletonList(1L)
                ))
        ).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDiscountProductUseCase.invoke(
                orderDTODataMock.getDataDefaultDeliveryPosList(),
                new ArrayList<>(Collections.singletonList(1L)
                ))
        ).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagIntegrationsUseCase.invoke(orderFlagIntegrationDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFoodCoinsUseCase.invoke(inputOrderTransactionValidatedDTO, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(saveOrderServiceUseCase.invoke(inputOrderTransactionValidatedDTO, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagUseCase.invoke(any()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(getIsPaymentTotalOrder.invoke(inputOrderTransactionValidatedDTO.getPaymentMethods()))
                .thenReturn(8900.0);

        Mockito.when(mockCreateOrderDeduction.invoke(inputOrderTransactionValidatedDTO, 1L, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        ResponseCreatedOrderTransactionDTO responseCreatedOrderTransactionDTO = createOrderTransactionUseCase
                .invoke(inputOrderTransactionValidatedDTO);

        verify(createOrderDevice).invoke(any(), any());
        verify(flushOrderTransactionUseCase).invoke(any());
        verify(createPaymentsUseCase).invoke(any(), any());

        Assertions.assertNotNull(responseCreatedOrderTransactionDTO);
    }

    @Test
    void test_not_send_created_order_message() throws CannotDivideByZeroException {

        inputOrderTransactionValidatedDTO.setId(null);

        when(createOrderDevice.invoke(any(), any()))
                .thenReturn(CompletableFuture.completedFuture(null));

        when(flushOrderTransactionUseCase.invoke(any()))
                .thenReturn(CompletableFuture.completedFuture(null));

        when(createTransactionUseCase.invoke(any()))
                .thenReturn(CompletableFuture.completedFuture(responseTransactionDTO));

        when(createPaymentsUseCase.invoke(any(), anyLong()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderUseCase.invoke(any(), any(), anyLong(), anyDouble()))
                .thenReturn(outputCreatedOrderDTO.getDataDefaultList());

        when(createOrderPaymentUseCase.invoke(anyList(), anyList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDetailUseCase.invoke(anyList(), anyList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderHistoryUseCase
                .invoke(anyString(), anyLong(), anyDouble(), anyBoolean(),any(RequestOrderTransactionDTO.class),
                        anyDouble(),
                        anyLong())
        ).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderBrandHistoryUseCase.invoke(
                anyList(), anyLong(), anyDouble(), anyBoolean(),
                any(RequestOrderTransactionDTO.class),
                anyDouble(), anyLong()
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderUserDataUseCase.invoke(anyList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderIntegrationUseCase.invoke(anyList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagSubmarineUseCase.invoke(anyList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagTogoUseCase.invoke(anyList()))
                .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderFlagCorporateUseCase.invoke(anyList()))
                .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderDiscountUseCase.invoke(anyList(), anyList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFoodCoinsUseCase.invoke(inputOrderTransactionValidatedDTO, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(saveOrderServiceUseCase.invoke(inputOrderTransactionValidatedDTO, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDiscountProductUseCase.invoke(anyList(), anyList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagIntegrationsUseCase.invoke(anyList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagUseCase.invoke(anyList()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFiscalIdentifierUseCase.invoke(any(), anyLong()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(getIsPaymentTotalOrder.invoke(anyList())).thenReturn(8900.0);

        Mockito.when(mockCreateOrderDeduction.invoke(inputOrderTransactionValidatedDTO, 1L, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        ResponseCreatedOrderTransactionDTO responseCreatedOrderTransactionDTO = createOrderTransactionUseCase
                .invoke(inputOrderTransactionValidatedDTO);

        Assertions.assertEquals(new OutputCreatedOrderTransactionDTODataMock().getDataDefault(), responseCreatedOrderTransactionDTO);
        verify(sendOrderCreatedToQueueUseCase, times(0)).invoke(any(), any());
    }

    @Test
    void test_Create_Order_Transaction__Not_Validated() throws CannotDivideByZeroException {

        when(createOrderDevice.invoke(inputOrderTransactionNotValidatedDTO.getDevice(), orderIds))
                .thenReturn(CompletableFuture.completedFuture(null));

        when(flushOrderTransactionUseCase.invoke(inputOrderTransactionNotValidatedDTO))
                .thenReturn(CompletableFuture.completedFuture(null));

        when(createTransactionUseCase.invoke(any(RequestTransactionDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(responseTransactionDTO));

        when(createPaymentsUseCase.invoke(requestPaymentMethodDTOList, 1L))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderPaymentUseCase.invoke(any(), any()))
                .thenReturn(CompletableFuture.completedFuture(true));

        inputOrderTransactionValidatedDTO.setAlreadyValidated(false);

        when(createOrderUseCase.invoke(
                inputOrderTransactionValidatedDTO,
                orderDTODataMock.getDataDefaultList(),
                1L,
                8900.0
        )).thenReturn(outputCreatedOrderDTO.getDataDefaultList());

        Mockito.when(mockCreateOrderDeduction.invoke(inputOrderTransactionNotValidatedDTO, 1L, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDetailUseCase.invoke(
                orderDTODataMock.getDataDefaultList(),
                outputCreatedOrderDTODataMock.getDataDefaultList()
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderHistoryUseCase.invoke(
                orderDTODataMock.getDataDefault(4L).getNotes(),
                1L,
                8900.0,
                true,
                inputOrderTransactionNotValidatedDTO,
                8900.0,
                1L
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderBrandHistoryUseCase.invoke(
                orderDTODataMock.getDataDefault(4L).getFinalProducts(),
                1L,
                8900.0,
                true,
                inputOrderTransactionNotValidatedDTO,
                8900.0,
                1L
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderUserDataUseCase.invoke(requestUserDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderIntegrationUseCase.invoke(any()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagSubmarineUseCase.invoke(orderFlagSubmarineDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFoodCoinsUseCase.invoke(inputOrderTransactionNotValidatedDTO, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(saveOrderServiceUseCase.invoke(inputOrderTransactionNotValidatedDTO, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagTogoUseCase.invoke(orderFlagTogoDTOList))
                .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderFlagCorporateUseCase.invoke(orderFlagCorporateDTOList))
                .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderDiscountUseCase.invoke(
                orderDTODataMock.getDataDefaultList(),
                new ArrayList<>(Collections.singletonList(1L)
                ))
        ).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDiscountProductUseCase.invoke(
                orderDTODataMock.getDataDefaultList(),
                new ArrayList<>(Collections.singletonList(1L)
                ))
        ).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagIntegrationsUseCase.invoke(any()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagUseCase.invoke(intermediateOrderFlagDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(getIsPaymentTotalOrder.invoke(inputOrderTransactionValidatedDTO.getPaymentMethods()))
                .thenReturn(8900.0);

        ResponseCreatedOrderTransactionDTO responseCreatedOrderTransactionDTO = createOrderTransactionUseCase
                .invoke(inputOrderTransactionNotValidatedDTO);

        Assertions.assertNotNull(responseCreatedOrderTransactionDTO);

        verify(createOrderFlagIntegrationsUseCase).invoke(any());
    }

    @Test
    void test_Create_Order_Transaction_Validated_With_Address_Data() throws CannotDivideByZeroException {

        when(createOrderDevice.invoke(inputOrderTransactionValidatedDTOWithDataAddress.getDevice(), orderIds))
                .thenReturn(CompletableFuture.completedFuture(null));

        when(flushOrderTransactionUseCase.invoke(inputOrderTransactionValidatedDTOWithDataAddress))
                .thenReturn(CompletableFuture.completedFuture(null));

        when(createTransactionUseCase.invoke(any(RequestTransactionDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(responseTransactionDTO));

        when(createPaymentsUseCase.invoke(requestPaymentMethodDTOList, 1L))
                .thenReturn(CompletableFuture.completedFuture(true));

        Mockito.when(mockCreateOrderDeduction.invoke(inputOrderTransactionValidatedDTOWithDataAddress, 1L, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderUseCase.invoke(
                inputOrderTransactionValidatedDTOWithDataAddress,
                orderDTODataMock.getDataDefaultList(),
                1L,
                8900.0
        )).thenReturn(outputCreatedOrderDTO.getDataDefaultList());

        when(createOrderPaymentUseCase.invoke(
                requestPaymentMethodDTOList,
                outputCreatedOrderDTODataMock.getDataDefaultList()
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDetailUseCase.invoke(
                orderDTODataMock.getDataDefaultList(),
                outputCreatedOrderDTODataMock.getDataDefaultList()
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderHistoryUseCase.invoke(
                orderDTODataMock.getDataDefault(4L).getNotes(),
                1L,
                8900.0,
                true,
                inputOrderTransactionValidatedDTOWithDataAddress,
                8900.0,
                1L
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderBrandHistoryUseCase.invoke(
                orderDTODataMock.getDataDefault(4L).getFinalProducts(),
                1L,
                8900.0,
                true,
                inputOrderTransactionValidatedDTOWithDataAddress,
                8900.0,
                1L
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderUserDataUseCase.invoke(requestUserDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderIntegrationUseCase.invoke(any()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagSubmarineUseCase.invoke(orderFlagSubmarineDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagTogoUseCase.invoke(orderFlagTogoDTOList))
                .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderFoodCoinsUseCase.invoke(inputOrderTransactionValidatedDTOWithDataAddress, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(saveOrderServiceUseCase.invoke(inputOrderTransactionValidatedDTOWithDataAddress, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagCorporateUseCase.invoke(orderFlagCorporateDTOList))
                .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderDiscountUseCase.invoke(
                orderDTODataMock.getDataDefaultList(),
                new ArrayList<>(Collections.singletonList(1L)
                ))
        ).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDiscountProductUseCase.invoke(
                orderDTODataMock.getDataDefaultList(),
                new ArrayList<>(Collections.singletonList(1L)
                ))
        ).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagIntegrationsUseCase.invoke(any()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagUseCase.invoke(intermediateOrderFlagDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(getIsPaymentTotalOrder.invoke(inputOrderTransactionValidatedDTOWithDataAddress.getPaymentMethods()))
                .thenReturn(8900.0);

        ResponseCreatedOrderTransactionDTO responseCreatedOrderTransactionDTO = createOrderTransactionUseCase
                .invoke(inputOrderTransactionValidatedDTOWithDataAddress);

        Assertions.assertEquals(new OutputCreatedOrderTransactionDTODataMock().getDataDefault(), responseCreatedOrderTransactionDTO);
    }

    @Test
    void test_Create_Order_Transaction_Validated_With_Coupons_Empty() throws CannotDivideByZeroException {

        when(createOrderDevice.invoke(inputOrderTransactionValidatedDTOWithDataAddress.getDevice(), orderIds))
                .thenReturn(CompletableFuture.completedFuture(null));

        when(flushOrderTransactionUseCase.invoke(inputOrderTransactionValidatedDTOWithDataAddress))
                .thenReturn(CompletableFuture.completedFuture(null));

        when(createTransactionUseCase.invoke(any(RequestTransactionDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(responseTransactionDTO));

        when(createPaymentsUseCase.invoke(requestPaymentMethodDTOList, 1L))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderUseCase.invoke(
                inputOrderTransactionValidatedDTOWithDataAddress,
                orderDTODataMock.getDataDefaultList(),
                1L,
                8900.0
        )).thenReturn(outputCreatedOrderDTO.getDataDefaultList());

        when(createOrderPaymentUseCase.invoke(
                requestPaymentMethodDTOList,
                outputCreatedOrderDTODataMock.getDataDefaultList()
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDetailUseCase.invoke(
                orderDTODataMock.getDataDefaultList(),
                outputCreatedOrderDTODataMock.getDataDefaultList()
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderHistoryUseCase.invoke(
                orderDTODataMock.getDataDefault(4L).getNotes(),
                1L,
                8900.0,
                true,
                inputOrderTransactionValidatedDTOWithDataAddress,
                8900.0,
                1L
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderBrandHistoryUseCase.invoke(
                orderDTODataMock.getDataDefault(4L).getFinalProducts(),
                1L,
                8900.0,
                true,
                inputOrderTransactionValidatedDTOWithDataAddress,
                8900.0,
                1L
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderUserDataUseCase.invoke(requestUserDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderIntegrationUseCase.invoke(any()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagSubmarineUseCase.invoke(orderFlagSubmarineDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagTogoUseCase.invoke(orderFlagTogoDTOList))
                .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderFlagCorporateUseCase.invoke(orderFlagCorporateDTOList))
                .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderFlagCorporateUseCase.invoke(orderFlagCorporateDTOList))
                .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderDiscountUseCase.invoke(
                orderDTODataMock.getDataDefaultList(),
                new ArrayList<>(Collections.singletonList(1L)
                ))
        ).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDiscountProductUseCase.invoke(
                orderDTODataMock.getDataDefaultList(),
                new ArrayList<>(Collections.singletonList(1L)
                ))
        ).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagIntegrationsUseCase.invoke(any()))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagUseCase.invoke(intermediateOrderFlagDTOList))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFoodCoinsUseCase.invoke(inputOrderTransactionValidatedDTOWithDataAddress, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(saveOrderServiceUseCase.invoke(inputOrderTransactionValidatedDTOWithDataAddress, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        Mockito.when(mockCreateOrderDeduction.invoke(inputOrderTransactionValidatedDTOWithDataAddress, 1L, orderIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(getIsPaymentTotalOrder.invoke(inputOrderTransactionValidatedDTOWithDataAddress.getPaymentMethods()))
                .thenReturn(8900.0);

        inputOrderTransactionValidatedDTOWithDataAddress.setCouponResponseEntities(null);

        ResponseCreatedOrderTransactionDTO responseCreatedOrderTransactionDTO = createOrderTransactionUseCase
                .invoke(inputOrderTransactionValidatedDTOWithDataAddress);

        Assertions.assertNotNull(responseCreatedOrderTransactionDTO);
    }

    @Test
    void test_Create_Order_Transaction_Validated_When_OrderID_Is_Null() throws CannotDivideByZeroException {

        when(createOrderDevice.invoke(inputOrderTransactionValidatedDTOWithOrderIdIsNull.getDevice(), orderIds))
            .thenReturn(CompletableFuture.completedFuture(null));

        when(flushOrderTransactionUseCase.invoke(inputOrderTransactionValidatedDTOWithOrderIdIsNull))
            .thenReturn(CompletableFuture.completedFuture(null));

        when(createTransactionUseCase.invoke(any(RequestTransactionDTO.class)))
            .thenReturn(CompletableFuture.completedFuture(responseTransactionDTO));

        when(createPaymentsUseCase.invoke(requestPaymentMethodDTOList, 1L))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderUseCase.invoke(
            inputOrderTransactionValidatedDTOWithOrderIdIsNull,
            List.of(orderDTODataMock.getDataDefaultWithOrderIdIsNull()),
            1L,
            8900.0
        )).thenReturn(outputCreatedOrderDTO.getDataDefaultList());

        when(createOrderPaymentUseCase.invoke(
            requestPaymentMethodDTOList,
            outputCreatedOrderDTODataMock.getDataDefaultList()
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDetailUseCase.invoke(
            List.of(orderDTODataMock.getDataDefaultWithOrderIdIsNull()),
            outputCreatedOrderDTODataMock.getDataDefaultList()
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderHistoryUseCase.invoke(
            orderDTODataMock.getDataDefault(1L).getNotes(),
            1L,
            8900.0,
            true,
                inputOrderTransactionValidatedDTOWithOrderIdIsNull,
            8900.0,
            1L
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderBrandHistoryUseCase.invoke(
            orderDTODataMock.getDataDefault(1L).getFinalProducts(),
            1L,
            8900.0,
            true,
                inputOrderTransactionValidatedDTOWithOrderIdIsNull,
            8900.0,
            1L
        )).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderUserDataUseCase.invoke(requestUserDTOList))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderIntegrationUseCase.invoke(any()))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagSubmarineUseCase.invoke(orderFlagSubmarineDTOList))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagTogoUseCase.invoke(orderFlagTogoDTOList))
            .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderFlagCorporateUseCase.invoke(orderFlagCorporateDTOList))
            .thenReturn(CompletableFuture.completedFuture(false));

        when(createOrderDiscountUseCase.invoke(
            List.of(orderDTODataMock.getDataDefaultWithOrderIdIsNull()),
            new ArrayList<>(Collections.singletonList(1L)
            ))
        ).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderDiscountProductUseCase.invoke(
            List.of(orderDTODataMock.getDataDefaultWithOrderIdIsNull()),
            new ArrayList<>(Collections.singletonList(1L)
            ))
        ).thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagIntegrationsUseCase.invoke(any()))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFoodCoinsUseCase.invoke(inputOrderTransactionValidatedDTOWithOrderIdIsNull, orderIds))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(saveOrderServiceUseCase.invoke(inputOrderTransactionValidatedDTOWithOrderIdIsNull, orderIds))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(createOrderFlagUseCase.invoke(intermediateOrderFlagDTOList))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(getIsPaymentTotalOrder.invoke(inputOrderTransactionValidatedDTOWithOrderIdIsNull.getPaymentMethods()))
            .thenReturn(8900.0);

        Mockito.when(mockCreateOrderDeduction.invoke(inputOrderTransactionValidatedDTOWithOrderIdIsNull, 1L, orderIds))
            .thenReturn(CompletableFuture.completedFuture(true));

        ResponseCreatedOrderTransactionDTO responseCreatedOrderTransactionDTO = createOrderTransactionUseCase
            .invoke(inputOrderTransactionValidatedDTOWithOrderIdIsNull);

        Assertions.assertNotNull(responseCreatedOrderTransactionDTO);
    }
}
