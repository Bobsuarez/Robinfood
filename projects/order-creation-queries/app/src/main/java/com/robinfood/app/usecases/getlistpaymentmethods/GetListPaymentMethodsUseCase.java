package com.robinfood.app.usecases.getlistpaymentmethods;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.paymentmethods.IPaymentMethodsRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetListPaymentMethodsUseCase implements IGetListPaymentMethodsUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    private final IPaymentMethodsRepository paymentMethodsRepository;

    public GetListPaymentMethodsUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IPaymentMethodsRepository paymentMethodsRepository
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.paymentMethodsRepository = paymentMethodsRepository;
    }

    @Override
    public Result<List<PaymentMethodsFilterDTO>> invoke() {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();
        return paymentMethodsRepository.getDataPaymentMethods(token.getAccessToken());
    }

}
