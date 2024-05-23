package com.robinfood.customersbc.thirdparties.components;

import com.robinfood.customersbc.thirdparties.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionConfigurationTest {
    @InjectMocks
    private GlobalExceptionConfiguration globalExceptionConfiguration;

    @Mock
    private ServerWebExchange serverWebExchange;

    @Mock
    private ServerHttpResponse serverHttpResponse;

    @Mock
    private Throwable throwable;

    @Test
    void test_Handle_ShouldBe_Ok_When_CommittedIsTrue() {
        when(serverWebExchange.getResponse()).thenReturn(serverHttpResponse);
        when(serverHttpResponse.isCommitted()).thenReturn(false);

        HttpHeaders headers = new HttpHeaders();
        when(serverHttpResponse.getHeaders()).thenReturn(headers);

        when(serverHttpResponse.writeWith(any())).thenReturn(Mono.empty());

        throwable = new EntityNotFoundException("type", "message");
        Mono<Void> result = globalExceptionConfiguration.handle(serverWebExchange, throwable);

        StepVerifier.create(result)
            .expectNextCount(0)
            .verifyComplete();

        verify(serverWebExchange, times(1)).getResponse();

        verify(serverHttpResponse, times(1)).isCommitted();

        verify(serverHttpResponse, times(1)).getHeaders();

        verify(serverHttpResponse).writeWith(any());
    }

    @Test
    void test_Handle_ShouldBe_Ok_When_CommittedIsFalse() {
        when(serverWebExchange.getResponse()).thenReturn(serverHttpResponse);
        when(serverHttpResponse.isCommitted()).thenReturn(true);

        throwable = new EntityNotFoundException("type", "message");
        Mono<Void> result = globalExceptionConfiguration.handle(serverWebExchange, throwable);

        StepVerifier.create(result)
            .expectError(EntityNotFoundException.class)
            .verify();

        verify(serverWebExchange, times(1)).getResponse();

        verify(serverHttpResponse, times(1)).isCommitted();
    }
}