package com.robinfood.app.usecases.getuserorderhistory;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.userorderhistory.IUserOrderHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of IGetOrderHistoryUseCase
 */
@Service
@Slf4j
public class GetUserOrderHistoryUseCase implements IGetUserOrderHistoryUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IUserOrderHistoryRepository userOrderHistoryRepository;

    public GetUserOrderHistoryUseCase(IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IUserOrderHistoryRepository userOrderHistoryRepository) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.userOrderHistoryRepository = userOrderHistoryRepository;
    }

    @Override
    public Result<EntityDTO<ResponseOrderDTO>> invoke(
            Integer currentPage,
            Integer perPage,
            Long userId
    ) {

        var token = getTokenBusinessCapabilityUseCase.invoke();

        log.info("Get orders history by user repository");
        return userOrderHistoryRepository.getOrderHistory(
            currentPage,
            perPage,
            token.getAccessToken(),
            userId
        );
    }
}
