package com.robinfood.localserver.electronicbillbc.usescases;

import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;
import com.robinfood.localserver.commons.dtos.sathub.response.SatCFE;

public interface ISendCancelSaleElectronicCouponUseCase {

    SatHubResultDto invoke(SatCFE satCFE, Long orderId);

}
