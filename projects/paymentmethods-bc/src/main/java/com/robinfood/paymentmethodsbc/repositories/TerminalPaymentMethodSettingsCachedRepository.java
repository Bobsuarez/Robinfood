package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.TerminalPaymentMethodSetting;
import java.util.List;
import java.util.Map;

public interface TerminalPaymentMethodSettingsCachedRepository {

    /**
     * Consulta configuraciones de payment methods según terminal
     * y payment gateway
     * @param terminalId id de terminal
     * @param paymentGatewayId id de payment gateway a consultar
     * @param parameterVisible parameter visibility
     * @return {@linkplain Map} lista de configuraciones
     */
    List<TerminalPaymentMethodSetting> findByTerminalIdAndPaymentGatewayIdAndTerminalParameterVisible(
        Long terminalId,
        Long paymentGatewayId,
        boolean parameterVisible
    );

    /**
     * Consulta configuraciones de payment methods según terminal
     * y payment gateway
     * @param terminalId id de terminal
     * @param paymentGatewayId id de payment gateway a consultar
     * @return {@linkplain Map} lista de configuraciones
     */
    Map<String, String> findAllSettingsTerminalPaymentMethod(
        Long terminalId,
        Long paymentGatewayId
    );

    /**
     * Service for clearing the cache
     */
    void clearCache();
}
