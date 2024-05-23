package com.robinfood.app.usecases.getordersstore;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.ordersstore.DataOrderStoreRequestDTO;
import com.robinfood.core.dtos.ordersstore.OrderStoreDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.ordersstore.IOrdersStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetOrdersStoreUseCase implements IGetOrdersStoreUseCase{

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IOrdersStoreRepository ordersStoreRepository;

    public GetOrdersStoreUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IOrdersStoreRepository ordersStoreRepository
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.ordersStoreRepository = ordersStoreRepository;
    }

    @Override
    public Result<List<OrderStoreDTO>> invoke(DataOrderStoreRequestDTO dataOrderStoreRequestDTO) {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        return ordersStoreRepository.getDataOrderStore(dataOrderStoreRequestDTO, token.getAccessToken());
    }
}
