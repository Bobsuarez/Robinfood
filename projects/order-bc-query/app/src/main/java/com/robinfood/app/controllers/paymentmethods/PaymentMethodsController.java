package com.robinfood.app.controllers.paymentmethods;

import com.robinfood.app.usecases.getlistpaymentmethods.IGetListPaymentMethodsUseCase;
import com.robinfood.core.dtos.PaymentMethodEntityDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.robinfood.core.constants.APIConstants.PAYMENT_METHODS_V1;

@RestController
@RequestMapping(PAYMENT_METHODS_V1)
public class PaymentMethodsController implements IPaymentMethodsController {

    private final IGetListPaymentMethodsUseCase getListPaymentMethods;

    public PaymentMethodsController(IGetListPaymentMethodsUseCase getListPaymentMethods) {
        this.getListPaymentMethods = getListPaymentMethods;
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<PaymentMethodEntityDTO>>> invoke() {

        return ResponseEntity.ok(new ApiResponseDTO<>(getListPaymentMethods.invoke()));
    }
}
