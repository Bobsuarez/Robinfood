package com.robinfood.localserver.electronicbillresponse.repositories.electronicbillingrepository;

import com.robinfood.localserver.commons.entities.electronicbill.ElectronicBillingRequestEntity;
import com.robinfood.localserver.commons.entities.electronicbill.ElectronicBillingResponseEntity;
import com.robinfood.localserver.commons.entities.http.ApiResponseEntity;

/**
 * Remote configuration data source that connects to external APIs to create an order electronic billing response
 */
public interface ICreateElectronicBillingRemoteDataSource {

    /**
     * Returns the result of create an order electronic billing response
     * @param electronicBillingRequest the info to create order electronic billing response
     * @return a future that contains the result creation of an order electronic billing response
     */
    ApiResponseEntity<ElectronicBillingResponseEntity> invoke(
            ElectronicBillingRequestEntity electronicBillingRequest,
            String tokenUser
    );
}
