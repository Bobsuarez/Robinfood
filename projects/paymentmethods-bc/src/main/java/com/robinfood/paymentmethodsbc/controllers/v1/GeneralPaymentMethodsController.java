package com.robinfood.paymentmethodsbc.controllers.v1;

import com.robinfood.paymentmethodsbc.annotations.BaseResponse;
import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.controllers.v1.docs.GeneralPaymentMethodsDocs;
import com.robinfood.paymentmethodsbc.dto.api.paymentmethods.GeneralPaymentMethodDetailsDTO;
import com.robinfood.paymentmethodsbc.services.GeneralPaymentMethodService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@BaseResponse
@RestController
@RequestMapping("/api/v1/paymentmethods")
public class GeneralPaymentMethodsController implements GeneralPaymentMethodsDocs {
    private final GeneralPaymentMethodService generalPaymentMethodService;

    public GeneralPaymentMethodsController(GeneralPaymentMethodService generalPaymentMethodService) {
        this.generalPaymentMethodService = generalPaymentMethodService;
    }

    @Override
    @BasicLog
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GeneralPaymentMethodDetailsDTO> getPaymentMethodsByStoreChannelFlowAndTerminal(
        final Long storeId,
        final Long channelId,
        final Long flowId,
        final String terminalUuid
    ) {
        return generalPaymentMethodService.getPaymentMethodsByStoreChannelFlowAndTerminal(
            storeId,
            channelId,
            flowId,
            terminalUuid
        );
    }
}
