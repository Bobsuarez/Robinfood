package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.PaymentMethodStoreFlowChannel;
import java.util.List;

public interface PaymentMethodStoreFlowChannelsCachedRepository {

    /**
     * Consulta configuraciones de payment gateways según pais
     * @param storeId id de tienda a consultar
     * @param channelId id de canal a consultar
     * @param flowId id del flujo a consultar
     * @param status status de registros a consultar
     * @return {@linkplain List<PaymentMethodStoreFlowChannel>}
     */
    List<PaymentMethodStoreFlowChannel> findByStoreIdAndChannelIdAndFlowIdAndStatus(
        Long storeId,
        Long channelId,
        Long flowId,
        boolean status
    );

    /**
     * Service for clearing the cache
     */
    void clearCache();
}
