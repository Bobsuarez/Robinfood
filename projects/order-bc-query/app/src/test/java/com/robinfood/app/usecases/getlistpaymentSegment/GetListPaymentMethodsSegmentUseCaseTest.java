package com.robinfood.app.usecases.getlistpaymentSegment;

import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.datamocks.entity.PaymentMethodEntityMock;
import com.robinfood.app.usecases.getorderpaymentbyorderids.GetOrderPaymentByOrderIdsUseCase;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.report.salebysegment.PaymentMethodSegmentDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.paymentmethod.IPaymentMethodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Collections;
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
class GetListPaymentMethodsSegmentUseCaseTest {

    @Mock
    private IPaymentMethodRepository paymentMethodRepository;

    @Mock
    private GetOrderPaymentByOrderIdsUseCase getOrderPaymentByOrderIdsUseCase;

    @InjectMocks
    private GetListPaymentMethodsSegmentUseCase getPaymentMethodsListSegmentCaseUse;

    @Test
    void test_When_InputDataFull_Should_OK_Response_PaymentMethodsList() {

        when(getOrderPaymentByOrderIdsUseCase.invoke(anyList()))
                .thenReturn(orderPaymentDTOS);

        when(paymentMethodRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(PaymentMethodEntityMock.getDataDefault()));

        List<PaymentMethodSegmentDTO> segmentDTOList = getPaymentMethodsListSegmentCaseUse
                .getListPaymentMethodsSegment(OrderEntityMock.getDataDefaultList(),
                        OrderEntityMock.getDataDefaultList(), List.of(1L, 2L));

        assertNotNull(segmentDTOList);
    }

    @Test
    void test_When_InputDataFullWithIdsFilterNull_Should_OK_Response_PaymentMethodsList() {

        when(paymentMethodRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(PaymentMethodEntityMock.getDataDefault()));

        List<PaymentMethodSegmentDTO> segmentDTOList = getPaymentMethodsListSegmentCaseUse
                .getListPaymentMethodsSegment(OrderEntityMock.getDataDefaultList(),
                        OrderEntityMock.getDataDefaultList(), null);

        assertNotNull(segmentDTOList);
    }

    @Test
    void test_When_filterAndRemoverOrders_Should_ReturnOrderList() {

        when(getOrderPaymentByOrderIdsUseCase.invoke(anyList()))
                .thenReturn(orderPaymentDTOS);

        List<OrderEntity> filterAndRemoverOrders = getPaymentMethodsListSegmentCaseUse
                .filterAndRemoverOrders(OrderEntityMock.getDataDefaultList(), List.of(1L, 2L));

        assertNotNull(filterAndRemoverOrders);
    }

    @Test
    void test_When_IsNullFilterIds_Should_ReturnOrderList() {

        List<OrderEntity> filterAndRemoverOrders = getPaymentMethodsListSegmentCaseUse
                .filterAndRemoverOrders(OrderEntityMock.getDataDefaultList(), null );

        assertNotNull(filterAndRemoverOrders);
    }

    private Map<Long, List<OrderPaymentDTO>> getDataMapOrderPaymentDTO() {
        return orderPaymentDTOS
                .stream()
                .collect(Collectors.groupingBy(OrderPaymentDTO::getOrderId));
    }

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

}