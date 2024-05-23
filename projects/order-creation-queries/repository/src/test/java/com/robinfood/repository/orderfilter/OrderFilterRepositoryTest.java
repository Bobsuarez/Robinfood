package com.robinfood.repository.orderfilter;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcAPI;
import com.robinfood.repository.mocks.ResponseOrderFilterEntityMock;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderFilterRepositoryTest {

    @Mock
    private OrderBcAPI orderBcAPI;

    @Mock
    private Call<APIResponseEntity<EntityDTO<OrderEntity>>> mockOrderFilterCall;

    @InjectMocks
    private OrderFilterRepository orderFilterRepository;

    private final String token = "token";

    @Test
    void Test_getOrderFilter_Should_InternalServerError_When_WrongAnswerOfOrderBC() throws Exception {

        when(orderBcAPI.getOrderFilter(
                anyInt(),
                anyString(),
                any(LocalDate.class),
                any(LocalDate.class),
                anyInt(),
                anyLong(),
                anyString(),
                anyString()
        )).thenReturn(mockOrderFilterCall);

        when(mockOrderFilterCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
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

        final Result<EntityDTO<OrderDTO>> result = orderFilterRepository.getOrderFilter(
                1,
                "",
                LocalDate.now(),
                LocalDate.now(),
                1,
                1L,
                "America/Bogota",
                token
        );

        assertTrue(result instanceof Result.Error);
    }

    @Test
    void Test_getOrderFilter_Should_RespondToOrdersAccordingToFilters_When_LetsInvokeTheRepository() throws Exception {

        when(orderBcAPI.getOrderFilter(
                anyInt(),
                anyString(),
                any(LocalDate.class),
                any(LocalDate.class),
                anyInt(),
                anyLong(),
                anyString(),
                anyString()
        )).thenReturn(mockOrderFilterCall);

        when(mockOrderFilterCall.execute()).thenReturn(Response.success(
                ResponseOrderFilterEntityMock.getAPIResponseEntity()
        ));

        orderFilterRepository.getOrderFilter(
                1,
                "",
                LocalDate.now(),
                LocalDate.now(),
                1,
                1L,
                "America/Bogota",
                token
        );

        verify(orderBcAPI).getOrderFilter(
                anyInt(),
                anyString(),
                any(LocalDate.class),
                any(LocalDate.class),
                anyInt(),
                anyLong(),
                anyString(),
                anyString()
        );
    }
}