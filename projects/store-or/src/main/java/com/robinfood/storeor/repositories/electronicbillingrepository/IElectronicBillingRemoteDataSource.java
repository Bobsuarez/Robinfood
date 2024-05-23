package com.robinfood.storeor.repositories.electronicbillingrepository;

import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingRequestEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingResponseEntity;

import java.util.List;

/**
 * Remote configuration data source that connects to external APIs to created or list order electronic billing response
 */
public interface IElectronicBillingRemoteDataSource {

    /**
     * Returns the result of create an order electronic billing response
     * @param electronicBillingRequestEntity the info to create order electronic billing response
     * @param token the authorization token
     * @return a future that contains the result creation of an order electronic billing response
     */
    APIResponseEntity<ElectronicBillingResponseEntity> save(
            String token,
            ElectronicBillingRequestEntity electronicBillingRequestEntity
    );

    /**
     * Returns the result of list orders with  electronic billing response
     * @param orderId  list of order's id
     * @param token the authorization token
     * @return a future that contains the result of  electonic billing for each order.
     */
    APIResponseEntity<List<ElectronicBillingEntity>> findAllByOrderIdIn (String token,List<Long> orderId);
}
