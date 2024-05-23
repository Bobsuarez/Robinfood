package com.robinfood.repository.orderspaid;

import com.robinfood.core.dtos.orderspaid.GetOrdersPaidDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcQueriesAPI;
import com.robinfood.repository.mocks.DataOrderStoreRequestDTOMock;
import com.robinfood.repository.mocks.DataOrdersPaidRequestDTOMock;
import com.robinfood.repository.mocks.GetOrderPaidDTOMock;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OrdersPaidRepositoryTest {

    @Mock
    private OrderBcQueriesAPI orderBcQueriesAPI;

    @Mock
    private Call<APIResponseEntity<GetOrdersPaidDTO>> responseEntityCall;

    @InjectMocks
    private OrdersPaidRepository ordersPaidRepository;

    private final String token = "token";

    @Test
    void test_GetOrderPaid_Should_OK_When_DataIsCorrect() throws Exception {

        when(orderBcQueriesAPI.getPaidOrders(
                anyList(),
                anyLong(),
                anyInt(),
                anyLong(),
                any(LocalDate.class),
                any(LocalDate.class),
                anyList(),
                anyList(),
                anyInt(),
                anyString(),
                anyList(),
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(responseEntityCall);

        when(responseEntityCall.execute()).thenReturn(Response.success(
                new APIResponseEntity<>(
                        200,
                        GetOrderPaidDTOMock.getDataDefault(),
                        "CO",
                        "Order filter returned",
                        "200"
                )
        ));

        ordersPaidRepository.getDataOrdersPaid(
                DataOrdersPaidRequestDTOMock.getDataDefault(),
                token
        );

        verify(orderBcQueriesAPI)
                .getPaidOrders(
                        anyList(),
                        anyLong(),
                        anyInt(),
                        anyLong(),
                        any(LocalDate.class),
                        any(LocalDate.class),
                        anyList(),
                        anyList(),
                        anyInt(),
                        anyString(),
                        anyList(),
                        anyString(),
                        anyString(),
                        anyString()
                );
    }

    @Test
    void test_GetOrderPaid_Should_InternalServerError_When_WrongAnswerOfOrderBC() throws Exception {

        when(orderBcQueriesAPI.getPaidOrders(
                anyList(),
                anyLong(),
                anyInt(),
                anyLong(),
                any(LocalDate.class),
                any(LocalDate.class),
                anyList(),
                anyList(),
                anyInt(),
                anyString(),
                anyList(),
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(responseEntityCall);

        when(responseEntityCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
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

        ordersPaidRepository.getDataOrdersPaid(
                DataOrdersPaidRequestDTOMock.getDataDefault(),
                token
        );

        verify(orderBcQueriesAPI)
                .getPaidOrders(
                        anyList(),
                        anyLong(),
                        anyInt(),
                        anyLong(),
                        any(LocalDate.class),
                        any(LocalDate.class),
                        anyList(),
                        anyList(),
                        anyInt(),
                        anyString(),
                        anyList(),
                        anyString(),
                        anyString(),
                        anyString()
                );
    }
}
