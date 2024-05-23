package com.robinfood.localserver.electronicbillbc.repositories;

import com.robinfood.localserver.commons.dtos.electronicbill.FiscalElectronicCouponDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.cancelsale.CancelElectronicCouponDTO;
import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;
import com.robinfood.localserver.commons.entities.electronicbill.FiscalElectronicCouponEntity;
import com.robinfood.localserver.commons.entities.electronicbill.cancelsale.CancelElectronicCouponEntity;
import com.robinfood.localserver.commons.mappers.electronicbill.ICancelElectronicCouponMapper;
import com.robinfood.localserver.commons.mappers.electronicbill.IFiscalElectronicCouponMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class FiscalElectronicCouponRepository implements IFiscalElectronicCouponRepository {

    private final IFiscalElectronicCouponMapper fiscalElectronicCouponMapper;
    private final ICancelElectronicCouponMapper cancelElectronicCouponMapper;
    private final IElectronicBillSaoPauloDataSource electronicBillSaoPauloDataSource;

    public FiscalElectronicCouponRepository(
            IFiscalElectronicCouponMapper fiscalElectronicCouponMapper,
            ICancelElectronicCouponMapper cancelElectronicCouponMapper,
            IElectronicBillSaoPauloDataSource electronicBillSaoPauloDataSource
    ) {
        this.fiscalElectronicCouponMapper = fiscalElectronicCouponMapper;
        this.cancelElectronicCouponMapper = cancelElectronicCouponMapper;
        this.electronicBillSaoPauloDataSource = electronicBillSaoPauloDataSource;
    }

    public SatHubResultDto sendFiscalElectronicCoupon(
            FiscalElectronicCouponDTO fiscalElectronicCouponDTO,
            Long orderId
    ) {

        log.debug("Init FiscalElectronicCouponRepository: {}", fiscalElectronicCouponDTO);

        FiscalElectronicCouponEntity fiscalElectronicCouponEntity = fiscalElectronicCouponMapper
                .toCouponFiscalElectronicEntity(fiscalElectronicCouponDTO);

        return electronicBillSaoPauloDataSource.sendFiscalElectronicCoupon(fiscalElectronicCouponEntity, orderId);
    }

    public SatHubResultDto sendCancelElectronicCoupon(
            CancelElectronicCouponDTO cancelElectronicCouponDTO,
            Long orderId,
            String emitCnpj
    ) {

        log.debug("Init FiscalElectronicCouponRepository sendCancelElectronicCoupon : {}", cancelElectronicCouponDTO);

        CancelElectronicCouponEntity cancelElectronicCouponEntity = cancelElectronicCouponMapper
                .toCancelElectronicCouponEntity(cancelElectronicCouponDTO);

        return electronicBillSaoPauloDataSource.sendCancelElectronicCoupon(cancelElectronicCouponEntity, orderId,
                emitCnpj);
    }

}
