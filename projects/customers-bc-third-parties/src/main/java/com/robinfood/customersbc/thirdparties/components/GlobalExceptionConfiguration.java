package com.robinfood.customersbc.thirdparties.components;

import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;
import com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers.DefaultExceptionHandler;
import com.robinfood.customersbc.thirdparties.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.robinfood.customersbc.thirdparties.commands.factories.ExceptionHandlerFactory.getExceptionHandler;

/**
 * Class for handle global exceptions in webflux
 */
@Slf4j
@Order(-1)
@RequiredArgsConstructor
public class GlobalExceptionConfiguration implements ErrorWebExceptionHandler {

    /**
     * This method handle all exceptions and use the ExceptionHandlerAdvice class
     * for parsing and map a general message output
     */
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        log.error("{}", ex.getClass());
        if (serverHttpResponse.isCommitted()) {
            return Mono.error(ex);
        }

        ResponseDTO<Object> responseDTO = getExceptionHandler(ex.getClass().getSimpleName())
            .orElse(new DefaultExceptionHandler())
            .handleException(ex);

        serverHttpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        serverHttpResponse.setStatusCode(HttpStatus.valueOf(responseDTO.getCode()));

        return serverHttpResponse.writeWith(
            Mono.fromSupplier(
                () -> ResponseUtils.buildDataBufferResponse(serverHttpResponse, responseDTO)
            )
        );
    }
}
