package com.robinfood.paymentmethodsbc.components.providers.impl;

import com.robinfood.paymentmethodsbc.components.clients.BCIClient;
import com.robinfood.paymentmethodsbc.components.providers.config.BCIProviderConfig;
import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCICreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCIDeleteCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIPaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIRefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusRequestDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.sample.BCIProviderSample;
import feign.FeignException;
import feign.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;

import static com.robinfood.paymentmethodsbc.sample.BCIProviderSample.getBCIPaymentResponseDTOWithAdditionalInformation;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BCIProviderImplTest {
    @Mock
    private BCIClient bciHttpClient;

    @Mock
    private BCIProviderConfig bciProviderConfig;

    @Mock
    private TransactionsConfig transactionsConfig;

    @InjectMocks
    private BCIProviderImpl BCIProvider;

    @Test
    public void testDoPaymentShouldBeOk()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");

        when(bciProviderConfig.getTransactionsConfig())
            .thenReturn(mock(TransactionsConfig.class));

        when(
            bciHttpClient.generateTransaction(
                any(URI.class),
                anyString(),
                any(BCIPaymentRequestDTO.class)
            )
        )
            .thenReturn(BCIProviderSample.getBCIPaymentResponseDTO(200));

        assertAll(
            () ->
                BCIProvider.doPayment(
                    BCIProviderSample.getSettingsDTO(),
                    BCIProviderSample.getPaymentPipeDTO()
                )
        );
    }

    @Test
    public void testDoPaymentShouldBeOkWithAdditionalInformation()
        throws PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");

        when(bciProviderConfig.getTransactionsConfig()).thenReturn(transactionsConfig);

        when(transactionsConfig.getStatusId(anyString())).thenReturn(1L);

        when(transactionsConfig.isStatusPending(anyLong())).thenReturn(false);

        when(
            bciHttpClient.generateTransaction(
                any(URI.class), anyString(), any(BCIPaymentRequestDTO.class)
            )
        ).thenReturn(getBCIPaymentResponseDTOWithAdditionalInformation(200));

        assertAll(
            () ->
                BCIProvider.doPayment(
                    BCIProviderSample.getSettingsDTO(),
                    BCIProviderSample.getPaymentPipeDTO()
                )
        );
    }

    @Test
    public void testDoPaymentShouldBeAccepted() throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap())).thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean())).thenReturn("new service token!!!");

        when(bciProviderConfig.getTransactionsConfig()).thenReturn(transactionsConfig);

        when(transactionsConfig.getStatusId(anyString())).thenReturn(1L);

        when(transactionsConfig.isStatusPending(anyLong())).thenReturn(false);

        when(
            bciHttpClient.generateTransaction(
                any(URI.class), anyString(), any(BCIPaymentRequestDTO.class)
            )
        ).thenReturn(BCIProviderSample.getBCIPaymentResponseDTO(200));

        assertAll(
            () ->
                BCIProvider.doPayment(
                    BCIProviderSample.getSettingsDTO(),
                    BCIProviderSample.getPaymentPipeDTO()
                )
        );
    }

    @Test
    public void testDoPaymentShouldBePending() throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap())).thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean())).thenReturn("new service token!!!");

        when(bciProviderConfig.getTransactionsConfig()).thenReturn(transactionsConfig);

        when(transactionsConfig.getStatusId(anyString())).thenReturn(2L);

        when(transactionsConfig.isStatusPending(anyLong())).thenReturn(true);

        when(
            bciHttpClient.generateTransaction(
                any(URI.class), anyString(), any(BCIPaymentRequestDTO.class)
            )
        ).thenReturn(BCIProviderSample.getBCIPaymentResponseDTOPending(200));

        assertAll(
            () ->
                BCIProvider.doPayment(
                    BCIProviderSample.getSettingsDTO(),
                    BCIProviderSample.getPaymentPipeDTO()
                )
        );
    }

    @Test
    public void testDoPaymentShouldBeRejected() throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap())).thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean())).thenReturn("new service token!!!");

        when(bciProviderConfig.getTransactionsConfig()).thenReturn(transactionsConfig);

        when(transactionsConfig.getStatusId(anyString())).thenReturn(3L);

        when(transactionsConfig.isStatusPending(anyLong())).thenReturn(false);

        when(
            bciHttpClient.generateTransaction(
                any(URI.class), anyString(), any(BCIPaymentRequestDTO.class)
            )
        ).thenReturn(BCIProviderSample.getBCIPaymentResponseDTO(200));

        assertAll(
            () ->
                BCIProvider.doPayment(
                    BCIProviderSample.getSettingsDTO(),
                    BCIProviderSample.getPaymentPipeDTO()
                )
        );
    }

    @Test
    public void testDoPaymentShouldBeOkWhenServiceTokenIsInvalid()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");

        when(bciProviderConfig.getTransactionsConfig())
            .thenReturn(mock(TransactionsConfig.class));

        when(
            bciHttpClient.generateTransaction(
                any(URI.class),
                anyString(),
                any(BCIPaymentRequestDTO.class)
            )
        )
            .thenReturn(BCIProviderSample.getBCIPaymentResponseDTO(401));

        assertAll(
            () ->
                BCIProvider.doPayment(
                    BCIProviderSample.getSettingsDTO(),
                    BCIProviderSample.getPaymentPipeDTO()
                )
        );
    }

    @Test
    public void testDoPaymentShouldBeRejectedWhenException()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");

        when(bciProviderConfig.getTransactionsConfig())
            .thenReturn(mock(TransactionsConfig.class));

        when(
            bciHttpClient.generateTransaction(
                any(URI.class),
                anyString(),
                any(BCIPaymentRequestDTO.class)
            )
        )
            .thenThrow(FeignException.class);

        assertAll(
            () ->
                BCIProvider.doPayment(
                    BCIProviderSample.getSettingsDTO(),
                    BCIProviderSample.getPaymentPipeDTO()
                )
        );
    }

    @Test
    public void testDoRefundShouldBeOK()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");

        when(bciProviderConfig.getTransactionsConfig())
            .thenReturn(mock(TransactionsConfig.class));

        when(
            bciHttpClient.refundTransaction(
                any(URI.class),
                anyString(),
                any(BCIRefundRequestDTO.class)
            )
        )
            .thenReturn(BCIProviderSample.getBCIRefundResponseDTO(200));

        assertAll(
            () ->
                BCIProvider.doRefund(
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getConfig(),
                    "reference",
                    "code",
                    "code"
                )
        );
    }

    @Test
    public void testDoRefundShouldBeOKWhenServiceTokenIsInvalid()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");

        when(bciProviderConfig.getTransactionsConfig())
            .thenReturn(mock(TransactionsConfig.class));

        when(
            bciHttpClient.refundTransaction(
                any(URI.class),
                anyString(),
                any(BCIRefundRequestDTO.class)
            )
        )
            .thenReturn(BCIProviderSample.getBCIRefundResponseDTO(401));

        assertAll(
            () ->
                BCIProvider.doRefund(
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getConfig(),
                    "reference",
                    "code",
                    "code"
                )
        );
    }

    @Test
    public void testDoRefundShouldBeRejectedWhenException()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");

        when(bciProviderConfig.getTransactionsConfig())
            .thenReturn(mock(TransactionsConfig.class));

        when(
            bciHttpClient.refundTransaction(
                any(URI.class),
                anyString(),
                any(BCIRefundRequestDTO.class)
            )
        )
            .thenThrow(FeignException.class);

        assertAll(
            () ->
                BCIProvider.doRefund(
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getConfig(),
                    "reference",
                    "code",
                    "code"
                )
        );
    }

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
                BCIProvider.doCreditCardTokenization(
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
                BCIProvider.doCreditCardTokenization(
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
                BCIProvider.doCreditCardTokenization(
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
                BCIProvider.doCreditCardRemove(
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
                BCIProvider.doCreditCardRemove(
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
                BCIProvider.doCreditCardRemove(
                    BCIProviderSample.getConfig(),
                    BCIProviderSample.getConfig(),
                    "user-id",
                    "token"
                ),
            "PaymentMethodsException"
        );
    }

    @Test
    public void testDoValidateStatusShouldBeOk()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");


        when(
            bciHttpClient.validateStatus(
                any(URI.class),
                anyString(),
                any(BCITransactionStatusRequestDTO.class)
            )
        )
            .thenReturn(
                BCIProviderSample.getBCITransactionStatusResponseDTO(200)
            );

        assertAll(
            () ->
                BCIProvider.doValidateStatus(
                    BCIProviderSample.getSettingsDTO(),
                    BCIProviderSample.getTransactionStatusPipeDTO()
                )
        );
    }

    @Test
    public void testDoValidateStatusShouldBeOkWhenTokenIsInvalid()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");

        when(
            bciHttpClient.validateStatus(
                any(URI.class),
                anyString(),
                any(BCITransactionStatusRequestDTO.class)
            )
        )
            .thenReturn(
                BCIProviderSample.getBCITransactionStatusResponseDTO(401)
            );

        assertAll(
            () ->
                BCIProvider.doValidateStatus(
                    BCIProviderSample.getSettingsDTO(),
                    BCIProviderSample.getTransactionStatusPipeDTO()
                )
        );
    }

    @Test
    public void testDoValidateStatusShouldBeErrorWhenException()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");


        when(
            bciHttpClient.validateStatus(
                any(URI.class),
                anyString(),
                any(BCITransactionStatusRequestDTO.class)
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
                BCIProvider.doValidateStatus(
                    BCIProviderSample.getSettingsDTO(),
                    BCIProviderSample.getTransactionStatusPipeDTO()
                ),
            "PaymentMethodsException"
        );
    }

    @Test
    public void testDoCancelTransactionShouldBeOK()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");

        when(
            bciHttpClient.cancelTransaction(
                any(URI.class),
                anyString(),
                anyString()
            )
        )
            .thenReturn(
                BCIProviderSample.getBCICancelTransactionResponseDTO(200)
            );

        assertAll(
            () ->
                BCIProvider.doCancelTransaction(
                    BCIProviderSample.getSettingsDTO(),
                    "reference"
                )
        );
    }

    @Test
    public void testDoCancelTransactionShouldBeOkWhenTokenIsInvalid()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");


        when(
            bciHttpClient.cancelTransaction(
                any(URI.class),
                anyString(),
                anyString()
            )
        )
            .thenReturn(
                BCIProviderSample.getBCICancelTransactionResponseDTO(401)
            );

        assertAll(
            () ->
                BCIProvider.doCancelTransaction(
                    BCIProviderSample.getSettingsDTO(),
                    "reference"
                )
        );
    }

    @Test
    public void testDoCancelTransactionShouldBeRejectedErrorWhenException()
        throws BaseException, PaymentMethodsException {
        when(bciProviderConfig.getUrl(anyMap(), anyMap()))
            .thenReturn("http://bcicomponent.com/api/v1");

        when(bciProviderConfig.getServiceToken(anyBoolean()))
            .thenReturn("new service token!!!");


        when(
            bciHttpClient.cancelTransaction(
                any(URI.class),
                anyString(),
                anyString()
            )
        )
            .thenThrow(FeignException.class);

        assertAll(
            () ->
                BCIProvider.doCancelTransaction(
                    BCIProviderSample.getSettingsDTO(),
                    "reference"
                )
        );
    }
}