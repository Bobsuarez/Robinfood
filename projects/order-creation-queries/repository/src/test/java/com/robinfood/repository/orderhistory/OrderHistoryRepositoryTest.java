package com.robinfood.repository.orderhistory;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.PaginationDTO;
import com.robinfood.core.dtos.orderhistory.request.OrderHistoryRequestDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.orderhistory.response.OrderHistoryItemEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcQueriesAPI;
import com.robinfood.repository.mocks.OrderHistoryItemEntityMock;
import com.robinfood.repository.mocks.OrderHistoryRequestDTOMock;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test of OrderHistoryRepository
 */
@ExtendWith(MockitoExtension.class)
class OrderHistoryRepositoryTest {

    @Mock
    private OrderBcQueriesAPI orderBcQueriesAPI;

    @Mock
    private Call<APIResponseEntity<EntityDTO<OrderHistoryItemEntity>>> apiResponseEntityCall;

    @InjectMocks
    private OrderHistoryRepository orderHistoryRepository;

    private final String token = "token";

    @Test
    void Test_getOrderFilter_Should_InternalServerError_When_WrongAnswerOfOrderBC() throws Exception {

        when(orderBcQueriesAPI.getOrderHistoryByStore(
                anyLong(),
                anyList(),
                anyInt(),
                anyBoolean(),
                any(LocalDate.class),
                any(LocalDate.class),
                anyInt(),
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(apiResponseEntityCall);

        when(apiResponseEntityCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        ObjectExtensions.toJson(new APIResponseEntity<>(
                                500,
                                List.of(),
                                "CO",
                                "",
                                "Order filter not be returned"
                        ))
                ))
        );

        orderHistoryRepository.getOrderHistory(OrderHistoryRequestDTO.builder()
                        .channelsId(List.of(1L))
                        .currentPage(1)
                        .isDelivery(Boolean.TRUE)
                        .localDateEnd(LocalDate.now())
                        .localDateStart(LocalDate.now())
                        .perPage(1)
                        .searchText("")
                        .storeId(1L)
                        .timeZone("America/Bogota")
                .build(), token);

        verify(orderBcQueriesAPI)
                .getOrderHistoryByStore(
                        anyLong(),
                        anyList(),
                        anyInt(),
                        anyBoolean(),
                        any(LocalDate.class),
                        any(LocalDate.class),
                        anyInt(),
                        anyString(),
                        anyString(),
                        anyString()
                );

    }

    @Test
    void Test_getOrderFilter_Should_RespondToOrderHistoryList_When_InvokeTheRepository() throws Exception {

        when(orderBcQueriesAPI.getOrderHistoryByStore(
                anyLong(),
                anyList(),
                anyInt(),
                anyBoolean(),
                any(LocalDate.class),
                any(LocalDate.class),
                anyInt(),
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(apiResponseEntityCall);

        when(apiResponseEntityCall.execute()).thenReturn(Response.success(
                new APIResponseEntity<>(
                        200,
                        OrderHistoryItemEntityMock.getDataDefault(),
                        "CO",
                        "Order filter returned",
                        "200"
                )
        ));

        orderHistoryRepository.getOrderHistory(OrderHistoryRequestDTOMock.getDataDefault(), token);

        verify(orderBcQueriesAPI)
                .getOrderHistoryByStore(
                        anyLong(),
                        anyList(),
                        anyInt(),
                        anyBoolean(),
                        any(LocalDate.class),
                        any(LocalDate.class),
                        anyInt(),
                        anyString(),
                        anyString(),
                        anyString()
                );
    }

}