package com.robinfood.localserver.electronicbillbc.repositories;

import com.robinfood.localserver.commons.entities.electronicbill.FiscalElectronicCouponEntity;
import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;
import com.robinfood.localserver.commons.entities.electronicbill.cancelsale.CancelElectronicCouponEntity;

public interface IElectronicBillSaoPauloDataSource {

    SatHubResultDto sendFiscalElectronicCoupon(
            FiscalElectronicCouponEntity fiscalElectronicCouponEntity,
            Long orderId
    );

    SatHubResultDto sendCancelElectronicCoupon(
            CancelElectronicCouponEntity cancelElectronicCouponEntity,
            Long orderId,
            String emitCnpj
    );

}
