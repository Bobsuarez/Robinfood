package com.robinfood.app.usecases.getdailysalessummary;

import com.robinfood.core.dtos.OrderDailySaleSummaryDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.ORDER_PAID;
import static com.robinfood.core.constants.GlobalConstants.ORDER_STATUS_CANCELLED;
import static com.robinfood.core.constants.GlobalConstants.ORDER_STATUS_RECOIL;

@Component
@Slf4j
public class GetDailySalesSummaryByStoreIdAndDateUseCase implements IGetDailySalesSummaryByStoreIdAndDateUseCase {

    private final IOrdersRepository ordersRepository;

    public GetDailySalesSummaryByStoreIdAndDateUseCase(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    /**
     * Get daily sales summary by store and specific date
     *
     * @param storeId   the store id to order
     * @param createdAt the date of creation to order
     * @return an object of OrderDailySaleSummaryDTO
     * @author Jose Mario Londoño - CKS
     */
    @Override
    public OrderDailySaleSummaryDTO invoke(Long storeId, LocalDate createdAt) {
        log.info("Starting process to get total daily sales by storeId = [{}], createdAt = [{}] ", storeId, createdAt);

        List<OrderEntity> orderDailySales =
            ordersRepository.findAllByStoreIdAndOperationDateAndPaidAndStatusIdNotIn(
                storeId,
                createdAt,
                ORDER_PAID,
                List.of(ORDER_STATUS_CANCELLED, ORDER_STATUS_RECOIL));

        log.info("Orders found {}", orderDailySales);

        final OrderDailySaleSummaryDTO dailySaleSummary = OrderDailySaleSummaryDTO.builder()
            .salesSummary(orderDailySales.stream().mapToDouble((OrderEntity orderDailySale) ->
                    orderDailySale.getTotal() + orderDailySale.getCo2Total().doubleValue()).sum())
            .ordersNumber(orderDailySales.size())
            .build();

        log.info("Orders daily sales summary response {}", dailySaleSummary);

        return dailySaleSummary;
    }

}
