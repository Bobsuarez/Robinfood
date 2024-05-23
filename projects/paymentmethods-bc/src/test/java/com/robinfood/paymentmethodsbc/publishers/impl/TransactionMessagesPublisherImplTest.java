package com.robinfood.paymentmethodsbc.publishers.impl;

import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.repositories.TransactionMessagesCreateRepository;
import com.robinfood.paymentmethodsbc.repositories.TransactionMessagesNotifyRepository;
import com.robinfood.paymentmethodsbc.security.SSOTokenUtil;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.robinfood.paymentmethodsbc.sample.JmsTransactionDetailDTOSample.getJmsTransactionDetailDTO;
import static com.robinfood.paymentmethodsbc.sample.JmsTransactionGenerateDTOSample.getJmsTransactionGenerateDTO;
import static com.robinfood.paymentmethodsbc.sample.JmsTransactionStatusChangeDTOSample.getJmsTransactionStatusChangeDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionMessagesPublisherImplTest {
    @Mock
    private SSOTokenUtil ssoTokenUtil;

    @Mock
    private RetryTemplate retryTemplate;

    @Mock
    private TransactionMessagesCreateRepository transactionMessagesCreateRepository;

    @Mock
    private TransactionMessagesNotifyRepository transactionMessagesNotifyRepository;

    @InjectMocks
    private TransactionMessagesPublisherImpl transactionMessagesPublisher;

    private Request getRequest() {
        Map<String, Collection<String>> headers = new HashMap<>();
        headers.put("Content_Type", List.of("application/json"));
        return Request.create(
            Request.HttpMethod.POST, "url", headers, Request.Body.empty(), new RequestTemplate()
        );
    }

    private Response getResponse(Request request, int status, String reason) {
        return Response.builder()
            .request(request)
            .status(status)
            .reason(reason)
            .build();
    }

    @Test
    void send_generate_message_should_be_ok() throws Throwable {
       when(retryTemplate.execute(any(RetryCallback.class))).thenAnswer(invocation -> {
            RetryCallback retry = invocation.getArgument(0);
            return retry.doWithRetry(null);
        });
        when(transactionMessagesCreateRepository.createTransaction(anyString(), any()))
            .thenReturn(ResponseDTO.builder().build());

        when(ssoTokenUtil.getPublicJwtToken()).thenReturn("token");

        ResponseDTO<Object> response = transactionMessagesPublisher.sendGenerateMessage(getJmsTransactionGenerateDTO());

        assertEquals(ResponseDTO.builder().build(), response, "Test equals");
    }

    @Test
    void send_generate_message_throws_exception() throws Throwable {

        when(retryTemplate.execute(any(RetryCallback.class))).thenAnswer(invocation -> {
            RetryCallback retry = invocation.getArgument(0);
            return retry.doWithRetry(null);
        });
        when(transactionMessagesCreateRepository.createTransaction(anyString(), any()))
            .thenThrow(new FeignException.UnprocessableEntity(
                "UnprocessableEntity",
                getRequest(),
                Request.Body.create(
                    "{\"code\": 422,\"message\": \"Authorization has been denied for this request.\"}"
                ).asBytes()
            ));

        when(ssoTokenUtil.getPublicJwtToken()).thenReturn("token");

        assertThrows(
            PaymentMethodsException.class,
            () -> transactionMessagesPublisher.sendGenerateMessage(getJmsTransactionGenerateDTO()),
            "PaymentMethodsException"
        );
    }

    @Test
    void notify_change_status_should_be_ok() throws Throwable {
        when(retryTemplate.execute(any(RetryCallback.class))).thenAnswer(invocation -> {
            RetryCallback retry = invocation.getArgument(0);
            return retry.doWithRetry(null);
        });
        when(transactionMessagesNotifyRepository.notifyChangeStatusTransaction(anyString(), any()))
            .thenReturn(ResponseDTO.builder().build());

        when(ssoTokenUtil.getPublicJwtToken()).thenReturn("token");

        ResponseDTO<Object> response =
            transactionMessagesPublisher.notifyChangeStatusTransaction(
                getJmsTransactionStatusChangeDTO(),
                getJmsTransactionDetailDTO()
            );

        assertEquals(ResponseDTO.builder().build(), response, "Test equals");
    }

    @Test
    void notify_change_status_throws_exception() throws Throwable {

        when(retryTemplate.execute(any(RetryCallback.class))).thenAnswer(invocation -> {
            RetryCallback retry = invocation.getArgument(0);
            return retry.doWithRetry(null);
        });
        when(transactionMessagesNotifyRepository.notifyChangeStatusTransaction(anyString(), any()))
            .thenThrow(new FeignException.UnprocessableEntity(
                "UnprocessableEntity",
                getRequest(),
                Request.Body.create(
                    "{\"code\": 422,\"message\": \"Authorization has been denied for this request.\"}"
                ).asBytes()
            ));

        when(ssoTokenUtil.getPublicJwtToken()).thenReturn("token");

        assertThrows(
            PaymentMethodsException.class,
            () -> transactionMessagesPublisher.notifyChangeStatusTransaction(
                getJmsTransactionStatusChangeDTO(),
                getJmsTransactionDetailDTO()
            ),
            "PaymentMethodsException"
        );
    }
}