package com.robinfood.localserver.electronicbillresponse.repositories.electronicbillingrepository;

import com.robinfood.localserver.commons.dtos.electronicbill.RequestElectronicBillingDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.ResponseElectronicBillingDTO;

/**
 * Repository that handles the creation of the order electronic billing response.
 */

public interface ICreateElectronicBillingRepository {

    /**
     * creates an order electronic billing response
     * @param requestOrderElectronicBillingDTO the request info to create order electronic billing response
     * @return a future containing the result of the operation
     */
    ResponseElectronicBillingDTO invoke(
            RequestElectronicBillingDTO requestOrderElectronicBillingDTO,
            String tokenUser

    );
}
