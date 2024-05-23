package com.robinfood.app.usecases.getdailysalessummary;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.OrderDailySaleSummaryDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.orderdailysalessummary.IOrderDailySalesSummaryRepository;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetDailySalesSummaryByStoreIdAndDateUseCase implements IGetDailySalesSummaryByStoreIdAndDateUseCase {

    private final IOrderDailySalesSummaryRepository orderDailySalesSummaryRepository;
    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    public GetDailySalesSummaryByStoreIdAndDateUseCase(
            IOrderDailySalesSummaryRepository orderDailySalesSummaryRepository,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase) {
        this.orderDailySalesSummaryRepository = orderDailySalesSummaryRepository;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
    }

    /**
     * Get daily sales summary by store and specific date
     *
     * @param storeId   the store id to order
     * @param createdAt the date of creation to order
     *
     * @return an object of OrderDailySaleSummaryDTO
     * @author Jose Mario Londoño - CKS
     */
    @Override
    public Result<OrderDailySaleSummaryDTO> invoke(Long storeId, LocalDate createdAt) {
        log.info("Starting process to get total daily sales by storeId = [{}], createdAt = [{}] ", storeId, createdAt);
        var token = getTokenBusinessCapabilityUseCase.invoke();
        return orderDailySalesSummaryRepository.getOrderDailySalesSummary(token.getAccessToken(), storeId, createdAt);
    }

}
