package com.robinfood.app.usecases.getorderspaymentmethodsbyposid;

import com.robinfood.app.datamocks.dto.core.GetOrderPaymentMethodsDTOMock;
import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.datamocks.entity.OrderPaymentEntityMock;
import com.robinfood.app.datamocks.entity.PaymentMethodEntityMock;
import com.robinfood.core.dtos.GetOrderPaymentMethodsDTO;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.orderpayment.IOrderPaymentRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.paymentmethod.IPaymentMethodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrdersPaymentMethodsByPosIdUseCaseTest {

    @Mock
    private IOrdersRepository ordersRepository;

    @Mock
    private IPaymentMethodRepository paymentMethodRepository;

    @Mock
    private IOrderPaymentRepository orderPaymentRepository;

    @InjectMocks
    private GetOrdersPaymentMethodsByPosIdUseCase getOrdersPaymentMethodsByPosIdUseCase;

    @Test
    void test_GetDataResponse_Ok_When_DataOrderPaymentMethods() {

        when(ordersRepository.findByCreatedAtBetweenAndPaidAndPosIdAndStatusIdNot(
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                anyBoolean(),
                anyLong(),
                anyLong())
        ).thenReturn(Optional.of(OrderEntityMock.getDataDefaultList()));

        when(orderPaymentRepository.findOrderPaymentEntitiesByOrderIdIn(anyList()))
                .thenReturn(OrderPaymentEntityMock.buildList());

        when(paymentMethodRepository.findById(15L))
                .thenReturn(Optional.of(PaymentMethodEntityMock.getDataDefault()));

        List<GetOrderPaymentMethodsDTO> result =
                getOrdersPaymentMethodsByPosIdUseCase
                        .invoke(
                                LocalDate.parse("2023-01-31"),
                                LocalDate.parse("2023-02-01"),
                                164L,
                                "America/Bogota");

        assertEquals(GetOrderPaymentMethodsDTOMock.orderPaymentsMethods, result);

    }

    @Test
    void test_GetDataResponse_BadRequest_When_DataOrderEntity_Not_Found() {

        when(ordersRepository.findByCreatedAtBetweenAndPaidAndPosIdAndStatusIdNot(
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                anyBoolean(),
                anyLong(),
                anyLong())
        ).thenReturn(Optional.ofNullable(null));

        GenericOrderBcException exception = assertThrows(GenericOrderBcException.class, () -> {
            getOrdersPaymentMethodsByPosIdUseCase
                    .invoke(
                            LocalDate.parse("2023-01-31"),
                            LocalDate.parse("2023-02-01"),
                            164L,
                            "America/Bogota");

        });

        assertEquals("order daily sales not found", exception.getMessage());

    }

    @Test
    void test_GetDataResponse_BadRequest_When_DataPaymentMethodsEntity_Not_Found() {

        when(ordersRepository.findByCreatedAtBetweenAndPaidAndPosIdAndStatusIdNot(
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                anyBoolean(),
                anyLong(),
                anyLong()
                )).thenReturn(Optional.of(OrderEntityMock.getDataDefaultList()));

        when(orderPaymentRepository.findOrderPaymentEntitiesByOrderIdIn(anyList()))
                .thenReturn(OrderPaymentEntityMock.buildList());

        GenericOrderBcException exception = assertThrows(GenericOrderBcException.class, () -> {
            getOrdersPaymentMethodsByPosIdUseCase
                    .invoke(
                            LocalDate.parse("2023-01-31"),
                            LocalDate.parse("2023-02-01"),
                            164L,
                            "America/Bogota");

        });

        assertEquals("paymentMethods not found", exception.getMessage());

    }
}