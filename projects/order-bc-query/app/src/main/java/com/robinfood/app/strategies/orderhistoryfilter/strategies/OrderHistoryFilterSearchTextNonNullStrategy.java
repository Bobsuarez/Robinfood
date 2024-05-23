package com.robinfood.app.strategies.orderhistoryfilter.strategies;

import com.robinfood.app.usecases.getusersbyfullnamelike.IGetUsersByFullNameLikeAndLocalDateUseCase;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.dtos.request.orderhistory.OrderHistoryRequestDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_START;
import static com.robinfood.core.constants.GlobalConstants.ORDER_PAID;

/**
 * Implementation of IOrderHistoryFilterStrategy
 */
@Component
public class OrderHistoryFilterSearchTextNonNullStrategy implements IOrderHistoryFilterStrategy {

    private final IGetUsersByFullNameLikeAndLocalDateUseCase getUsersByFullNameLikeAndLocalDate;

    private final IOrdersRepository ordersRepository;

    public OrderHistoryFilterSearchTextNonNullStrategy(
            IGetUsersByFullNameLikeAndLocalDateUseCase getUsersByFullNameLikeAndLocalDate,
            IOrdersRepository ordersRepository
    ) {
        this.ordersRepository = ordersRepository;
        this.getUsersByFullNameLikeAndLocalDate = getUsersByFullNameLikeAndLocalDate;
    }

    @Override
    public Page<OrderEntity> execute(
            List<Long> deliveryTypeIds,
            Map<String, LocalDateTime> localDateTimeMap,
            OrderHistoryRequestDTO orderHistoryRequestDTO
    ) {

        final List<UserDataDTO> userDataDTOS = getUsersByFullNameLikeAndLocalDate.invoke(
                orderHistoryRequestDTO.getSearchText(),
                orderHistoryRequestDTO.getSearchText(),
                localDateTimeMap,
                orderHistoryRequestDTO.getStoreId()
        );

        List<Long> orderIds = userDataDTOS.stream()
                .map(UserDataDTO::getOrderId)
                .distinct()
                .collect(Collectors.toList());

        return ordersRepository
                .findAllByFilterLikeBrandNameAndOrderInvoiceNumberCreatedAtBetweenOrderByCreatedAtDesc(
                        deliveryTypeIds,
                        localDateTimeMap.get(LOCAL_DATE_TIME_START),
                        localDateTimeMap.get(LOCAL_DATE_TIME_END),
                        orderIds,
                        ORDER_PAID,
                        PageRequest.of(
                                orderHistoryRequestDTO.getCurrentPage() - 1,
                                orderHistoryRequestDTO.getPerPage()
                        ),
                        orderHistoryRequestDTO.getSearchText(),
                        orderHistoryRequestDTO.getStoreId()
                );
    }

}
