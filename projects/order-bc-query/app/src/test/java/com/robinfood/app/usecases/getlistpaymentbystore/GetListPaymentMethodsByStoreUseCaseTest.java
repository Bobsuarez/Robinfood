package com.robinfood.app.usecases.getlistpaymentbystore;

import com.robinfood.app.datamocks.dto.core.OrderPaymentDTOMock;
import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.datamocks.entity.PaymentMethodEntityMock;
import com.robinfood.app.usecases.getorderpaymentbyorderids.GetOrderPaymentByOrderIdsUseCase;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.report.salebystore.PaymentMethodsOfStoreDTO;
import com.robinfood.repository.paymentmethod.IPaymentMethodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetListPaymentMethodsByStoreUseCaseTest {

    @Mock
    private IPaymentMethodRepository paymentMethodRepository;

    @Mock
    private GetOrderPaymentByOrderIdsUseCase getOrderPaymentByOrderIdsUseCase;

    @InjectMocks
    private GetListPaymentMethodsByStoreUseCase getListPaymentMethodsByStoreUseCase;

    @Test
    void test_When_InputDataFull_Should_OK_Response_PaymentMethodsList() {

        when(getOrderPaymentByOrderIdsUseCase.invoke(anyList()))
                .thenReturn(OrderPaymentDTOMock.getOrderPaymentDTOList());

        when(paymentMethodRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(PaymentMethodEntityMock.getDataDefault()));

        List<PaymentMethodsOfStoreDTO> segmentDTOList = getListPaymentMethodsByStoreUseCase
                .invoke(OrderEntityMock.getDataDefaultList(),
                        OrderEntityMock.getDataDefaultList()
                );

        assertNotNull(segmentDTOList);
    }

    private Map<Long, List<OrderPaymentDTO>> getDataMapOrderPaymentDTO() {
        return OrderPaymentDTOMock.getOrderPaymentDTOList()
                .stream()
                .collect(Collectors.groupingBy(OrderPaymentDTO::getOrderId));
    }
}