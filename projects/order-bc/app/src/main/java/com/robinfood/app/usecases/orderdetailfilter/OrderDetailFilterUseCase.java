package com.robinfood.app.usecases.orderdetailfilter;

import com.robinfood.app.mappers.OrderMappers;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.utilities.CalculateDateDisplacementUTC;
import com.robinfood.repository.orders.IOrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;

/**
 * Implementation of 'IOrderDetailFilterUseCase'
 */
@Service
@Slf4j
public class OrderDetailFilterUseCase implements IOrderDetailFilterUseCase {

    private final IOrdersRepository ordersRepository;

    public OrderDetailFilterUseCase(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Page<OrderDTO> invoke(
            Integer currentPage,
            String filterText,
            LocalDate localDateEnd,
            LocalDate localDateStart,
            Integer perPage,
            Long storeId,
            String timeZone
    ) {

        final ZoneId zoneId = CalculateDateDisplacementUTC.getZoneId(timeZone);

        LocalDateTime localDateTimeStart = CalculateDateDisplacementUTC.getLocalDateTimeStart(
                localDateStart, zoneId
        );

        LocalDateTime localDateTimeEnd = CalculateDateDisplacementUTC.getLocalDateTimeEnd(
                localDateEnd, zoneId
        );

        Page<OrderEntity> orderEntities = this.ordersRepository
                .findByStoreIdAndOrderNumberAndOrderInvoiceNumberContainingCreatedAtBetweenOrderByCreatedAtDesc(
                        localDateTimeEnd,
                        localDateTimeStart,
                        PageRequest.of(currentPage - DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT, perPage),
                        filterText,
                        filterText,
                        storeId
                );

        return orderEntities.map(OrderMappers::toOrderDTO);
    }

}
