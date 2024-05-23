package com.robinfood.repository.configurationsbc;

import com.robinfood.core.dtos.transactionrequestdto.ChannelDTO;
import com.robinfood.core.models.domain.configuration.Store;
import com.robinfood.core.models.domain.configuration.StoreInformation;

/**
 * Contract that defines the consumptions to configurations bc
 */
public interface IConfigurationRepository {

    /**
     * Method that obtains the channel by the ChannelId
     *
     * @param token to access configurations bc
     * @param channelId    of the channelId
     *
     * @return channelDTO with channelId
     */
    ChannelDTO getChannel (
            String token,
            Long channelId
    );

    /**
     * Method that obtains the posId by the storeId and the paymentMethodIds
     *
     * @param token to access configurations bc
     * @param store with storeId
     *
     * @return store with posId
     */
    Store getPosIdByStoreIdAndPaymentMethodIds(
            String token,
            Store store
    );

    /**
     * Method that obtains the posId by the storeId and the paymentMethodIds
     *
     * @param token to access configurations bc
     * @param id    of the store
     *
     * @return store with posId
     */
    StoreInformation getStoreConfiguration(
            String token,
            Long id
    );
}
