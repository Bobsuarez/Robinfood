package com.robinfood.repository.services;

import com.robinfood.core.dtos.services.ServiceDetailDTO;
import com.robinfood.core.entities.APIServicesResponseEntity;
import com.robinfood.core.entities.services.ServiceDeliveryCourierEntity;
import com.robinfood.core.entities.services.ServiceDetailEntity;
import com.robinfood.core.entities.services.ServiceStatusTraceEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.ServiceBcAPI;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceRepositoryTest {

    @Mock
    private ServiceBcAPI mockServiceBcApi;

    @Mock
    private Call<APIServicesResponseEntity<ServiceDetailEntity>> mockApiResponseEntityCall;

    @InjectMocks
    private ServiceRepository serviceRepository;

    private final String token = "token";
    private final String orderUId = "12345abcde";

    @Test
    void test_Get_Services_Returns_Successfully() throws Exception {

        when(mockServiceBcApi.getServiceDetail(
            token,
            orderUId
        )).thenReturn(mockApiResponseEntityCall);

        when(mockApiResponseEntityCall.execute()).thenReturn(Response.success(getResultServiceDTO()));

        final Result<ServiceDetailDTO> result = serviceRepository.getServiceDetail(
            orderUId,
            token
        );

        assertTrue(result instanceof Result.Success);
        ServiceDetailDTO serviceDetailDTO = ((Result.Success<ServiceDetailDTO>) result).getData();

        assertNotNull(serviceDetailDTO);
        assertNotNull(serviceDetailDTO.getDeliveryCourier());
        assertThat(serviceDetailDTO.getDeliveryCourier().getName(), is(equalTo("TEST TEST")));
        assertThat(serviceDetailDTO.getDeliveryCourier().getPhone(), is(equalTo("+573100000000")));
        assertThat(serviceDetailDTO.getDeliveryCourier().getVehiclePlate(), is(equalTo("AAA000")));
    }

    @Test
    void test_Get_Services_Without_Data_Returns_Successfully() throws Exception {

        when(mockServiceBcApi.getServiceDetail(
            token,
            orderUId
        )).thenReturn(mockApiResponseEntityCall);

        when(mockApiResponseEntityCall.execute()).thenReturn(Response.success(getResultServiceWithoutDataDTO()));

        final Result<ServiceDetailDTO> result = serviceRepository.getServiceDetail(
            orderUId,
            token
        );

        assertTrue(result instanceof Result.Success);
        ServiceDetailDTO serviceDetailDTO = ((Result.Success<ServiceDetailDTO>) result).getData();

        assertNotNull(serviceDetailDTO);
        assertNull(serviceDetailDTO.getDeliveryCourier());
    }

    @Test
    void test_Get_Services_Returns_With_Error() throws IOException {
        when(mockServiceBcApi.getServiceDetail(
            token,
            orderUId
        )).thenReturn(mockApiResponseEntityCall);

        when(mockApiResponseEntityCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
            MediaType.parse("application/json"),
            ObjectExtensions.toJson(getResultServiceErrorDTO())
        )));

        final Result<ServiceDetailDTO> result = serviceRepository.getServiceDetail(
            orderUId,
            token
        );

        assertTrue(result instanceof Result.Error);
    }

    private APIServicesResponseEntity<ServiceDetailEntity> getResultServiceDTO() {
        return new APIServicesResponseEntity<>(
            200,
            "Services Found",
            null,
            new ServiceDetailEntity(
                new ServiceDeliveryCourierEntity(
                    "TEST TEST",
                    "+573100000000",
                    null,
                    "AAA000"
                ),
                1L,
                "12345abcde",
                1L,
                "123abc",
                new ServiceStatusTraceEntity(
                    1L,
                    "created"
                ),
                true
            )
        );
    }

    private APIServicesResponseEntity<ServiceDetailEntity> getResultServiceWithoutDataDTO() {
        return new APIServicesResponseEntity<>(
            200,
            "Services Not Found",
            null,
            null
        );
    }

    private APIServicesResponseEntity<ServiceDetailEntity> getResultServiceErrorDTO() {
        return new APIServicesResponseEntity<>(
            500,
            "Internal Server Error",
            List.of("Internal Server Error"),
            null
        );
    }

}
