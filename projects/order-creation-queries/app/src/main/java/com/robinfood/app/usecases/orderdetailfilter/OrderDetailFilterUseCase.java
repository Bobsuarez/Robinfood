package com.robinfood.app.usecases.orderdetailfilter;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.orderfilter.IOrderFilterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Implementation of 'IOrderDetailFilterUseCase'
 */
@Service
@Slf4j
public class OrderDetailFilterUseCase implements IOrderDetailFilterUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IOrderFilterRepository orderFilterRepository;

    public OrderDetailFilterUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IOrderFilterRepository orderFilterRepository
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.orderFilterRepository = orderFilterRepository;
    }

    public Result<EntityDTO<OrderDTO>> invoke(
            Integer currentPage,
            String filterText,
            LocalDate localDateEnd,
            LocalDate localDateStart,
            Integer perPage,
            Long storeId,
            String timeZone
    ) {

        var token = getTokenBusinessCapabilityUseCase.invoke();

        return this.orderFilterRepository.getOrderFilter(
                currentPage,
                filterText,
                localDateEnd,
                localDateStart,
                perPage,
                storeId,
                timeZone,
                token.getAccessToken()
        );
    }
}
