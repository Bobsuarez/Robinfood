package com.robinfood.network.http.di;

import com.robinfood.util.LogsUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

import java.io.IOException;
import java.util.Objects;

import static com.robinfood.constants.GeneralConstants.LEFT_ARROW;
import static com.robinfood.constants.GeneralConstants.RIGHT_ARROW;

public class SimpleLoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        final String methodRequest = request.method();
        final String bodyRequest = getBodyRequest(request);

        LogsUtil.info(RIGHT_ARROW + " %s", methodRequest);
        LogsUtil.info("Request URL: %s", request.url());
        LogsUtil.info("Request body: %s", bodyRequest);
        LogsUtil.info(LEFT_ARROW);

        Response response = chain.proceed(request);
        ResponseBody responseBodyCopy = response.peekBody(Long.MAX_VALUE);
        String bodyToJson = responseBodyCopy.string();

        LogsUtil.info(RIGHT_ARROW + " %s", methodRequest);
        LogsUtil.info("Response code: %s", response.code());
        LogsUtil.info("Response body: %s", bodyToJson);
        LogsUtil.info(LEFT_ARROW);

        return response;
    }

    private String getBodyRequest(Request request) throws IOException {

        if (Objects.isNull(request.body())) {
            return "";
        }

        final Buffer buffer = new Buffer();
        request.body().writeTo(buffer);
        return buffer.readUtf8();
    }
}
