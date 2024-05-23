package com.robinfood.app.strategies.orderhistoryfilter.strategies;

import com.robinfood.app.datamocks.dto.core.UserDataDTOMock;
import com.robinfood.app.usecases.getusersbyfullnamelike.IGetUsersByFullNameLikeAndLocalDateUseCase;
import com.robinfood.core.dtos.request.orderhistory.OrderHistoryRequestDTO;
import com.robinfood.repository.orders.IOrdersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_CURRENT_PAGE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_PER_PAGE;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_START;
import static com.robinfood.core.constants.GlobalConstants.ORDER_PAID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderHistoryFilterSearchTextNonNullStrategyTest {

    @InjectMocks
    private OrderHistoryFilterSearchTextNonNullStrategy orderHistoryFilterSearchTextNonNullStrategy;

    @Mock
    private IOrdersRepository mockOrdersRepository;

    @Mock
    private IGetUsersByFullNameLikeAndLocalDateUseCase getUsersByFullNameLikeAndLocalDate;

    @Test
    void test_execute_should_Orders_when_ExecuteStrategy() {

        final LocalDateTime localDateTimeEnd = LocalDateTime.now();
        final LocalDateTime LocalDateTimeStart = LocalDateTime.now();
        final Long storeId = 1L;

        final Map<String, LocalDateTime> localDateTimeMap = Map.of(
                LOCAL_DATE_TIME_START,
                LocalDateTimeStart,
                LOCAL_DATE_TIME_END,
                localDateTimeEnd
        );

        when(getUsersByFullNameLikeAndLocalDate.invoke(
                "searchText",
                "searchText",
                localDateTimeMap,
                storeId
        ))
                .thenReturn(List.of(
                        UserDataDTOMock.getDataDefault()
                ));

        when(mockOrdersRepository.findAllByFilterLikeBrandNameAndOrderInvoiceNumberCreatedAtBetweenOrderByCreatedAtDesc(
                List.of(1L),
                LocalDateTimeStart,
                localDateTimeEnd,
                List.of(1L),
                ORDER_PAID,
                PageRequest.of(
                        Integer.parseInt(DEFAULT_CURRENT_PAGE) - DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT,
                        Integer.parseInt(DEFAULT_PER_PAGE)
                ),
                "searchText",
                1L
        )).thenReturn(
                Page.empty()
        );

        orderHistoryFilterSearchTextNonNullStrategy.execute(
                List.of(1L),
                Map.of(LOCAL_DATE_TIME_START, LocalDateTimeStart, LOCAL_DATE_TIME_END, localDateTimeEnd),
                OrderHistoryRequestDTO.builder()
                        .channelsId(List.of(1L))
                        .storeId(1L)
                        .currentPage(Integer.parseInt(DEFAULT_CURRENT_PAGE))
                        .perPage(Integer.parseInt(DEFAULT_PER_PAGE))
                        .searchText("searchText")
                        .build()
        );

        verify(mockOrdersRepository)
                .findAllByFilterLikeBrandNameAndOrderInvoiceNumberCreatedAtBetweenOrderByCreatedAtDesc(
                        anyList(),
                        any(LocalDateTime.class),
                        any(LocalDateTime.class),
                        anyList(),
                        anyBoolean(),
                        any(PageRequest.class),
                        anyString(),
                        anyLong()
                );

    }

}
