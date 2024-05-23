package com.robinfood.app.usecases.getorderhistory;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderhistory.request.OrderHistoryRequestDTO;
import com.robinfood.core.dtos.orderhistory.response.OrderHistoryItemDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.orderhistory.IOrderHistoryRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of IGetOrderHistoryUseCase
 */
@Slf4j
public class GetOrderHistoryUseCase implements IGetOrderHistoryUseCase {

    private final IOrderHistoryRepository orderHistoryRepository;
    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    public GetOrderHistoryUseCase(
            IOrderHistoryRepository orderHistoryRepository,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase
    ) {
        this.orderHistoryRepository = orderHistoryRepository;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
    }

    @Override
    public Result<EntityDTO<OrderHistoryItemDTO>> invoke(OrderHistoryRequestDTO orderHistoryRequestDTO) {

        var token = getTokenBusinessCapabilityUseCase.invoke();

        return orderHistoryRepository.getOrderHistory(
                orderHistoryRequestDTO,
                token.getAccessToken()
        );
    }
}
