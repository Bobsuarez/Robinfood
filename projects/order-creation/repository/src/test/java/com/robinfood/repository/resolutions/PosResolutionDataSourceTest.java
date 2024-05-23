package com.robinfood.repository.resolutions;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.InformationPosResolutionsResponseEntity;
import com.robinfood.core.util.ObjectMapperSingleton;
import com.robinfood.network.api.OrderBcQueryAPI;
import com.robinfood.repository.mocks.dtos.InformationPosResolutionDTOMocks;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PosResolutionDataSourceTest {

    @Mock
    private OrderBcQueryAPI orderBcQueryAPI;

    @Mock
    private Call<ApiResponseEntity<InformationPosResolutionsResponseEntity>> mockApiResponseEntity;

    @InjectMocks
    private PosResolutionDataSource posResolutionDataSource;

    private final ApiResponseEntity<InformationPosResolutionsResponseEntity> getPosResolutionApiResponseEntity =
            ApiResponseEntity.<InformationPosResolutionsResponseEntity>builder()
                    .code(200)
                    .data(InformationPosResolutionDTOMocks.getDataDefault())
                    .locale("CO")
                    .message("OK")
                    .build();

    private final ApiResponseEntity<InformationPosResolutionsResponseEntity> getPosResolutionApiErrorResponseEntity =
            ApiResponseEntity.<InformationPosResolutionsResponseEntity>builder()
                    .code(500)
                    .error("Error")
                    .locale("CO")
                    .message("Not Found")
                    .build();


    @Test
    void test_When_ConsultOrderBcQuery_Should_GetPosResolution() throws IOException {

        String token = "token";

        Short posId = 164;

        when(orderBcQueryAPI.getResolutionByPos(
                anyString(), anyString(), anyLong(), any(LocalDate.class), any(LocalDate.class)
        )).thenReturn(mockApiResponseEntity);

        when(mockApiResponseEntity.execute())
                .thenReturn(Response.success(getPosResolutionApiResponseEntity));

        CompletableFuture<InformationPosResolutionsResponseEntity> informationPosResolution =
                posResolutionDataSource.getInformationPosResolution(
                        token,
                        Long.valueOf(posId)
                );

        Assertions.assertEquals(informationPosResolution.join()
                                        .getPosId(), posId);
    }


    @Test
    void test_When_ConsultOrderBcQuery_Should_ExceptionGetPosResolution() throws IOException {

        String token = "token";

        short posId = 164;

        when(orderBcQueryAPI.getResolutionByPos(
                anyString(), anyString(), anyLong(), any(LocalDate.class), any(LocalDate.class)
        )).thenReturn(mockApiResponseEntity);

        when(mockApiResponseEntity.execute())
                .thenReturn(Response.error(500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        ObjectMapperSingleton.objectToJson(getPosResolutionApiErrorResponseEntity)
                )));

        Assertions.assertThrows(Exception.class, () ->
                posResolutionDataSource.getInformationPosResolution(
                        token,
                        (long) posId
                ));
    }
}