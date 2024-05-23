package com.robinfood.app.usecases.getorderpayment;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.orderpayment.DataOrderPaymentRequestDTO;
import com.robinfood.core.dtos.orderpayment.OrderPaymentDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.orderpayment.IOrderPaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetOrderPaymentUseCase implements IGetOrderPaymentUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IOrderPaymentRepository iOrderPaymentRepository;

    public GetOrderPaymentUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IOrderPaymentRepository iOrderPaymentRepository
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.iOrderPaymentRepository = iOrderPaymentRepository;
    }

    @Override
    public Result<List<OrderPaymentDTO>> invoke(DataOrderPaymentRequestDTO dataRequestDTO) {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        return iOrderPaymentRepository.getDataOrderPayment(dataRequestDTO, token.getAccessToken());
    }
}
