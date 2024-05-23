package com.robinfood.app.usecases.getordersquerybysegmentstatement;

import com.robinfood.app.mappers.SearchToCriteriaMapper;
import com.robinfood.app.strategies.querystatements.context.SearchQueryStrategyContext;
import com.robinfood.app.strategies.querystatements.helper.AndPredicateStrategy;
import com.robinfood.app.strategies.querystatements.helper.BetweenTimeZeroToValuePredicateStrategy;
import com.robinfood.app.strategies.querystatements.helper.InPredicateStrategy;
import com.robinfood.app.strategies.querystatements.helper.JoinOrderPaymentPredicateStrategy;
import com.robinfood.app.strategies.querystatements.helper.NotInPredicateStrategy;
import com.robinfood.core.dtos.report.salebysegment.DataIdsToFindTheSegment;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.ORDER_PAID;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_BRAND;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_COMPANY;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_LOCAL_DATE;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_LOCAL_TIME;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_ORIGIN;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_PAID;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_STATUS;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_STORE;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDER_PAYMENT_METHODS;

@Component
public class GetOrdersQueryBySegmentStatementUseCase implements IGetOrdersQueryBySegmentStatementUseCase {

    private final IOrdersRepository ordersRepository;

    @Value("#{'${pos.orders.status-cancelled}'.split(',')}")
    private List<Long> IDS_ORDERS_STATUS_CANCELLED_AND_DISCARDED;

    public GetOrdersQueryBySegmentStatementUseCase(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<OrderEntity> invoke(LocalDateTime dateToSearch, DataIdsToFindTheSegment dataRequestDTO) {

        SearchQueryStrategyContext<OrderEntity> searchQueryStrategyContext = new SearchQueryStrategyContext<>();

        searchQueryStrategyContext.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDERS_BRAND, dataRequestDTO.getBrandsList()),
                dataRequestDTO.getBrandsList(),
                new InPredicateStrategy<>());

        searchQueryStrategyContext.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDERS_STORE, dataRequestDTO.getStoresList()),
                dataRequestDTO.getStoresList(),
                new InPredicateStrategy<>());

        searchQueryStrategyContext.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDERS_COMPANY, dataRequestDTO.getCompaniesList()),
                dataRequestDTO.getCompaniesList(),
                new InPredicateStrategy<>());

        searchQueryStrategyContext.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDERS_ORIGIN, dataRequestDTO.getChannelsList()),
                dataRequestDTO.getChannelsList(),
                new InPredicateStrategy<>());

        searchQueryStrategyContext.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDERS_LOCAL_DATE, dateToSearch.toLocalDate()),
                dataRequestDTO.getDateToSearch().toLocalDate(),
                new AndPredicateStrategy<>());

        searchQueryStrategyContext.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDERS_LOCAL_TIME, dateToSearch.toLocalTime()),
                dataRequestDTO.getDateToSearch().toLocalTime(),
                new BetweenTimeZeroToValuePredicateStrategy<>());

        searchQueryStrategyContext.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDERS_STATUS, IDS_ORDERS_STATUS_CANCELLED_AND_DISCARDED),
                IDS_ORDERS_STATUS_CANCELLED_AND_DISCARDED,
                new NotInPredicateStrategy<>());

        searchQueryStrategyContext.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDERS_PAID, ORDER_PAID),
                ORDER_PAID,
                new AndPredicateStrategy<>());

        searchQueryStrategyContext.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDER_PAYMENT_METHODS,
                                dataRequestDTO.getPaymentsMethodsList()),
                dataRequestDTO.getPaymentsMethodsList(),
                new JoinOrderPaymentPredicateStrategy<>());

        return ordersRepository.findAll(searchQueryStrategyContext);
    }
}
