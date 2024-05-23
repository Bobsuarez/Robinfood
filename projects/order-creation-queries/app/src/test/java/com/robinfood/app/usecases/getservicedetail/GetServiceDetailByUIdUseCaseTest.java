package com.robinfood.app.usecases.getservicedetail;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.services.ServiceDeliveryCourierDTO;
import com.robinfood.core.dtos.services.ServiceDetailDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.services.IServiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Test of GetUserOrderDetailByUIdUseCase
 */
@ExtendWith(MockitoExtension.class)
public class GetServiceDetailByUIdUseCaseTest {

    @Mock
    private IServiceRepository serviceRepository;
    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    @InjectMocks
    private GetServiceDetailByUIdUseCase useCase;

    @Test
    void test_ServiceDetail_Returns_Correctly() {
        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
            TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(serviceRepository.getServiceDetail(
            "12345abcde",
            token
        )).thenReturn(getResultServiceDTO());

        final Result<ServiceDetailDTO> result = useCase.invoke(
            "12345abcde"
        );

        assertInstanceOf(Result.Success.class, result);

        ServiceDetailDTO serviceDetailDTO = ((Result.Success<ServiceDetailDTO>) result).getData();

        assertNotNull(serviceDetailDTO);
        assertNotNull(serviceDetailDTO.getDeliveryCourier());
        assertThat(serviceDetailDTO.getId(), is(equalTo(1L)));
        assertThat(serviceDetailDTO.getDeliveryCourier().getName(), is(equalTo("TEST TEST")));
        assertThat(serviceDetailDTO.getDeliveryCourier().getPhone(), is(equalTo("+573100000000")));
        assertThat(serviceDetailDTO.getDeliveryCourier().getVehiclePlate(), is(equalTo("AAA000")));
    }

    @Test
    void test_ServiceDetail_Without_Info_Returns_Correctly() {
        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
            TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(serviceRepository.getServiceDetail(
            "12345abcde",
            token
        )).thenReturn(getResultServiceWithoutInfoDTO());

        final Result<ServiceDetailDTO> result = useCase.invoke(
            "12345abcde"
        );

        assertInstanceOf(Result.Success.class, result);

        ServiceDetailDTO serviceDetailDTO = ((Result.Success<ServiceDetailDTO>) result).getData();

        assertNotNull(serviceDetailDTO);
        assertNull(serviceDetailDTO.getDeliveryCourier());
    }

    @Test
    void test_OrderDetail_Returns_Failure() {
        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
            TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(serviceRepository.getServiceDetail(
            "12345abcde",
            token
        )).thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

        final Result<ServiceDetailDTO> result = useCase.invoke(
            "12345abcde"
        );

        assertTrue(result instanceof Result.Error);
        assertTrue(((Result.Error) result).getException() instanceof Exception);
    }

    private Result<ServiceDetailDTO> getResultServiceDTO() {
        return new Result.Success<>(
            ServiceDetailDTO.builder()
                .id(1L)
                .referenceUid("12345abcde")
                .deliveryCourier(
                    ServiceDeliveryCourierDTO.builder()
                        .name("TEST TEST")
                        .phone("+573100000000")
                        .vehiclePlate("AAA000")
                        .build()
                )
                .build()
        );
    }

    private Result<ServiceDetailDTO> getResultServiceWithoutInfoDTO() {
        return new Result.Success<>(
            new ServiceDetailDTO()
        );
    }
}
