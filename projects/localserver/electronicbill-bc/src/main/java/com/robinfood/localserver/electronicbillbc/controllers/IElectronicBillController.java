package com.robinfood.localserver.electronicbillbc.controllers;

import com.robinfood.localserver.commons.dtos.http.ApiResponseDTO;
import com.robinfood.localserver.commons.dtos.orders.billing.OrderBillingDTO;
import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;
import com.robinfood.localserver.commons.dtos.sathub.response.SatCFE;

public interface IElectronicBillController {

    ApiResponseDTO<SatHubResultDto> sendElectronicBillToSefaz(OrderBillingDTO orderBillingDTO);

    ApiResponseDTO<SatHubResultDto> sendCancelSaleBillToSefaz(SatCFE satCFE, Long orderId);

}
