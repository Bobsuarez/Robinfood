package com.robinfood.localserver.electronicbillbc.usescases;

import com.robinfood.localserver.commons.dtos.orders.billing.OrderBillingDTO;
import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;

public interface ISendFiscalElectronicCouponUseCase {

    SatHubResultDto invoke(OrderBillingDTO orderBillingDTO);

}
