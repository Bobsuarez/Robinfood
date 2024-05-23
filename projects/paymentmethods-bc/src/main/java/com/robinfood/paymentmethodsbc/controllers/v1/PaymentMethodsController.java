package com.robinfood.paymentmethodsbc.controllers.v1;

import com.robinfood.paymentmethodsbc.annotations.BaseResponse;
import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.controllers.v1.docs.PaymentMethodsDocs;
import com.robinfood.paymentmethodsbc.dto.api.paymentmethods.PaymentMethodDetailsDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.services.PaymentMethodService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@BaseResponse
@RequestMapping("/api/v1/payment-methods")
public class PaymentMethodsController implements PaymentMethodsDocs {
    private final PaymentMethodService paymentMethodService;

    public PaymentMethodsController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @Override
    @BasicLog
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentMethodDetailsDTO> getPaymentMethodsByStoreAndChannelAndOrigin(
        Long storeId,
        Long channelId,
        Long originId
    )
        throws BaseException {
        return paymentMethodService.getPaymentMethodsByStoreAndChannelAndOrigin(
            storeId,
            channelId,
            originId
        );
    }
}
