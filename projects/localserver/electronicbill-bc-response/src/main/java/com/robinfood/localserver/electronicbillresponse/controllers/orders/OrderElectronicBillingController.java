package com.robinfood.localserver.electronicbillresponse.controllers.orders;

import com.robinfood.localserver.commons.dtos.electronicbill.RequestElectronicBillingDTO;
import com.robinfood.localserver.commons.dtos.http.ApiResponseDTO;
import com.robinfood.localserver.electronicbillresponse.usecase.createelectronicbilling.ICreateOrderElectronicBillUseCase;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service("ElectronicBillingResponseController")
public class OrderElectronicBillingController implements IOrderElectronicBillingController {

    private final ICreateOrderElectronicBillUseCase createOrderElectronicBillUseCase;

    public OrderElectronicBillingController(ICreateOrderElectronicBillUseCase createOrderElectronicBillUseCase) {
        this.createOrderElectronicBillUseCase = createOrderElectronicBillUseCase;
    }

    @Override
    public ApiResponseDTO<Boolean> createOrderElectronicBilling(
            @Valid @RequestBody() final RequestElectronicBillingDTO requestElectronicBillingDTO) {

        log.debug("Init OrderElectronicBillingController {}", requestElectronicBillingDTO);

        Boolean responseElectronicBillingDTO = createOrderElectronicBillUseCase.invoke(requestElectronicBillingDTO);

        ApiResponseDTO<Boolean> response = new ApiResponseDTO<>();
        response.setData(responseElectronicBillingDTO);

        return response;
    }

}
