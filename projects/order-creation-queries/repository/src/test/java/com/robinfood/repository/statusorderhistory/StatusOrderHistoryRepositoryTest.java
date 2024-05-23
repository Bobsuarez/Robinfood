package com.robinfood.repository.statusorderhistory;

import com.robinfood.core.dtos.statusorderhistory.StatusOrderHistoryDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcQueriesAPI;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatusOrderHistoryRepositoryTest {

    @Mock
    private OrderBcQueriesAPI orderBcQueriesAPI;

    @Mock
    private Call<APIResponseEntity<List<StatusOrderHistoryDTO>>> mockStatusOrderHistory;

    @InjectMocks
    private StatusOrderHistoryRepository statusOrderHistoryRepository;

    @Test
    void test_GetStatusOrderHistory_Should_InternalServerError_When_WrongAnswerOfOrderBC() throws Exception {

        when(orderBcQueriesAPI.getStatusOrderHistory(
                anyString(),
                anyString()
        )).thenReturn(mockStatusOrderHistory);

        when(mockStatusOrderHistory.execute())
                .thenReturn(Response.error(500,
                        ResponseBody.create(
                                MediaType.parse("application/json"),
                                ObjectExtensions.toJson(new APIResponseEntity<>(
                                        500,
                                        List.of(),
                                        "CO",
                                        "",
                                        "Order filter not be returned"
                                ))
                        )
                ));

        statusOrderHistoryRepository.getStatusOrderHistory("token", "uuid");

        verify(orderBcQueriesAPI)
                .getStatusOrderHistory(anyString(), anyString());
    }

    @Test
    void test_GetStatusOrderHistory_Should_RespondToStatusOrderHistoryList_When_InvokeRepository() throws Exception {

        when(orderBcQueriesAPI.getStatusOrderHistory(
                anyString(),
                anyString()
        )).thenReturn(mockStatusOrderHistory);

        when(mockStatusOrderHistory.execute())
                .thenReturn(Response.success(
                        new APIResponseEntity<>(
                                200,
                                List.of(StatusOrderHistoryDTO.builder().build()),
                                "CO",
                                "Order filter returned",
                                "200"
                        )
                ));
        statusOrderHistoryRepository.getStatusOrderHistory("token", "uuid");

        verify(orderBcQueriesAPI)
                .getStatusOrderHistory(anyString(), anyString());
    }
}