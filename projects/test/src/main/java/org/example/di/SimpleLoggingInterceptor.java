package org.example.di;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.example.ObjectMapperSingletonUtil;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

@Slf4j
public class SimpleLoggingInterceptor implements Interceptor {

    private static final Pattern compilePattern = Pattern.compile("(\\\\(?!n)|\\\\n)\\s*");

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        final String methodRequest = request.method();
        final String bodyRequest = getBodyRequest(request);

        String messageRequest = buildMessageLogs(methodRequest, bodyRequest, request, null);
        log.info(messageRequest);

        Response response = chain.proceed(request);
        ResponseBody responseBodyCopy = response.peekBody(Long.MAX_VALUE);
        String bodyToJson = responseBodyCopy.string();

        String messageResponse = buildMessageLogs(methodRequest, bodyToJson, null, response);
        log.info(messageResponse);

        return response;
    }

    private String getBodyRequest(Request request) throws IOException {

        if (Objects.isNull(request.body())) {
            return "";
        }

        final Buffer buffer = new Buffer();
        request.body().writeTo(buffer);
        final String body = buffer.readUtf8();
        String dataOfBody = ObjectMapperSingletonUtil.objectToJson(body);
        return dataOfBody.replaceAll(compilePattern.toString(), "");
    }

    private String buildMessageLogs(
            String methodRequest,
            String bodyRequest,
            Request request,
            Response response
    ) {

        StringBuilder createMessage = new StringBuilder();

        createMessage
                .append("--->")
                .append("\n")
                .append(" ")
                .append(methodRequest)
                .append("\n");

        if (Objects.nonNull(request)) {
            createMessage
                    .append("Request URL: ")
                    .append(request.url());
        }
        if (Objects.nonNull(response)) {
            createMessage
                    .append("Request code: ")
                    .append(response.code());
        }
        createMessage.append("\n")
                .append("Request body: ")
                .append(bodyRequest)
                .append("\n")
                .append("<---");

//        System.out.println(createMessage);

        return createMessage.toString();
    }
}
