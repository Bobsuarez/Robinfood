package com.robinfood.repository.lambdaexchange;

import com.google.gson.JsonObject;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.LambdaExchangesBcAPI;
import com.robinfood.repository.mocks.LambdaExchangeJsonObjectMock;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LambdaExchangeRepositoryTest {

    @Mock
    private LambdaExchangesBcAPI lambdaExchangesBcAPI;

    @Mock
    private Call<APIResponseEntity<JsonObject>> responseEntityCall;

    @InjectMocks
    private LambdaExchangeRepository lambdaExchangeRepository;

    private final String token = "token";

    @Test
    void test_GetLambdaExchanges_Should_OK_When_DataIsCorrect() throws Exception {

        when(lambdaExchangesBcAPI.getExchanges(
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(responseEntityCall);

        when(responseEntityCall.execute()).thenReturn(Response.success(
                new APIResponseEntity<>(
                        200,
                        LambdaExchangeJsonObjectMock.getDataDefault(),
                        "CO",
                        "Order filter returned",
                        "200"
                )
        ));

        lambdaExchangeRepository.getExchanges(
                "2023-03-14",
                "2023-03-7",
                token
        );

        verify(lambdaExchangesBcAPI)
                .getExchanges(
                        anyString(),
                        anyString(),
                        anyString()
                );
    }

    @Test
    void test_GetLambdaExChanges_Should_InternalServerError_When_WrongAnswerOfOrderBC() throws Exception {

        when(lambdaExchangesBcAPI.getExchanges(
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(responseEntityCall);

        when(responseEntityCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        ObjectExtensions.toJson(new APIResponseEntity<>(
                                500,
                                List.of(),
                                "CO",
                                "",
                                "Order filter not be returned"
                        ))
                ))
        );

        lambdaExchangeRepository.getExchanges(
                "2023-03-14",
                "2023-03-7",
                token
        );

        verify(lambdaExchangesBcAPI)
                .getExchanges(
                        anyString(),
                        anyString(),
                        anyString()
                );
    }
}
