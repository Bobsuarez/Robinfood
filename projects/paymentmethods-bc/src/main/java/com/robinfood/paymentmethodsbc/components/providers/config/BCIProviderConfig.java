package com.robinfood.paymentmethodsbc.components.providers.config;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import java.util.Map;

public interface BCIProviderConfig {
    String getUrl(Map<String, String> config, Map<String, String> orchConfig)
        throws PaymentMethodsException;

    String getServiceToken(boolean force) throws PaymentMethodsException;


    TransactionsConfig getTransactionsConfig();
 
}
