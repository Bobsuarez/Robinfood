package com.robinfood.paymentmethodsbc.components.providers.config.impl;

import static com.robinfood.paymentmethodsbc.constants.AppConstants.BCI_COMPONENT_SETTINGS_NAME;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.components.providers.config.BCIProviderConfig;
import com.robinfood.paymentmethodsbc.configs.BCIUrlConfig;
import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.repositories.SSOTokenRepository;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BCIProviderConfigImpl implements BCIProviderConfig {
    private final BCIUrlConfig bciUrlConfig;
    private final TransactionsConfig transactionsConfig;
    private final SSOTokenRepository ssoTokenRepository;

    public BCIProviderConfigImpl(
        BCIUrlConfig bciUrlConfig,
        TransactionsConfig transactionsConfig,
        SSOTokenRepository ssoTokenRepository
    ) {
        this.bciUrlConfig = bciUrlConfig;
        this.transactionsConfig = transactionsConfig;
        this.ssoTokenRepository = ssoTokenRepository;
    }

    @BasicLog
    @Override
    public String getUrl(
        Map<String, String> config,
        Map<String, String> orchConfig
    )
        throws PaymentMethodsException {
        log.info("Build BCI URL");
        return buildUrl(config, orchConfig);
    }

    private String buildUrl(
        Map<String, String> config,
        Map<String, String> orchConfig
    )
        throws PaymentMethodsException {
        return bciUrlConfig.getComponentUrl(
            getComponentName(config, orchConfig)
        );
    }

    private String getComponentName(
        Map<String, String> config,
        Map<String, String> orchConfig
    )
        throws PaymentMethodsException {
        return Optional
            .ofNullable(orchConfig)
            .map(
                (Map<String, String> confMap) ->
                    confMap.get(BCI_COMPONENT_SETTINGS_NAME)
            )
            .orElse(
                Optional
                    .ofNullable(config)
                    .map(
                        (Map<String, String> confMap) ->
                            confMap.get(BCI_COMPONENT_SETTINGS_NAME)
                    )
                    .orElseThrow(
                        () ->
                            new PaymentMethodsException(
                                String.format(
                                    "No se ha definido la propiedad '%s' para el orquestador o gateway",
                                    BCI_COMPONENT_SETTINGS_NAME
                                )
                            )
                    )
            );
    }

    @Override
    public TransactionsConfig getTransactionsConfig() {
        return transactionsConfig;
    }

    @Override
    public String getServiceToken(boolean force)
        throws PaymentMethodsException {
        try {
            return new StringBuilder()
                .append("Bearer ")
                .append(ssoTokenRepository.getServiceToken(force))
                .toString();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PaymentMethodsException(e.getMessage());
        }
    }
}
