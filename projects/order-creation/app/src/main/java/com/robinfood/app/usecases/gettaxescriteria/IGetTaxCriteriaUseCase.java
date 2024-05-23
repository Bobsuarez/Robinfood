package com.robinfood.app.usecases.gettaxescriteria;

import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;

public interface IGetTaxCriteriaUseCase {

    /**
     * Consult the store info and then  set the criteria info for the taxes
     * @param token the authorization token
     * @param order the order
     */
    void invoke (String token, OrderDTO order);
}
