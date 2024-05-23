package com.robinfood.repository.co2;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.CO2CalculateRequestEntity;
import com.robinfood.core.entities.CO2CalculateResponseEntity;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.CO2BCApi;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CO2RemoteDataSourceTest {

    @Mock
    private CO2BCApi co2BCApi;

    @Mock
    private Call<ApiResponseEntity<CO2CalculateResponseEntity>> mockCO2Calculate;

    @InjectMocks
    private CO2RemoteDataSource co2RemoteDataSource;

    private final String token = "token";

    @Test
    void test_CO2Calculate_Success() throws Exception {

        CO2CalculateRequestEntity request = CO2CalculateRequestEntity.builder().build();

        when(co2BCApi.co2Calculate(token, request)).thenReturn(mockCO2Calculate);

        ApiResponseEntity<CO2CalculateResponseEntity> response = ApiResponseEntity.<CO2CalculateResponseEntity>builder()
                .code(200)
                .data(CO2CalculateResponseEntity.builder().build())
                .locale("CO")
                .message("Has applied consumption today")
                .build();

        when(mockCO2Calculate.execute()).thenReturn(Response.success(response));

        assertAll(() -> co2RemoteDataSource.calculateCO2Compensation(token, request));
    }

    @Test
    void test_CO2Calculate_Error() throws Exception {
        CO2CalculateRequestEntity request = CO2CalculateRequestEntity.builder().build();

        when(co2BCApi.co2Calculate(token, request)).thenReturn(mockCO2Calculate);

        ApiResponseEntity<Object> response = ApiResponseEntity.builder()
                .code(400)
                .locale("CO")
                .message("Has applied consumption today")
                .build();

        final String responseJSON = ObjectExtensions.toJson(response);

        when(mockCO2Calculate.execute()).thenReturn(Response.error(400, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        assertThrows(
                TransactionCreationException.class,
                () -> co2RemoteDataSource.calculateCO2Compensation(token, request)
        );
    }
}
