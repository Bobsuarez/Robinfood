package com.robinfood.paymentmethodsbc.services;

import com.robinfood.paymentmethodsbc.dto.api.paymentmethods.GeneralPaymentMethodDetailsDTO;
import java.util.List;

/**
 * Interface that allows to apply general payment methods
 */
public interface GeneralPaymentMethodService {

    /**
     * Allows the consultation of general payment methods
     * by store, channel, flow and terminal
     * @param storeId {@linkplain Long}
     * @param channelId {@linkplain Long}
     * @param flowId {@linkplain Long}
     * @param terminalUuid {@linkplain String}
     * @return {@linkplain List<GeneralPaymentMethodDetailsDTO>}
     */
    List<GeneralPaymentMethodDetailsDTO> getPaymentMethodsByStoreChannelFlowAndTerminal(
        Long storeId,
        Long channelId,
        Long flowId,
        String terminalUuid
    );
}
