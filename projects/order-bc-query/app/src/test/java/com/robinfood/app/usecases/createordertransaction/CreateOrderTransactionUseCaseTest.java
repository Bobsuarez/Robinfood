package com.robinfood.app.usecases.createordertransaction;

import com.robinfood.app.datamocks.dto.input.DeliveryDTODataMock;
import com.robinfood.app.datamocks.dto.input.OrderDTODataMock;
import com.robinfood.app.datamocks.dto.input.OrderFiscalIdentifierDTODataMock;
import com.robinfood.app.datamocks.dto.output.OutputCreatedOrderDTODataMock;
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
import com.robinfood.core.dtos.response.transaction.ResponseTransactionDTO;
import com.robinfood.repository.orderdiscount.IOrderDiscountRepository;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private ICreateOrderFoodCoinsUseCase createOrderFoodCoinsUseCase;

    @Mock
    private ISaveOrderServiceUseCase saveOrderServiceUseCase;

    @Mock
    private IOrderDiscardedService orderDiscardedService;

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
            1L,
            "57"
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

}
