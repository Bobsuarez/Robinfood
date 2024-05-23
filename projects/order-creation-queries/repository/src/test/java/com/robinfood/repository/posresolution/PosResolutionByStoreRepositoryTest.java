package com.robinfood.repository.posresolution;

import com.robinfood.core.dtos.posresolution.DataPosResolutionRequestDTO;
import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcQueriesAPI;
import com.robinfood.repository.mocks.PosResolutionDTOMock;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PosResolutionByStoreRepositoryTest {

    @Mock
    private OrderBcQueriesAPI orderBcQueriesAPI;

    @Mock
    private Call<APIResponseEntity<List<GetPosResolutionsDTO>>> mockOrderTotalDailySales;

    @InjectMocks
    private PosResolutionRepository posResolutionRepository;

    private final String token = "token";

    @Test
    void test_GetPosReturnResolutionByStore_Should_OK_When_DataIsCorrect() throws Exception {

        when(orderBcQueriesAPI.getPosResolutionsSequenceByStore(
                anyLong(),
                any(LocalDate.class),
                any(LocalDate.class),
                anyString(),
                anyString()
        )).thenReturn(mockOrderTotalDailySales);

        when(mockOrderTotalDailySales.execute()).thenReturn(Response.success(
                new APIResponseEntity<>(
                        200,
                        List.of(PosResolutionDTOMock.getDataDefault()),
                        "CO",
                        "Order filter returned",
                        "200"
                )
        ));

        posResolutionRepository.getDataPosResolutionByStore(DataPosResolutionRequestDTO.builder()
                .storeId(1L)
                .localDateStart(LocalDate.now())
                .localDateEnd(LocalDate.now())
                .timeZone("America/Bogotá")
                .build(), token);

        verify(orderBcQueriesAPI)
                .getPosResolutionsSequenceByStore(
                        anyLong(),
                        any(LocalDate.class),
                        any(LocalDate.class),
                        anyString(),
                        anyString()
                );

    }

    @Test
    void test_GetPosByStore_Should_BadRequest_When_ApiContainsError() throws Exception {

        when(orderBcQueriesAPI.getPosResolutionsSequenceByStore(
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
                        List.of(),
                        "CO",
                        "",
                        "The start date cannot be greater than the end date"
                ))
        )));

        posResolutionRepository.getDataPosResolutionByStore(DataPosResolutionRequestDTO.builder()
                .storeId(1L)
                .localDateStart(LocalDate.now())
                .localDateEnd(LocalDate.now())
                .timeZone("America/Bogotá")
                .build(), token);

        verify(orderBcQueriesAPI)
                .getPosResolutionsSequenceByStore(
                        anyLong(),
                        any(LocalDate.class),
                        any(LocalDate.class),
                        anyString(),
                        anyString()
                );
    }
}
