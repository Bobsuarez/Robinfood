package com.robinfood.localserver.electronicbillresponse.usecase.createelectronicbilling;

import com.robinfood.localserver.commons.dtos.electronicbill.RequestElectronicBillingDTO;

/**
 * The interface that you must implement to create order electronic billing
 */
public interface ICreateOrderElectronicBillUseCase {

    /**
     * Executes the corresponding process to call apigateway and create order electronic billing
     *
     * @param requestElectronicBillingDTO Request order
     * @return StoreResponseDTO The result of the specified store
     */
    Boolean invoke(RequestElectronicBillingDTO requestElectronicBillingDTO);

}
