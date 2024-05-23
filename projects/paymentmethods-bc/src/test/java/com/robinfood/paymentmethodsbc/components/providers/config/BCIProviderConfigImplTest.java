package com.robinfood.paymentmethodsbc.components.providers.config;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.components.providers.config.impl.BCIProviderConfigImpl;
import com.robinfood.paymentmethodsbc.configs.BCIUrlConfig;
import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.repositories.SSOTokenRepository;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BCIProviderConfigImplTest {
    @Mock
    private BCIUrlConfig bciUrlConfig;

    @Mock
    private TransactionsConfig transactionsConfig;

    @Mock
    private SSOTokenRepository ssoTokenRepository;

    @InjectMocks
    private BCIProviderConfigImpl bciProviderConfig;

    @Test
    public void testGetUrlShouldBeOk() {
        when(bciUrlConfig.getComponentUrl(anyString()))
            .thenReturn("http://component.com");

        assertAll(
            () ->
                bciProviderConfig.getUrl(
                    Map.of("bciComponent", "redeban"),
                    Map.of("bciComponent", "redeban")
                )
        );
    }

 

    @Test
    public void testGetUrlShouldBeErrorWhenAllConfigIsNull() {
        assertThrows(
            PaymentMethodsException.class,
            () -> bciProviderConfig.getUrl(null, null)
        );
    }

    @Test
    public void testGetUrlShouldBeOkWhenOrchConfigIsNull() {
        when(bciUrlConfig.getComponentUrl(anyString()))
            .thenReturn("http://component.com");

        assertAll(
            () ->
                bciProviderConfig.getUrl(
                    Map.of("bciComponent", "redeban"),
                    null
                )
        );
    }

    @Test
    public void testGetTransactionsConfigShouldBeOk() {
        assertAll(() -> bciProviderConfig.getTransactionsConfig());
    }

    @Test
    public void testGetServiceTokenShouldBeOk() throws BaseException {
        when(ssoTokenRepository.getServiceToken(anyBoolean()))
            .thenReturn("token");

        assertAll(() -> bciProviderConfig.getServiceToken(false));
    }

    @Test
    public void testGetServiceTokenShouldBeErrorWhenGenerateException()
        throws BaseException {
        when(ssoTokenRepository.getServiceToken(anyBoolean()))
            .thenThrow(BaseException.class);

        assertThrows(
            PaymentMethodsException.class,
            () -> bciProviderConfig.getServiceToken(false)
        );
    }
}
