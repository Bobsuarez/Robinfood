package com.robinfood.ordereports.repositories.services;

import com.robinfood.app.library.comunication.ClientRetroFit;
import com.robinfood.app.library.dto.Result;
import com.robinfood.app.library.exception.ApplicationException;
import com.robinfood.app.library.exception.app.NetworkConnectionException;
import com.robinfood.ordereports.dtos.orders.DeliveryCourierServiceDTO;
import com.robinfood.ordereports.entities.ApiResponseEntity;
import com.robinfood.ordereports.mocks.DeliveryCourierServiceDTOMock;
import com.robinfood.ordereports.network.api.ServicesBcAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ServicesRepositoryTest {

    @Mock
    private ServicesBcAPI servicesBcAPI;

    @InjectMocks
    private ServicesRepository servicesRepository;

    private final String token = "token";

    private final String transactionUuid = "abc-123";

    private final Result result = new Result.Success(
            new ApiResponseEntity<>(
                    200,
                    DeliveryCourierServiceDTOMock.getDataDefault(),
                    null,
                    "CO",
                    "OK",
                    "200"
            ));

    @Test
    void test_Services_Should_OK_When_DataIsCorrect() throws Exception {
        try (MockedStatic<ClientRetroFit> mockedStatic = mockStatic(ClientRetroFit.class)) {
            mockedStatic.when(() -> ClientRetroFit.safeAPICall(any())).thenReturn(result);

            DeliveryCourierServiceDTO deliveryCourierServiceDTO = servicesRepository.getServices(token, transactionUuid);
            Assertions.assertNotNull(deliveryCourierServiceDTO);
            verify(servicesBcAPI).getServices(anyString(), anyString());
        }
    }

    @Test
    void test_Services_Should_When_Data_Is_Error_500() throws Exception {
        try (MockedStatic<ClientRetroFit> mockedStatic = mockStatic(ClientRetroFit.class)) {
            NetworkConnectionException exception = new NetworkConnectionException("Generic Error", 500);
            mockedStatic.when(() -> ClientRetroFit.safeAPICall(any())).thenThrow(exception);

            Assertions.assertThrows(ApplicationException.class, () -> servicesRepository.getServices(token, transactionUuid));
        }
    }

    @Test
    void test_Services_Should_When_Data_Is_Error_404() throws Exception {
        try (MockedStatic<ClientRetroFit> mockedStatic = mockStatic(ClientRetroFit.class)) {
            NetworkConnectionException exception = new NetworkConnectionException("Not Found", 404);
            mockedStatic.when(() -> ClientRetroFit.safeAPICall(any())).thenThrow(exception);

            DeliveryCourierServiceDTO deliveryCourierServiceDTO = servicesRepository.getServices(token, transactionUuid);
            Assertions.assertEquals(deliveryCourierServiceDTO, null);
        }
    }
}
