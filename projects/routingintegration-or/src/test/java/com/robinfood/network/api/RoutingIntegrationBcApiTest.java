package com.robinfood.network.api;

import com.robinfood.datamock.RoutingIntegrationDTOMock;
import com.robinfood.entities.api.routingintegration.ResponseRoutingIntegrationEntity;
import com.robinfood.network.connection.HttpClientConnection;
import com.robinfood.util.ObjectMapperSingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class RoutingIntegrationBcApiTest {

    private static final String BEARER_AUTH = "Bearer ";

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoib3JkZXItY3JlYXRpb24iLCJhdWQiOiJzZXJ2aWNlIiwic3ViIjoib3JkZXItY3JlYXRpb24udjEiLCJwZXIiOltdLCJqdGkiOiIyYzhjNDYzNS0yM2I3LTQ1NDktYjBjMC1mZjMwNzQ2NDM0MmQiLCJpc3MiOiJiYWNrb2ZmaWNlLnYxIiwiaWF0IjoxNjg3NDYyOTkwLCJleHAiOjE5ODc0NjM1OTB9.eoynYjIgNp5AdMrU-7_agSD-j5W5EddTlAjrpk0ymFuy8Dib5vMrC0VVeVvjhgOL5DTgUfnSpLDPlcuv3YT08Q";

    @Mock
    private HttpClientConnection httpClientConnection;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_GetChannelIntegration_Should_ResponseRoutingIntegrationEntity_When_InvokeTheApi() {

        String dataRoutingDTO = ObjectMapperSingleton.objectToJson(RoutingIntegrationDTOMock.getDefault());

        when(httpClientConnection.connectionProcess(anyString(), anyString(), anyString())).thenReturn(dataRoutingDTO);

        RoutingIntegrationBcApi routingIntegrationBcApi = new RoutingIntegrationBcApi(httpClientConnection);

        ResponseRoutingIntegrationEntity responseRoutingIntegrationEntity =
                routingIntegrationBcApi.getChannelIntegration(10L, BEARER_AUTH + TOKEN, "valor");

        Assertions.assertEquals("POS", responseRoutingIntegrationEntity.getName());
        Assertions.assertEquals(1L, responseRoutingIntegrationEntity.getChannelId());
        Assertions.assertEquals(2L, responseRoutingIntegrationEntity.getId());
    }
}
