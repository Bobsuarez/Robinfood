package com.robinfood.changestatusor.config.network.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.robinfood.changestatusor.entities.ApiResponseEntity;
import com.robinfood.changestatusor.entities.changestateorderequestentities.ChangeStateOrderRequestEntity;
import com.robinfood.changestatusor.entities.changestateorderequestentities.ChangeStateOrderRespondEntity;
import com.robinfood.changestatusor.mappers.RequestJsonMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.robinfood.changestatusor.constants.APIConstants.JSON;
import static com.robinfood.changestatusor.constants.APIConstants.STATE_V1;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * Connections to Change Status Business Capability
 */
@Component
@Slf4j
@RefreshScope
public class ChangeStatusBCAPI {

    @Value("${url.change-to-status-business-capability}")
    private String URL_CHANGE_STATUS_BC_API;

    /**
     * Connects to an endpoint in orders business capability to change status
     *
     * @param changeStateOrderRequestEntity Request body with change status orders data
     * @param token                           the authorization token
     * @return the change status result
     */
    @SneakyThrows
    public ApiResponseEntity<ChangeStateOrderRespondEntity> changeState(
            ChangeStateOrderRequestEntity changeStateOrderRequestEntity,
            String token
    ){

        final OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build();

        final String url = URL_CHANGE_STATUS_BC_API.concat(STATE_V1);

        final Request request = new Request.Builder()
                .url(url)
                .addHeader(AUTHORIZATION,token)
                .post(RequestBody.create(RequestJsonMapper.buildToRequestJson(
                                changeStateOrderRequestEntity),
                        JSON
                ))
                .build();

        log.info("view send url: {} request: {} ", url, request);

        final String responseBodyApi = Objects.requireNonNull(
                client.newCall(request)
                        .execute()
                        .body()
        ).string();

        log.info("view responseBodyApi: {} ", responseBodyApi);

        final Gson gson = new Gson();

        return gson.fromJson(
                responseBodyApi,
                new TypeToken<ApiResponseEntity<ChangeStateOrderRespondEntity>>(){}.getType()
        );

    }
}
