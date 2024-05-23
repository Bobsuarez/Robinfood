package com.robinfood.app.usecases.getuseractiveorder;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.useractiveorder.IUserActiveOrderRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of IGetUserActiveOrderUseCase
 */
@Service
@Slf4j
public class GetUserActiveOrderUseCase implements IGetUserActiveOrderUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IUserActiveOrderRepository userActiveOrderRepository;

    public GetUserActiveOrderUseCase(IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IUserActiveOrderRepository userActiveOrderRepository) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.userActiveOrderRepository = userActiveOrderRepository;
    }

    @Override
    public Result<List<ResponseOrderDTO>> invoke(
            Long userId
    ) {
        var token = getTokenBusinessCapabilityUseCase.invoke();

        log.info("Get active orders by user repository");
        return userActiveOrderRepository.getActiveOrders(
                token.getAccessToken(),
            userId
        );
    }
}
