package org.example.connection;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.example.ObjectMapperSingletonUtil;
import org.example.di.SimpleLoggingInterceptor;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Class to make the http connection
 */
@Slf4j
public class HttpClientConnection {

    public String connectionProcess(
            String attribute,
            Object requestBodyData,
            String token,
            String url
    ) {

        long startTime = System.currentTimeMillis();

        final OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new SimpleLoggingInterceptor())
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build();

        Request request = buildHttp(requestBodyData, token, url);

        Call call = client.newCall(request);

        long endTime = 0L;
        try (Response response = call.execute()) {

            if (!response.isSuccessful()) {
                ResponseBody responseBodyCopy = response.peekBody(Long.MAX_VALUE);

                String bodyToJson = responseBodyCopy.string();

                log.error(bodyToJson);

                return null;
            }

            ResponseBody responseBodyCopy = response.peekBody(Long.MAX_VALUE);

            String bodyToJson = responseBodyCopy.string();

            if (bodyToJson.isEmpty()) {
                log.info("body is empty");
                return null;
            }

            JsonNode jsonNode = ObjectMapperSingletonUtil.stringToJsonNode(bodyToJson);

            JsonNode dataNode = jsonNode.get(attribute);
            endTime = System.currentTimeMillis();

            return dataNode.toString();

        } catch (Exception exception) {
            log.info("------------------------------------ ---------- {}" , exception.getMessage());
            exception.printStackTrace();
        } finally {
            long totalTime = endTime - startTime;
            log.info("--> Time HTTP {}", totalTime);
            call.cancel();
        }
        return null;
    }



    private static Request buildHttp(Object dataBody, String token, String url) {

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .addHeader("Authorization", token);

        if (Objects.isNull(dataBody)) {
            requestBuilder = requestBuilder.get();
        } else {
            requestBuilder = requestBuilder.post(RequestBody.create(
                    ObjectMapperSingletonUtil.objectToJson(
                            dataBody), MediaType.get("application/json; charset=utf-8")
            ));
        }
        return requestBuilder
                .build();
    }
}
