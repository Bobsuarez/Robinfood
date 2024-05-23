package com.robinfood.app.usecases.getpaymentmethod;

import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;

import static kotlin.collections.CollectionsKt.firstOrNull;

/**
 * Implementation of IGetPaymentMethodFoodCoinsUseCase
 */
@RefreshScope
@Service
@Slf4j
public class GetPaymentMethodFoodCoinsUseCase implements IGetPaymentMethodFoodCoinsUseCase {

    @Value("${payment.method-foodcoins-id}")
    private Long foodCoinsPaymentMethodId;

    public GetPaymentMethodFoodCoinsUseCase() {
        //Constructor empty
    }

    public PaymentMethodDTO invoke(List<PaymentMethodDTO> paymentMethodDTOS) {

        return firstOrNull(
                paymentMethodDTOS,
                (PaymentMethodDTO paymentMethod) ->
                        paymentMethod.getId().equals(foodCoinsPaymentMethodId)
        );
    }
}
