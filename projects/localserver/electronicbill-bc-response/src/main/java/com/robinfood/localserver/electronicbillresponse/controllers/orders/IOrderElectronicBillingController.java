package com.robinfood.localserver.electronicbillresponse.controllers.orders;

import com.robinfood.localserver.commons.dtos.electronicbill.RequestElectronicBillingDTO;
import com.robinfood.localserver.commons.dtos.http.ApiResponseDTO;

public interface IOrderElectronicBillingController {

    /**
     *
     * @param requestElectronicBillingDTO
     * @return
     */
    ApiResponseDTO<Boolean> createOrderElectronicBilling(RequestElectronicBillingDTO requestElectronicBillingDTO);

}
