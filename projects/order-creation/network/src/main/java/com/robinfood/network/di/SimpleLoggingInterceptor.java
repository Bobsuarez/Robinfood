package com.robinfood.network.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.robinfood.core.logging.mappeddiagnosticcontext.CreateTransactionCustomLog;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;
import static com.robinfood.core.constants.GlobalConstants.ENTER;
import static com.robinfood.core.constants.GlobalConstants.LEFT_ARROW;
import static com.robinfood.core.constants.GlobalConstants.RIGHT_ARROW;

public class SimpleLoggingInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleLoggingInterceptor.class);
    private static final Pattern compilePattern = Pattern.compile("(\\\\(?!n)|\\\\n)\\s*");
    private static final Pattern compilePatternURL = Pattern.compile("https://(.*?)/");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        CreateTransactionCustomLog.addUuid();
        MDC.put("resources", extractDomain(request.url().toString()));

        final String methodRequest = request.method();
        final String bodyRequest = getBodyRequest(request);

        String messageRequest = buildMessageLogs(methodRequest, bodyRequest, request, null);
        LOGGER.info(messageRequest);

        Response response = chain.proceed(request);
        ResponseBody responseBodyCopy = response.peekBody(Long.MAX_VALUE);
        String bodyToJson = responseBodyCopy.string();

        String messageResponse = buildMessageLogs(methodRequest, bodyToJson, null, response);
        LOGGER.info(messageResponse);

        MDC.remove("resources");

        return response;
    }

    private String getBodyRequest(Request request) throws IOException {

        if (Objects.isNull(request.body())) {
            return StringUtils.SPACE;
        }

        final Buffer buffer = new Buffer();
        request.body().writeTo(buffer);
        final String body = buffer.readUtf8();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String dataOfBody = gson.toJson(body);
        return dataOfBody.replaceAll(compilePattern.toString(), DEFAULT_STRING_VALUE);
    }

    private String buildMessageLogs(
            String methodRequest,
            String bodyRequest,
            Request request,
            Response response
    ) {

        StringBuilder createMessage = new StringBuilder();

        createMessage
                .append(RIGHT_ARROW)
                .append(ENTER)
                .append(StringUtils.SPACE)
                .append(methodRequest)
                .append(ENTER);

        if (Objects.nonNull(request)) {
            createMessage
                    .append("Request URL: ")
                    .append(request.url())
                    .append(ENTER)
                    .append("Request body: ");
        }
        if (Objects.nonNull(response)) {
            createMessage
                    .append("Response code: ")
                    .append(response.code())
                    .append(ENTER)
                    .append("Response body: ");
        }
        createMessage
                .append(bodyRequest)
                .append(ENTER)
                .append(LEFT_ARROW);

        return createMessage.toString();
    }

    private static String extractDomain(String url) {

        Matcher matcher = compilePatternURL.matcher(url);

        String domain = "No domain found";

        if (matcher.find()) {
            domain = matcher.group(1);
            return domain;
        }
        return domain;
    }
}
