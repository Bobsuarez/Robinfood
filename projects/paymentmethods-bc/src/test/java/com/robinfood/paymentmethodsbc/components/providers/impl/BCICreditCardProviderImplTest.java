package com.robinfood.paymentmethodsbc.components.providers.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.robinfood.paymentmethodsbc.components.clients.BCIClient;
import com.robinfood.paymentmethodsbc.components.providers.config.BCIProviderConfig;
import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCICreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCIDeleteCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCIUpdateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.sample.BCIProviderSample;
import feign.FeignException;
import feign.Request;

@ExtendWith(MockitoExtension.class)
public class BCICreditCardProviderImplTest {
    @Mock
    private BCIClient bciHttpClient;

    @Mock
    private BCIProviderConfig bciProviderConfig;

    @Mock
    private TransactionsConfig transactionsConfig;

    @InjectMocks
    private BCICreditCardProviderImpl BCIProvider;


    @Test
    public void testDoCreditCardTokenizationShouldBeOk()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");


        when(
            bciHttpClient.createCreditCard(
                any(URI.class),
                anyString(),
                any(BCICreateCreditCardRequestDTO.class)
            )
        ).thenReturn(BCIProviderSample.getBCICreditCardResponseDTO(200));

        assertAll(
            () ->
                BCIProvider.createToken(
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getCreditCardTokenDTO()
                )
        );
    }

    @Test
    public void testDoCreditCardTokenizationShouldBeOkWhenTokenIsInvalid()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");

        when(
            bciHttpClient.createCreditCard(
                any(URI.class),
                anyString(),
                any(BCICreateCreditCardRequestDTO.class)
            )
        )
            .thenReturn(BCIProviderSample.getBCICreditCardResponseDTO(401));

        assertAll(
            () ->
                BCIProvider.createToken(
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getCreditCardTokenDTO()
                )
        );
    }

    @Test
    public void testDoCreditCardTokenizationShouldBeRejectedWhenException()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");


        when(
            bciHttpClient.createCreditCard(
                any(URI.class),
                anyString(),
                any(BCICreateCreditCardRequestDTO.class)
            )
        )
            .thenThrow(
                new FeignException.BadRequest(
                    "message",
                    mock(Request.class),
                    "body".getBytes()
                )
            );

        assertThrows(
            PaymentMethodsException.class,
            () ->
                BCIProvider.createToken(
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getCreditCardTokenDTO()
                ),
            "PaymentMethodsException"
        );
    }

    @Test
    public void testDoCreditCardRemoveShouldBeOk()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");


        when(
            bciHttpClient.deleteCreditCard(
                any(URI.class),
                anyString(),
                any(BCIDeleteCreditCardRequestDTO.class)
            )
        )
            .thenReturn(BCIProviderSample.getBCICreditCardResponseDTO(200));

        assertAll(
            () ->
                BCIProvider.removeToken(
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getConfig(),
                    "user-id",
                    "token"
                )
        );
    }

    @Test
    public void testDoCreditCardRemoveShouldBeOWhenTokenIsInvalid()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");

        when(
            bciHttpClient.deleteCreditCard(
                any(URI.class),
                anyString(),
                any(BCIDeleteCreditCardRequestDTO.class)
            )
        )
            .thenReturn(BCIProviderSample.getBCICreditCardResponseDTO(401));

        assertAll(
            () ->
                BCIProvider.removeToken(
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getConfig(),
                    "user-id",
                    "token"
                )
        );
    }

    @Test
    public void testDoCreditCardRemoveShouldBeErrorResponseWhenException()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");

        when(
            bciHttpClient.deleteCreditCard(
                any(URI.class),
                anyString(),
                any(BCIDeleteCreditCardRequestDTO.class)
            )
        )
            .thenThrow(
                new FeignException.BadRequest(
                    "message",
                    mock(Request.class),
                    "body".getBytes()
                )
            );

        assertThrows(
            PaymentMethodsException.class,
            () ->
                BCIProvider.removeToken(
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getConfig(),
                    "user-id",
                    "token"
                ),
            "PaymentMethodsException"
        );
    }

    @Test
    public void testDoUpdateCreditCardTokenizationShouldBeOk()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");


        when(
            bciHttpClient.updateCreditCard(
                any(URI.class),
                anyString(),
                any(BCIUpdateCreditCardRequestDTO.class)
            )
        ).thenReturn(BCIProviderSample.getBCICreditCardResponseDTO(200));

        assertAll(
            () ->
                BCIProvider.updateToken(
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getCreditCardTokenDTO(),
                    "CURRENT_TOKEN_ID"
                )
        );
    }


    @Test
    public void testDoUpdateCreditCardTokenizationShouldBeOkWhenInvalidToken()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");


        when(
            bciHttpClient.updateCreditCard(
                any(URI.class),
                anyString(),
                any(BCIUpdateCreditCardRequestDTO.class)
            )
        ).thenReturn(BCIProviderSample.getBCICreditCardResponseDTO(401));

        assertAll(
            () ->
                BCIProvider.updateToken(
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getCreditCardTokenDTO(),
                    "CURRENT_TOKEN_ID"
                )
        );
    }



    @Test
    public void testDoUpdateCreditCardTokenizationShouldBeRejectedWhenException()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");


        when(
            bciHttpClient.updateCreditCard(
                any(URI.class),
                anyString(),
                any(BCIUpdateCreditCardRequestDTO.class)
            )
        )
            .thenThrow(
                new FeignException.BadRequest(
                    "message",
                    mock(Request.class),
                    "body".getBytes()
                )
            );

        assertThrows(
            PaymentMethodsException.class,
            () ->
                BCIProvider.updateToken(
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getCreditCardTokenDTO(),
                    "CURRENT_TOKEN_ID"
                ),
            "PaymentMethodsException"
        );
    }

}
