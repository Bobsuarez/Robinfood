package com.robinfood.network.http.connection;

import com.fasterxml.jackson.databind.JsonNode;
import com.robinfood.exceptions.ApiClientsException;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.network.http.di.SimpleLoggingInterceptor;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.robinfood.constants.GeneralConstants.JSON;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_RESPONSE_HTTP;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_RESPONSE_HTTP_BODY_NULL;

/**
 * Class to make the http connection
 */
public class HttpClientConnection {

    public JsonNode connectionProcess(
            String url
    ) {
        return connectionProcess(null, url);
    }

    /**
     * Method that executes the connection process
     *
     * @param requestBodyData object data send
     * @param url             parameter url of connection
     * @return String data response
     * @throws ApiClientsException In case of generating error
     */
    public JsonNode connectionProcess(
            Object requestBodyData,
            String url
    ) {

        long startTime = System.currentTimeMillis();

        final OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new SimpleLoggingInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Request request = buildHttp(requestBodyData, url);

        Call call = client.newCall(request);

        long endTime = 0L;
        try (Response response = call.execute()) {

            if (!response.isSuccessful()) {
                LogsUtil.error(ERROR_RESPONSE_HTTP.getMessageWithCodeWithComplement(url));
                throw new ApiClientsException(
                        ERROR_RESPONSE_HTTP.replaceComplement(
                                url + " Body : " + Objects.requireNonNull(response.body()).string()
                        ),
                        response.code()
                );
            }

            ResponseBody responseBodyCopy = response.peekBody(Long.MAX_VALUE);

            String bodyToJson = responseBodyCopy.string();

            if (bodyToJson.isEmpty()) {
                LogsUtil.error(ERROR_RESPONSE_HTTP.getMessageWithCodeWithComplement(url));
                throw new ApiClientsException(ERROR_RESPONSE_HTTP_BODY_NULL.replaceComplement(url), response.code());
            }

            endTime = System.currentTimeMillis();

            return ObjectMapperSingleton.stringToJsonNode(bodyToJson);

        } catch (ApplicationException a) {

            LogsUtil.error(a.getMessage());
            throw new ApiClientsException(
                    a.getMessage(),
                    a.getApiGatewayResponseDTO().getStatusCode());

        } catch (Exception exception) {

            LogsUtil.error(exception.getMessage());
            throw new ApiClientsException(exception);

        } finally {
            long totalTime = endTime - startTime;
            LogsUtil.info("--> Time HTTP %s", totalTime);
        }
    }

    private Request buildHttp(Object dataBody, String url) {

        Request.Builder requestBuilder = new Request.Builder()
                .url(url);

        if (Objects.isNull(dataBody)) {
            requestBuilder = requestBuilder.get();
        } else {
            requestBuilder = requestBuilder.post(RequestBody.create(
                    ObjectMapperSingleton.objectToJson(
                            dataBody), JSON
            ));
        }
        return requestBuilder
                .build();
    }
}
