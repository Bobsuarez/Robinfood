package com.robinfood.localserver.electronicbillbc.repositories;

import com.robinfood.localserver.commons.dtos.electronicbill.FiscalElectronicCouponDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.cancelsale.CancelElectronicCouponDTO;
import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;

public interface IFiscalElectronicCouponRepository {

    SatHubResultDto sendFiscalElectronicCoupon(
            FiscalElectronicCouponDTO fiscalElectronicCouponDTO,
            Long orderId
    );

    SatHubResultDto sendCancelElectronicCoupon(
            CancelElectronicCouponDTO cancelElectronicCouponDTO,
            Long orderId,
            String emitCnpj
    );

}
