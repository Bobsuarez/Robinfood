package com.robinfood.network.api;

import com.robinfood.datamock.RequestRoutingIntegrationDTOMock;
import com.robinfood.dtos.request.RequestChangeOrderStatusDTO;
import com.robinfood.network.connection.HttpClientConnection;
import com.robinfood.util.ObjectMapperSingleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ChangeOrderStatusBcApiTest {

    private static final String BEARER_AUTH = "Bearer ";

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoib3JkZXItY3JlYXRpb24iLCJhdWQiOiJzZXJ2aWNlIiwic3ViIjoib3JkZXItY3JlYXRpb24udjEiLCJwZXIiOltdLCJqdGkiOiIyYzhjNDYzNS0yM2I3LTQ1NDktYjBjMC1mZjMwNzQ2NDM0MmQiLCJpc3MiOiJiYWNrb2ZmaWNlLnYxIiwiaWF0IjoxNjg3NDYyOTkwLCJleHAiOjE5ODc0NjM1OTB9.eoynYjIgNp5AdMrU-7_agSD-j5W5EddTlAjrpk0ymFuy8Dib5vMrC0VVeVvjhgOL5DTgUfnSpLDPlcuv3YT08Q";

    private static final String URL_CHANGE_STATUS = "https://lambdachangeorderstatus-bc.rf-dev.com/api/v1/orders/pos/creation/states";

    @Mock
    private HttpClientConnection httpClientConnection;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_ChangeOrderStatus_Should_ReturnAPIResponseEntity_When_InvokeTheAPI() {

        when(httpClientConnection
                .connectionProcess(
                        anyString(),
                        any(RequestChangeOrderStatusDTO.class),
                        anyString(),
                        anyString()
                )).thenReturn(ObjectMapperSingleton
                .objectToJson(RequestRoutingIntegrationDTOMock.getDefault()));

        ChangeOrderStatusBcApi changeOrderStatusBcApi = new ChangeOrderStatusBcApi(httpClientConnection);

        changeOrderStatusBcApi.changeOrderStatus(RequestChangeOrderStatusDTO.builder().orderId(1L).statusCode("ORDER_IN_PROGESS").origin("local server").statusId(2L).orderId(5493718L).notes("Se entrega la orden a domiciliario").build(), URL_CHANGE_STATUS, BEARER_AUTH + TOKEN);
    }
}
