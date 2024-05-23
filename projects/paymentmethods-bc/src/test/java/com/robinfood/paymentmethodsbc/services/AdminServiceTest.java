package com.robinfood.paymentmethodsbc.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.repositories.CountryPaymentGatewaySettingsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewaySettingsCachedRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentMethodStoreFlowChannelsCachedRepository;
import com.robinfood.paymentmethodsbc.repositories.TerminalPaymentMethodSettingsCachedRepository;
import com.robinfood.paymentmethodsbc.sample.PaymentGatewaySamples;
import com.robinfood.paymentmethodsbc.services.impl.AdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @Mock
    private CountryPaymentGatewaySettingsRepository countryPaymentGatewaySettingsRepository;

    @Mock
    private PaymentGatewaySettingsCachedRepository paymentGatewaySettingsCachedRepository;

    @Mock
    private PaymentMethodStoreFlowChannelsCachedRepository paymentMethodStoreFlowChannelsRepository;

    @Mock
    private TerminalPaymentMethodSettingsCachedRepository terminalPaymentMethodSettingsRepository;

    @InjectMocks
    private AdminServiceImpl adminServiceImpl;

    @Test
    public void testCacheResetOk() throws BaseException {
        when(countryPaymentGatewaySettingsRepository.findAll())
            .thenReturn(
                PaymentGatewaySamples.getCountryPaymentGatewaySettings()
            );

        when(
                paymentGatewaySettingsCachedRepository.getSettingsPaymentGateway(
                    anyBoolean(),
                    anyLong(),
                    anyLong()
                )
            )
            .thenReturn(PaymentGatewaySamples.getPaymentGatewaySettings());

        assertAll(() -> adminServiceImpl.cacheReset());
    }
}
