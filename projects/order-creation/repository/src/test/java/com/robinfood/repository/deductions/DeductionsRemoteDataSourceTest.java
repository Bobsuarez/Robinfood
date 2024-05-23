package com.robinfood.repository.deductions;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.DeductionsApi;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class DeductionsRemoteDataSourceTest {
    @InjectMocks
    private DeductionsRemoteDataSource deductionsRemoteDataSource;

    @Mock
    private  DeductionsApi deductionsApi;

    @Mock
    private Call<ApiResponseEntity<Map<Long,String>>> mockResponse;

    @Mock
    private Call<ApiResponseEntity<Object>> mockCouponSystemRevert;

    final Map<Long,String> data = new HashMap<>();

    private final String token = "token";

    final ApiResponseEntity<Object> apiErrorResponseEntityDeductions = ApiResponseEntity.builder()
            .code(400)
            .error("Error")
            .locale("CO")
            .message("Error obtained all the active type deductions")
            .build();

    final ApiResponseEntity<Object> apiErrorResponseEntityMenu = ApiResponseEntity.builder()
            .code(400)
            .error("Error")
            .locale("CO")
            .message("Error obtained all the active type deductions")
            .build();

    final ApiResponseEntity<Map<Long,String>> apiResponseEntityGetALlSuccess = ApiResponseEntity.<Map<Long, String>>builder()
            .code(200)
            .data(data)
            .locale("CO")
            .message("We deduct $1 from your order")
            .build();

    @Test
    void test_GetALlActiveDeductions_Returns_Correctly () throws IOException {

        when(deductionsApi.getAllActiveTypeDeductions(token)).thenReturn(mockResponse);

        when(mockResponse.execute()).thenReturn(Response.success(apiResponseEntityGetALlSuccess));

        final Map<Long,String> result = deductionsRemoteDataSource.getAllActiveTypeDeductions(token);

        assertEquals(apiResponseEntityGetALlSuccess.getData(), result);

    }

    @Test
    void test_GetAllActiveDeductions_Returns_Error() throws Exception {
        final String responseJSON = ObjectExtensions.toJson(apiErrorResponseEntityDeductions);

        when(deductionsApi.getAllActiveTypeDeductions(token)).thenReturn(mockResponse);

        when(mockResponse.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));
        try {
            deductionsRemoteDataSource.getAllActiveTypeDeductions(token);

        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseEntityMenu.getMessage()));
        }
    }
}
