package com.robinfood.customersbc.thirdparties.constants;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.TomcatHttpHandlerAdapter;
import org.springframework.security.web.server.context.SecurityContextServerWebExchange;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public final class LogConstants {
    public static final double SECONDS_DIVISOR = 1000;
    public static final String TOMCAT_SERVER_HTTP_REQUEST = "TomcatServerHttpRequest";

    public static final Map<String, Boolean> CLASS_NAME_MAP =
        ofEntries(
            entry(ServerWebExchange.class.getSimpleName(), Boolean.TRUE),
            entry(SecurityContextServerWebExchange.class.getSimpleName(), Boolean.TRUE),
            entry(ServerHttpRequest.class.getSimpleName(), Boolean.TRUE),
            entry(TomcatHttpHandlerAdapter.class.getSimpleName(), Boolean.TRUE),
            entry(TOMCAT_SERVER_HTTP_REQUEST, Boolean.TRUE)
        );

    private LogConstants() {}
}
