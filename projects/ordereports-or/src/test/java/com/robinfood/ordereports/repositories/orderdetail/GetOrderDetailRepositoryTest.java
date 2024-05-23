package com.robinfood.ordereports.repositories.orderdetail;

import com.robinfood.app.library.comunication.ClientRetroFit;
import com.robinfood.app.library.dto.Result;
import com.robinfood.app.library.exception.ApplicationException;
import com.robinfood.app.library.exception.app.NetworkConnectionException;
import com.robinfood.ordereports.dtos.orders.OrderDetailDTO;
import com.robinfood.ordereports.entities.ApiResponseEntity;
import com.robinfood.ordereports.mocks.OrderDetailDTOMock;
import com.robinfood.ordereports.network.api.OrderDetailBcAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class GetOrderDetailRepositoryTest {

    @Mock
    private OrderDetailBcAPI orderDetailBcAPI;

    @InjectMocks
    private GetOrderDetailRepository getOrderDetailRepository;

    private final String token = "token";

    private final String transactionUuid = "abc-123";

    private final  Result result = new Result.Success(
            new ApiResponseEntity<>(
                    200,
                    OrderDetailDTOMock.getDataDefault(),
                    null,
                    "CO",
                    "OK",
                    "200"
            ));

    @Test
    void test_GeOrderDetail_Should_OK_When_DataIsCorrect() throws Exception {
        try (MockedStatic<ClientRetroFit> mockedStatic = mockStatic(ClientRetroFit.class)) {
            mockedStatic.when(() -> ClientRetroFit.safeAPICall(any())).thenReturn(result);

            OrderDetailDTO orderDetail = getOrderDetailRepository.getOrderDetail(token, transactionUuid);
            Assertions.assertNotNull(orderDetail);
            verify(orderDetailBcAPI).getOrderDetail(anyString(), anyString());
        }
    }

    @Test
    void test_GeOrderDetail_Should_OK_When_DataIsCorrect1() throws Exception {
        try (MockedStatic<ClientRetroFit> mockedStatic = mockStatic(ClientRetroFit.class)) {
            NetworkConnectionException exception = new NetworkConnectionException("Generic Error", 500);
            mockedStatic.when(() -> ClientRetroFit.safeAPICall(any())).thenThrow(exception);

            Assertions.assertThrows(ApplicationException.class, () -> getOrderDetailRepository.getOrderDetail(token, transactionUuid));
        }
    }
}
