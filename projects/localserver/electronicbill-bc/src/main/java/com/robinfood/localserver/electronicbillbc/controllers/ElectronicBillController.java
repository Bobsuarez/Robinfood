package com.robinfood.localserver.electronicbillbc.controllers;

import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;
import com.robinfood.localserver.commons.dtos.http.ApiResponseDTO;
import com.robinfood.localserver.commons.dtos.orders.billing.OrderBillingDTO;
import com.robinfood.localserver.commons.dtos.sathub.response.SatCFE;
import com.robinfood.localserver.commons.utilities.JsonLogUtil;
import com.robinfood.localserver.electronicbillbc.usescases.ISendCancelSaleElectronicCouponUseCase;
import com.robinfood.localserver.electronicbillbc.usescases.ISendFiscalElectronicCouponUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("ElectronicBillController")
@Slf4j
public class ElectronicBillController implements IElectronicBillController {

    private final ISendFiscalElectronicCouponUseCase sendFiscalElectronicCouponUseCase;
    private final ISendCancelSaleElectronicCouponUseCase sendCancelSaleElectronicCouponUseCase;

    public ElectronicBillController(ISendFiscalElectronicCouponUseCase sendFiscalElectronicCouponUseCase,
                                    ISendCancelSaleElectronicCouponUseCase sendCancelSaleElectronicCouponUseCase) {

        this.sendFiscalElectronicCouponUseCase = sendFiscalElectronicCouponUseCase;
        this.sendCancelSaleElectronicCouponUseCase = sendCancelSaleElectronicCouponUseCase;
    }

    public ApiResponseDTO<SatHubResultDto> sendElectronicBillToSefaz(OrderBillingDTO orderBillingDTO) {

        log.debug("Init ElectronicBillController {}", orderBillingDTO);

        log.info("JSON ElectronicBillController orderBillingDTO => {}",
                JsonLogUtil.logJson(orderBillingDTO, ElectronicBillController.class));

        log.debug("JSON ElectronicBillController orderBillingDTO => {}",
                JsonLogUtil.logJson(orderBillingDTO, ElectronicBillController.class));

        SatHubResultDto responseCase = sendFiscalElectronicCouponUseCase.invoke(orderBillingDTO);
        ApiResponseDTO<SatHubResultDto> response = new ApiResponseDTO<>();

        response.setData(responseCase);
        return response;
    }

    @Override
    public ApiResponseDTO<SatHubResultDto> sendCancelSaleBillToSefaz(SatCFE satCFE, Long orderId) {
        log.debug("Init sendCancelSaleBillToSefaz orderId {} With SatCFE {} ", orderId, satCFE);

        log.info("JSON ElectronicBillController Cancel Sale orderId => {}",
                JsonLogUtil.logJson(satCFE, ElectronicBillController.class));

        SatHubResultDto responseCase = sendCancelSaleElectronicCouponUseCase.invoke(satCFE, orderId);
        ApiResponseDTO<SatHubResultDto> response = new ApiResponseDTO<>();

        response.setData(responseCase);
        return response;
    }


}
