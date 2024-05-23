package com.robinfood.app.usecases.getstoreorders;

import com.robinfood.app.mappers.storeorder.StoreOrderMappers;
import com.robinfood.core.dtos.storeorder.StoreOrderDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.StatusEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.core.utilities.CalculateDateDisplacementUTCUtil;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.status.IStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_START;
import static com.robinfood.core.constants.GlobalConstants.ORDER_PAID;

/**
 * Implementation of IGetStoreOrdersUseCase
 */
@Service
@Slf4j
public class GetStoreOrdersUseCase implements IGetStoreOrdersUseCase {

    private final IOrdersRepository ordersRepository;

    private final IStatusRepository statusRepository;

    public GetStoreOrdersUseCase(
            IOrdersRepository ordersRepository,
            IStatusRepository statusRepository
    ) {
        this.ordersRepository = ordersRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public List<StoreOrderDTO> invoke(
            LocalDate localDateEnd,
            LocalDate localDateStart,
            Long storeId,
            String timeZone
    ) {
        log.info("Start of the process get store orders: {}", storeId);

        final Map<String, LocalDateTime> localDateTimeZone = CalculateDateDisplacementUTCUtil.getLocalDateByRange(
                localDateEnd,
                localDateStart,
                timeZone
        );

        final List<OrderEntity> orderList = ordersRepository.findByCreatedAtBetweenAndPaidAndStoreIdOrderByCreatedAtAsc(
                localDateTimeZone.get(LOCAL_DATE_START),
                localDateTimeZone.get(LOCAL_DATE_END),
                ORDER_PAID,
                storeId
        ).orElseThrow(() -> new GenericOrderBcException("Orders no found"));

        final List<StatusEntity> statusEntities = statusRepository.findAll();

        return orderList.stream()
                .map(order -> StoreOrderMappers.orderEntityToStoreOrderDTO(order, statusEntities))
                .collect(Collectors.toList());
    }
}
