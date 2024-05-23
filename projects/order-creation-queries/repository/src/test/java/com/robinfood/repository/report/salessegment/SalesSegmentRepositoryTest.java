package com.robinfood.repository.report.salessegment;

import com.robinfood.core.dtos.report.salebysegment.GetSaleBySegmentDTO;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalesSegmentRepositoryTest {


    @Mock
    private OrderBcQueriesAPI orderBcQueriesAPI;

    @Mock
    private Call<APIResponseEntity<GetSaleBySegmentDTO>> mockOrderTotalDailySales;

    @InjectMocks
    private SalesSegmentRepository salesSegmentRepository;

    private final String token = "token";

    @Test
    void test_GetSaleBySegmentDTO_Should_OK_When_DataIsCorrect() throws Exception {

        when(orderBcQueriesAPI.getSalesSegment(
                anyList(),
                anyList(),
                anyList(),
                any(LocalDateTime.class),
                anyList(),
                anyList(),
                anyList(),
                anyString()
        )).thenReturn(mockOrderTotalDailySales);

        when(mockOrderTotalDailySales.execute()).thenReturn(Response.success(
                new APIResponseEntity<>(
                        200,
                        GetSaleBySegmentDTO.builder().build(),
                        "CO",
                        "Order filter returned",
                        "200"
                )
        ));

        salesSegmentRepository.getSalesSegment(
                List.of(1L, 2L), List.of(1L, 2L), List.of(1L, 2L), LocalDateTime.now(),
                List.of(1L, 2L), List.of(1L, 2L), List.of("America/Bogota", "America/Sao_Paulo"), token
        );

        verify(orderBcQueriesAPI)
                .getSalesSegment(
                        anyList(),
                        anyList(),
                        anyList(),
                        any(LocalDateTime.class),
                        anyList(),
                        anyList(),
                        anyList(),
                        anyString()
                );

    }

    @Test
    void test_GetSaleBySegmentDTO_Should_BadRequest_When_ApiContainsError() throws Exception {


        when(orderBcQueriesAPI.getSalesSegment(
                anyList(),
                anyList(),
                anyList(),
                any(LocalDateTime.class),
                anyList(),
                anyList(),
                anyList(),
                anyString()
        )).thenReturn(mockOrderTotalDailySales);


        when(mockOrderTotalDailySales.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                ObjectExtensions.toJson(new APIResponseEntity<>(
                        500,
                        List.of(),
                        "CO",
                        "",
                        "The start date cannot be greater than the end date"
                ))
        )));

        salesSegmentRepository.getSalesSegment(
                List.of(1L, 2L), List.of(1L, 2L), List.of(1L, 2L), LocalDateTime.now(),
                List.of(1L, 2L), List.of(1L, 2L), List.of("America/Bogota", "America/Sao_Paulo"), token
        );

        verify(orderBcQueriesAPI)
                .getSalesSegment(
                        anyList(),
                        anyList(),
                        anyList(),
                        any(LocalDateTime.class),
                        anyList(),
                        anyList(),
                        anyList(),
                        anyString()
                );
    }
}
