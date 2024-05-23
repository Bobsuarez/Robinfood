package com.robinfood.paymentmethodsbc.services.impl;

import com.robinfood.paymentmethodsbc.model.CountryPaymentGatewaySetting;
import com.robinfood.paymentmethodsbc.repositories.CountryPaymentGatewaySettingsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewaySettingsCachedRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentMethodStoreFlowChannelsCachedRepository;
import com.robinfood.paymentmethodsbc.repositories.TerminalPaymentMethodSettingsCachedRepository;
import com.robinfood.paymentmethodsbc.services.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
    private final CountryPaymentGatewaySettingsRepository countryPaymentGatewaySettingsRepository;
    private final PaymentGatewaySettingsCachedRepository paymentGatewaySettingsCachedRepository;
    private final PaymentMethodStoreFlowChannelsCachedRepository paymentMethodStoreFlowChannelsRepository;
    private final TerminalPaymentMethodSettingsCachedRepository terminalPaymentMethodSettingsRepository;

    public AdminServiceImpl(
        CountryPaymentGatewaySettingsRepository countryPaymentGatewaySettingsRepository,
        PaymentGatewaySettingsCachedRepository paymentGatewaySettingsCachedRepository,
        PaymentMethodStoreFlowChannelsCachedRepository paymentMethodStoreFlowChannelsRepository,
        TerminalPaymentMethodSettingsCachedRepository terminalPaymentMethodSettingsRepository
    ) {
        this.countryPaymentGatewaySettingsRepository =
            countryPaymentGatewaySettingsRepository;
        this.paymentGatewaySettingsCachedRepository =
            paymentGatewaySettingsCachedRepository;
        this.paymentMethodStoreFlowChannelsRepository = paymentMethodStoreFlowChannelsRepository;
        this.terminalPaymentMethodSettingsRepository = terminalPaymentMethodSettingsRepository;
    }

    @Override
    public String cacheReset() {
        log.info("Cache reset requested");
        countryPaymentGatewaySettingsRepository
            .findAll()
            .forEach(
                (CountryPaymentGatewaySetting item) ->
                    paymentGatewaySettingsCachedRepository.getSettingsPaymentGateway(
                        true,
                        item.getCountry().getId(),
                        item.getPaymentGateway().getId()
                    )
            );

        paymentMethodStoreFlowChannelsRepository.clearCache();
        terminalPaymentMethodSettingsRepository.clearCache();

        return "OK";
    }
}
