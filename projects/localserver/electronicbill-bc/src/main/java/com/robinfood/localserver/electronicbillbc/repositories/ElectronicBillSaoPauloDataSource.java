package com.robinfood.localserver.electronicbillbc.repositories;

import com.robinfood.localserver.commons.dtos.electronicbill.FiscalElectronicCouponDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.cancelsale.CancelElectronicCouponDTO;
import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;
import com.robinfood.localserver.commons.dtos.http.ApiResponseDTO;
import com.robinfood.localserver.commons.entities.electronicbill.FiscalElectronicCouponEntity;
import com.robinfood.localserver.commons.entities.electronicbill.cancelsale.CancelElectronicCouponEntity;
import com.robinfood.localserver.commons.exceptions.HttpClientException;
import com.robinfood.localserver.commons.mappers.electronicbill.ICancelElectronicCouponMapper;
import com.robinfood.localserver.commons.mappers.electronicbill.IFiscalElectronicCouponMapper;
import com.robinfood.localserver.commons.utilities.JsonLogUtil;
import com.robinfood.localserver.electronicbillbcsaopaulo.controllers.v1.sathubcontroller.ISatHubController;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ElectronicBillSaoPauloDataSource implements IElectronicBillSaoPauloDataSource {

    private final IFiscalElectronicCouponMapper fiscalElectronicCouponMapper;
    private final ICancelElectronicCouponMapper cancelElectronicCouponMapper;
    private final ISatHubController satHubController;

    public ElectronicBillSaoPauloDataSource(
            IFiscalElectronicCouponMapper fiscalElectronicCouponMapper,
            ICancelElectronicCouponMapper cancelElectronicCouponMapper,
            ISatHubController satHubController
    ) {
        this.fiscalElectronicCouponMapper = fiscalElectronicCouponMapper;
        this.cancelElectronicCouponMapper = cancelElectronicCouponMapper;
        this.satHubController = satHubController;
    }

    @SneakyThrows
    public SatHubResultDto sendFiscalElectronicCoupon(FiscalElectronicCouponEntity fiscalElectronicCouponEntity,
            Long orderId) {

        log.debug("Init LocalOrderOrDataSource {}", fiscalElectronicCouponEntity);

        log.info("JSON ElectronicBillSaoPauloDataSource electronic bill => {}",
                JsonLogUtil.logJson(fiscalElectronicCouponEntity, ElectronicBillSaoPauloDataSource.class));

        FiscalElectronicCouponDTO fiscalElectronicCouponDTO = fiscalElectronicCouponMapper
                .toCouponFiscalElectronicCouponDTO(fiscalElectronicCouponEntity);

        log.info("After mapping in sendFiscalElectronicCoupon");

        ApiResponseDTO<SatHubResultDto> responseSatHub = satHubController.sendSaleData(fiscalElectronicCouponDTO,
                orderId);

        if (!responseSatHub.getCode().equals(HttpStatus.OK.value())) {
            throw new HttpClientException(responseSatHub.getMessage());
        }

        return responseSatHub.getData();
    }

    @SneakyThrows
    public SatHubResultDto sendCancelElectronicCoupon(CancelElectronicCouponEntity cancelElectronicCouponEntity,
                                                      Long orderId, String emitCnpj) {

        log.debug("Init LocalOrderOrDataSource Cancel Sale {}", cancelElectronicCouponEntity);

        log.info("JSON ElectronicBillSaoPauloDataSource cancel sale electronic bill => {}",
                JsonLogUtil.logJson(cancelElectronicCouponEntity, ElectronicBillSaoPauloDataSource.class));

        CancelElectronicCouponDTO cancelElectronicCouponDTO = cancelElectronicCouponMapper
                .toCancelElectronicCouponDTO(cancelElectronicCouponEntity);

        log.info("After mapping in sendCancelElectronicCoupon");

        ApiResponseDTO<SatHubResultDto> responseSatHub = satHubController.sendCancelSaleData(cancelElectronicCouponDTO,
                orderId, emitCnpj);

        if (!responseSatHub.getCode().equals(HttpStatus.OK.value())) {
            throw new HttpClientException(responseSatHub.getMessage());
        }

        return responseSatHub.getData();
    }
}
