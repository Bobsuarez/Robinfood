package com.robinfood.repository.ordercategory;

import com.robinfood.core.dtos.ordercategories.DataRequestOrderCategoryDTO;
import com.robinfood.core.dtos.ordercategories.OrderCategoryDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcQueriesAPI;
import com.robinfood.repository.mocks.OrderCategoriesDTOMock;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderCategoryRepositoryTest {

    @Mock
    private OrderBcQueriesAPI orderBcQueriesAPI;

    @Mock
    private Call<APIResponseEntity<List<OrderCategoryDTO>>> mockOrderTotalDailySales;

    @InjectMocks
    private OrderCategoryRepository orderCategoryRepository;
    
    private final String token = "token";

    @Test
    void test_GetOrderCategory_Should_OK_When_DataIsCorrect() throws Exception {

        when(orderBcQueriesAPI.getOrdersGroupedByCategories(
                anyLong(),
                any(LocalDate.class),
                any(LocalDate.class),
                anyString(),
                anyString()
        )).thenReturn(mockOrderTotalDailySales);
        when(mockOrderTotalDailySales.execute()).thenReturn(Response.success(
                new APIResponseEntity<>(
                        200,
                        OrderCategoriesDTOMock.getDataOrderCategoryList(),
                        "CO",
                        "Order filter returned",
                        "200"
                )
        ));

        orderCategoryRepository.getOrderListCategories(DataRequestOrderCategoryDTO.builder()
                .posId(1L)
                .localDateStart(LocalDate.now())
                .localDateEnd(LocalDate.now())
                .timeZone("America/Bogotá")
                .build(), token);

        verify(orderBcQueriesAPI)
                .getOrdersGroupedByCategories(
                        anyLong(),
                        any(LocalDate.class),
                        any(LocalDate.class),
                        anyString(),
                        anyString()
                );
    }

    @Test
    void test_GetOrderCategory_Should_BadRequest_When_ApiContainsError() throws Exception {
        when(orderBcQueriesAPI.getOrdersGroupedByCategories(
                anyLong(),
                any(LocalDate.class),
                any(LocalDate.class),
                anyString(),
                anyString()
        )).thenReturn(mockOrderTotalDailySales);
        when(mockOrderTotalDailySales.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                ObjectExtensions.toJson(new APIResponseEntity<>(
                        500,
                        OrderCategoriesDTOMock.getDataOrderCategoryList(),
                        "CO",
                        "",
                        "The start date cannot be greater than the end date"
                ))
        )));
        orderCategoryRepository.getOrderListCategories(DataRequestOrderCategoryDTO.builder()
                .posId(1L)
                .localDateStart(LocalDate.now())
                .localDateEnd(LocalDate.now())
                .timeZone("America/Bogotá")
                .build(), token);

        verify(orderBcQueriesAPI)
                .getOrdersGroupedByCategories(
                        anyLong(),
                        any(LocalDate.class),
                        any(LocalDate.class),
                        anyString(),
                        anyString()
                );
    }

}
