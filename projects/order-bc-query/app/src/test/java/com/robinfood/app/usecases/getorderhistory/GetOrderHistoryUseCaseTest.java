package com.robinfood.app.usecases.getorderhistory;

import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.strategies.orderhistoryfilter.chain.IOrderHistoryFilterChain;
import com.robinfood.app.strategies.orderhistoryfilter.chain.OrderHistoryFilterBasicChain;
import com.robinfood.app.strategies.orderhistoryfilter.strategies.OrderHistoryFilterBasicStrategy;
import com.robinfood.app.usecases.getdeliverytypes.IGetDeliveryTypesUseCase;
import com.robinfood.app.usecases.getstatusbyorderids.IGetStatusByOrderIds;
import com.robinfood.app.usecases.getuserdatabyorderids.IGetUserDataByOrderIdsUseCase;
import com.robinfood.core.dtos.DeliveryTypeDTO;
import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.dtos.request.orderhistory.OrderHistoryRequestDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orderfoodcoins.IOrderFoodCoinRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_CURRENT_PAGE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_PER_PAGE;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_START;
import static com.robinfood.core.constants.GlobalConstants.ORDER_PAID;
import static com.robinfood.core.constants.GlobalConstants.TIMEZONE_BY_DEVICE_DEFAULT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrderHistoryUseCaseTest {

    @InjectMocks
    private GetOrderHistoryUseCase getOrderHistoryUseCase;

    @Mock
    private IGetDeliveryTypesUseCase getDeliveryTypesUseCase;

    @Mock
    private IGetStatusByOrderIds getStatusByOrderIds;

    @Mock
    private IGetUserDataByOrderIdsUseCase getUserDataByOrderIdsUseCase;

    @Mock
    private OrderHistoryFilterBasicChain orderHistoryFilterBasicChain;

    @Mock
    private OrderHistoryFilterBasicStrategy mockOrderHistoryFilterStrategy;

    @Mock
    private IOrdersRepository mockOrdersRepository;

    private final OrderEntityMock orderEntityMock = new OrderEntityMock();

    @Test
    void test_invoke_Should_OrderHistoryList_When_InvokeTheUseCase() {

        OrderEntity orderEntity = orderEntityMock.getDataDefault();
        Pageable pageable = PageRequest.of(0, 10);

        final Map<String, LocalDateTime> localDateTimeMap = Map.of(
                LOCAL_DATE_TIME_START,
                LocalDateTime.now(),
                LOCAL_DATE_TIME_END,
                LocalDateTime.now()
        );

        when(getDeliveryTypesUseCase.invoke())
                .thenReturn(List.of(DeliveryTypeDTO.builder()
                                .id(1L)
                        .build())
                );

        when(orderHistoryFilterBasicChain.handler(any(OrderHistoryRequestDTO.class)))
                .thenReturn(new OrderHistoryFilterBasicStrategy(mockOrdersRepository));

        when(getUserDataByOrderIdsUseCase.invoke(anyList()))
                .thenReturn(List.of(UserDataDTO.builder().build()));

        when(getStatusByOrderIds.invoke(anyList()))
                .thenReturn(List.of(OrderStatusDTO.builder().build()));

        when(mockOrdersRepository.findAllByStoreIdAndDeliveryTypeIdInAndPaidAndCreatedAtBetweenOrderByCreatedAtDesc(
                anyLong(),
                anyList(),
                anyBoolean(),
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                any(PageRequest.class)
        )).thenReturn(
                Page.empty()
        );

        lenient().when(mockOrderHistoryFilterStrategy.execute(anyList(), anyMap(), any(OrderHistoryRequestDTO.class)))
                .thenReturn(new PageImpl<>(List.of(orderEntity), pageable, 1));

        getOrderHistoryUseCase.invoke(OrderHistoryRequestDTO.builder()
                .currentPage(Integer.parseInt(DEFAULT_CURRENT_PAGE))
                .isDelivery(Boolean.FALSE)
                .localDateEnd(LocalDate.now())
                .localDateStart(LocalDate.now())
                .perPage(Integer.parseInt(DEFAULT_PER_PAGE))
                .storeId(1L)
                .timeZone(TIMEZONE_BY_DEVICE_DEFAULT)
                .build());

        verify(getStatusByOrderIds)
                .invoke(anyList());

    }

}