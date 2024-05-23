package com.robinfood.app.usecases.createorderdeductions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.robinfood.app.datamocks.dto.input.DeliveryDTODataMock;
import com.robinfood.app.datamocks.dto.input.OrderDTODataMock;
import com.robinfood.app.datamocks.dto.input.OrderFiscalIdentifierDTODataMock;
import com.robinfood.app.datamocks.dto.input.RequestPaymentDetailDTODataMock;
import com.robinfood.app.datamocks.dto.input.UserDataDTODataMock;
import com.robinfood.app.usecases.createdorderdeduction.CreateOrderDeductionsUseCase;
import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.dtos.request.transaction.RequestCompanyDTO;
import com.robinfood.core.dtos.request.transaction.RequestDeviceDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO;
import com.robinfood.repository.orderdeduction.IOrderDeductionRepository;
import com.robinfood.repository.orderdeductiontrasaction.IDeductionTransactionRepository;
import com.robinfood.repository.orderproductodeduction.IOrderProductFinalDeductionRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateOrderDeductionsTest {
    @InjectMocks
    private CreateOrderDeductionsUseCase createOrderDeductions;

    @Mock
    private IDeductionTransactionRepository deductionTransactionRepository;

    @Mock
    private IOrderDeductionRepository orderDeductionRepository;

    @Mock
    private IOrderProductFinalDeductionRepository orderProductFinalDeductionRepository;

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
            List.of(),
            new DeliveryDTODataMock().getDataDefault(),
            new RequestDeviceDTO("172.80.4.207", 1L, "America/Bogota", "1.5"),
            3L,
            orderDTODataMock.getDataDefaultListDeduction(),
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
    void test_CreateOrderDeduction_When_Save_Success() {
        inputOrderTransactionValidatedDTO.setDeductions(List.of(new DeductionDTO(
                1L,
                BigDecimal.valueOf(2000)
        )));

        List<Long> idOrders = new ArrayList<>();
        idOrders.add(1L);

        Boolean result = createOrderDeductions.invoke(
                inputOrderTransactionValidatedDTO,1L, idOrders)
                .join();

        assertTrue(result);


    }

    @Test
    void test_CreateOrderDeduction_When_Save_Success_Without_Deductions() {

        List<Long> idOrders = new ArrayList<>();
        idOrders.add(1L);

        Boolean result = createOrderDeductions.invoke(
                        inputOrderTransactionValidatedDTO,1L, idOrders)
                .join();

        assertTrue(result);


    }
}
