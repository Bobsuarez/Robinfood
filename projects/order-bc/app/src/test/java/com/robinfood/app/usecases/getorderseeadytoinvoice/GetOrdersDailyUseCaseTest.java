package com.robinfood.app.usecases.getorderseeadytoinvoice;

import com.robinfood.app.datamocks.dto.core.OrderDTOMock;
import com.robinfood.app.datamocks.dto.core.OriginDTOMock;
import com.robinfood.app.datamocks.dto.core.UserDataDTOMock;
import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.usecases.getorderpaymentbyorderids.IGetOrderPaymentByOrderIdsUseCase;
import com.robinfood.app.usecases.getordersnotdirectpayments.IGetOrdersNotDirectPaymentsUseCase;
import com.robinfood.app.usecases.getoriginsbyids.IGetOriginsByIdsUseCase;
import com.robinfood.app.usecases.getuserdatabyorderids.IGetUserDataByOrderIdsUseCase;
import com.robinfood.core.dtos.OrderDailyDTO;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.OriginDTO;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrdersDailyUseCaseTest {

    @Mock
    private IGetOriginsByIdsUseCase getOriginsByIdsUseCase;

    @Mock
    private IGetOrdersNotDirectPaymentsUseCase getOrdersNotDirectPaymentsUseCase;

    @Mock
    private IGetUserDataByOrderIdsUseCase getUserDataByOrderIdsUseCase;

    @Mock
    private IOrdersRepository ordersRepository;

    @Mock
    private IGetOrderPaymentByOrderIdsUseCase getOrderPaymentByOrderIdsUseCase;


    @InjectMocks
    private GetOrdersDailyUseCase getOrdersDailyUseCase;

    List<OrderEntity> orders = new OrderEntityMock().getDataDefaultList();
    private final List<OriginDTO> originDTOS = List.of(
            OriginDTOMock.getDataDefault()
    );

    private final List<UserDataDTO> userDataDTOS = List.of(UserDataDTOMock.getDataDefault());

    private final OrderDailyDTO orderDailyDTO = OrderDailyDTO.builder().build();


    private final List<OrderPaymentDTO> orderPaymentEqualTotalDTOS = new ArrayList<>(
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

    private final List<OrderPaymentDTO> orderPaymentDifferentAndMinorDTOS = new ArrayList<>(
            Collections.singletonList(new OrderPaymentDTO(
                    null,
                    0.0,
                    1L,
                    1L,
                    1L,
                    7L,
                    1000.0,
                    0.0,
                    1900.0
            )
    ));

    final String timezone = "America/Bogota";

    @Test
    void test_invoke_Should_Get_Orders_Daily_When_InvokeTheUseCase() {

        when(ordersRepository.findAllByStoreIdAndPaidAndFullSynchronizedAndStatusIdAndCreatedAtBetweenOrderByCreatedAtDesc(
                        anyLong(),
                        anyBoolean(),
                        anyInt(),
                        anyLong(),
                        any(LocalDateTime.class),
                        any(LocalDateTime.class)
                )
        ).thenReturn(orders);

        when(getOrdersNotDirectPaymentsUseCase.invoke(anyList(), anyList()))
                .thenReturn(List.of(OrderDTOMock.build()));

        when(getOriginsByIdsUseCase.invoke(anyList()))
                .thenReturn(originDTOS);

        when(getUserDataByOrderIdsUseCase.invoke(anyList()))
                .thenReturn(userDataDTOS);

        when(getOrderPaymentByOrderIdsUseCase.invoke(anyList()))
                .thenReturn(orderPaymentEqualTotalDTOS);

        getOrdersDailyUseCase.invoke(1L, timezone);

        verify(ordersRepository)
                .findAllByStoreIdAndPaidAndFullSynchronizedAndStatusIdAndCreatedAtBetweenOrderByCreatedAtDesc(
                        anyLong(),
                        anyBoolean(),
                        anyInt(),
                        anyLong(),
                        any(LocalDateTime.class),
                        any(LocalDateTime.class)
                );
        verify(getOriginsByIdsUseCase).invoke(anyList());
        verify(getUserDataByOrderIdsUseCase).invoke(anyList());

    }

    @Test
    void test_invoke_Should_Orders_Daily_With_Payment_Different_And_Minor_When_InvokeTheUseCase() {

        when(ordersRepository.findAllByStoreIdAndPaidAndFullSynchronizedAndStatusIdAndCreatedAtBetweenOrderByCreatedAtDesc(
                        anyLong(),
                        anyBoolean(),
                        anyInt(),
                        anyLong(),
                        any(LocalDateTime.class),
                        any(LocalDateTime.class)
                )
        ).thenReturn(orders);

        when(getOrdersNotDirectPaymentsUseCase.invoke(anyList(), anyList()))
                .thenReturn(List.of(OrderDTOMock.build()));

        when(getOriginsByIdsUseCase.invoke(anyList()))
                .thenReturn(originDTOS);

        when(getUserDataByOrderIdsUseCase.invoke(anyList()))
                .thenReturn(userDataDTOS);

        when(getOrderPaymentByOrderIdsUseCase.invoke(anyList()))
                .thenReturn(orderPaymentDifferentAndMinorDTOS);

        getOrdersDailyUseCase.invoke(1L, timezone);

        verify(ordersRepository)
                .findAllByStoreIdAndPaidAndFullSynchronizedAndStatusIdAndCreatedAtBetweenOrderByCreatedAtDesc(
                        anyLong(),
                        anyBoolean(),
                        anyInt(),
                        anyLong(),
                        any(LocalDateTime.class),
                        any(LocalDateTime.class)
                );
        verify(getOriginsByIdsUseCase).invoke(anyList());
        verify(getUserDataByOrderIdsUseCase).invoke(anyList());

    }

}