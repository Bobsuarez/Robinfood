package com.robinfood.paymentmethodsbc.repositories;

import java.util.Map;

/**
 * Repositorio encargado de consultar configuraciones cacheadas de payment gateways
 */
public interface PaymentGatewaySettingsCachedRepository {
    /**
     * Consulta configuraciones de payment gateways según pais
     * @param countryId id de pais a consultar
     * @param paymentGatewayId id de payment gateway a consultar
     * @return {@linkplain Map} lista de configuraciones
     */
    Map<String, String> getSettingsPaymentGateway(
        boolean forceUpdate,
        Long countryId,
        Long paymentGatewayId
    );

    /**
     * Consulta configuraciones de orquestadores de payment gateways según pais
     * @param countryId id de pais a consultar
     * @param orchestratorId id de orquestador a consultar
     * @return {@linkplain Map} lista de configuraciones
     */
    Map<String, String> getSettingsOrchestratorPaymentGateway(
        Long countryId,
        Long orchestratorId
    );

    /**
     * Consulta configuraciones de payment gateways habilitados para un pais específico
     * @param countryId id de pais a consultar
     * @return {@linkplain Map} lista de configuraciones
     */
    Map<Long, Map<String, String>> getPaymentGatewaySettingsByCountryForTokenization(
        Long countryId
    );
}
