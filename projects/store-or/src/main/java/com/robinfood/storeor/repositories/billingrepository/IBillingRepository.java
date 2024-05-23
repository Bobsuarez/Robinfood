package com.robinfood.storeor.repositories.billingrepository;

import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.CommandConfiguration.CommandConfigurationEntity;
import com.robinfood.storeor.entities.TreasureDepartmentsEntity;

import java.util.List;

public interface IBillingRepository {

    /**
     * Method that invokes the repository to obtain the billing configuration of a store by its identifier
     *
     * @param storeId Identifier of the store
     * @return information about billing configuration
     */
    APIResponseEntity<TreasureDepartmentsEntity> getBillingConfiguration(Long storeId, String token);

    /**
     * Method that invokes the repository to obtain the commandConfiguration
     *
     * @param storeId Identifier of the store
     * @return commandConfiguration of store
     */
    APIResponseEntity<List<CommandConfigurationEntity>> getCommandConfiguration(Long storeId, String token);

}
