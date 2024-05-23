package com.robinfood.changestatusor.config.network.api.token;

import com.google.gson.Gson;
import com.robinfood.changestatusor.mappers.RequestJsonMapper;
import com.robinfood.changestatusor.models.retrofit.TokenRequest;
import com.robinfood.changestatusor.models.retrofit.TokenResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.http.Body;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.robinfood.changestatusor.constants.APIConstants.JSON;
import static com.robinfood.changestatusor.constants.APIConstants.SERVICES_V1;

/**
 * Connections to Token Business Capability
 */
@Component
@Slf4j
public class TokenToBusinessCapabilityAPI {

    @Value("${url.token-to-business-capability}")
    private String URL_TOKEN_BC_API;

    @SneakyThrows
    public TokenResponse get(@Body TokenRequest validateTaxRequest){

        final OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build();

        final String url = URL_TOKEN_BC_API.concat(SERVICES_V1);

        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(RequestJsonMapper.buildToTokenRequestJson(
                                validateTaxRequest),
                        JSON
                ))
                .build();

        log.info("view send url: {} request: {} ", url, request);

        final String responseBodyApi = Objects.requireNonNull(
                client.newCall(request)
                        .execute()
                        .body()
        ).string();

        log.info("view return responseBodyApi: {} ", responseBodyApi);

        final Gson gson = new Gson();

        TokenResponse tokenResponse = gson.fromJson(responseBodyApi, TokenResponse.class);

        return tokenResponse;
    }
}
