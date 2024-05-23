package com.robinfood.repository.services;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.servicesentities.*;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.ServiceBCApi;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServicesDataSourceTest {

    private final ServiceRequestEntity serviceRequestEntity = ServiceRequestEntity.builder()
            .grossValue(BigDecimal.valueOf(21))
            .discount(BigDecimal.valueOf(2))
            .netValue(BigDecimal.valueOf(19))
            .storeId(123L)
            .build();

    private final ServiceListRequestEntity serviceListRequestEntity = ServiceListRequestEntity.builder()
            .stores(List.of(serviceRequestEntity))
            .build();

    private final ApiResponseEntity<ServiceValidationResponseEntity> apiErrorResponseEntity = ApiResponseEntity.<ServiceValidationResponseEntity>builder()
            .code(500)
            .error("Error")
            .locale("CO")
            .message("Error validate service")
            .build();

    private final String token = "token";

    private final ServiceResponseErrorDetailsEntity serviceResponseErrorDetailsEntity = ServiceResponseErrorDetailsEntity.builder()
            .key(123L)
            .field("Campo")
            .error("Mensaje de error 1")
            .build();

    private final ServiceResponseError serviceResponseError = ServiceResponseError.builder()
            .type("ERROR_VALIDATING_SHIPPING_COST")
            .code("1000")
            .details(List.of(serviceResponseErrorDetailsEntity))
            .build();

    private final ServiceValidationResponseEntity serviceValidationResponseEntity = ServiceValidationResponseEntity.builder()
            .code("400")
            .message("error validating the data")
            .error(serviceResponseError)
            .build();

    private final ApiResponseEntity<ServiceValidationResponseEntity> apiOkResponseEntity = ApiResponseEntity.<ServiceValidationResponseEntity>builder()
            .code(200)
            .data(serviceValidationResponseEntity)
            .locale("CO")
            .message("Success")
            .build();

    @Mock
    private ServiceBCApi serviceBCApi;

    @Mock
    private Call<ApiResponseEntity<ServiceValidationResponseEntity>> mockServiceValidation;

    @InjectMocks
    private ServicesDataSource servicesDataSource;

    @Test
    void given_Service_List_Entity_When_Consumes_Service_Api_Then_Response_Is_Correct() throws Exception {

        when(serviceBCApi.validateServices(
                any(), any(ServiceListRequestEntity.class)
        )).thenReturn(mockServiceValidation);

        when(mockServiceValidation.execute()).thenReturn(Response.success(apiOkResponseEntity));

        final boolean  result =
                servicesDataSource.validateService(token, serviceListRequestEntity ).join();

        assertTrue( result);
    }

    @Test
    void given_Service_List_Entity_When_Consumes_Service_Api_Then_Exception() throws Exception {

        final String responseJSON = ObjectExtensions.toJson(apiErrorResponseEntity);

        when(serviceBCApi.validateServices(
                any(), any(ServiceListRequestEntity.class)
        )).thenReturn(mockServiceValidation);

        when(mockServiceValidation.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"), responseJSON)));

        try {
            servicesDataSource.validateService(token,  serviceListRequestEntity ).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseEntity.getMessage()));
        }
    }
}
