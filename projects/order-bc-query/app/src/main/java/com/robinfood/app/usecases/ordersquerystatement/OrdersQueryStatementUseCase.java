package com.robinfood.app.usecases.ordersquerystatement;

import com.robinfood.app.enums.CustomFiltersEnum;
import com.robinfood.app.enums.StatusOrderCancellationEnum;
import com.robinfood.app.mappers.SearchToCriteriaMapper;
import com.robinfood.app.strategies.orderfiltercancellation.context.ProcessFilterContext;
import com.robinfood.app.strategies.orderfiltercancellation.helper.ClientMobileStrategy;
import com.robinfood.app.strategies.orderfiltercancellation.helper.ClientNameStrategy;
import com.robinfood.app.strategies.orderfiltercancellation.helper.InvoiceNumberStrategy;
import com.robinfood.app.strategies.orderfiltercancellation.helper.OrderIntegrationStrategy;
import com.robinfood.app.strategies.orderfiltercancellation.helper.OrderNumberStrategy;
import com.robinfood.app.strategies.orderfiltercancellation.helper.OrderUuidStrategy;
import com.robinfood.app.strategies.querystatements.context.SearchQueryStrategyContext;
import com.robinfood.app.strategies.querystatements.helper.AndPredicateStrategy;
import com.robinfood.app.strategies.querystatements.helper.BetweenDateRangePredicateStrategy;
import com.robinfood.app.strategies.querystatements.helper.EqualPredicateStrategy;
import com.robinfood.app.strategies.querystatements.helper.InPredicateStrategy;
import com.robinfood.app.strategies.querystatements.helper.JoinOrderPaymentPredicateStrategy;
import com.robinfood.core.dtos.report.ordercancellation.DataToSearchIdsCanceledOrdersDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static com.robinfood.core.constants.GlobalConstants.ORDER_PAID;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_BRAND;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_COMPANY;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_LOCAL_DATE;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_ORIGIN;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_PAID;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_STATUS;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_STORE;
import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDER_PAYMENT_METHODS;

@Component
public class OrdersQueryStatementUseCase implements IOrdersQueryStatementUseCase {

    private final IOrdersRepository ordersRepository;

    public OrdersQueryStatementUseCase(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Page<OrderEntity> invoke(DataToSearchIdsCanceledOrdersDTO dataRequestDTO) {

        SearchQueryStrategyContext<OrderEntity> searchQueryStrategyContext = new SearchQueryStrategyContext<>();

        ProcessFilterContext<OrderEntity> filterContextNumberBill = new ProcessFilterContext<>(new InvoiceNumberStrategy<>());

        buildDefaultParameters(searchQueryStrategyContext, dataRequestDTO);

        filterContextNumberBill.processFilter(
                dataRequestDTO.getIdCustomFilterDTO(),
                CustomFiltersEnum.NUMBER_BILL.getCode(),
                searchQueryStrategyContext,
                dataRequestDTO.getValueCustomFilterDTO()
        );

        ProcessFilterContext<OrderEntity> filterContextOrderNumber = new ProcessFilterContext<>(new OrderNumberStrategy<>());

        filterContextOrderNumber.processFilter(
                dataRequestDTO.getIdCustomFilterDTO(),
                CustomFiltersEnum.ORDER_NUMBER.getCode(),
                searchQueryStrategyContext,
                dataRequestDTO.getValueCustomFilterDTO()
        );

        ProcessFilterContext<OrderEntity> filterContextOrderUUID = new ProcessFilterContext<>(new OrderUuidStrategy<>());

        filterContextOrderUUID.processFilter(
                dataRequestDTO.getIdCustomFilterDTO(),
                CustomFiltersEnum.ORDER_UUID.getCode(),
                searchQueryStrategyContext,
                dataRequestDTO.getValueCustomFilterDTO()
        );

        ProcessFilterContext<OrderEntity> filterContextClientName = new ProcessFilterContext<>(new ClientNameStrategy<>());

        filterContextClientName.processFilter(
                dataRequestDTO.getIdCustomFilterDTO(),
                CustomFiltersEnum.CLIENT_NAME.getCode(),
                searchQueryStrategyContext,
                dataRequestDTO.getValueCustomFilterDTO()
        );

        ProcessFilterContext<OrderEntity> filterContextClientMobile = new ProcessFilterContext<>(new ClientMobileStrategy<>());

        filterContextClientMobile.processFilter(
                dataRequestDTO.getIdCustomFilterDTO(),
                CustomFiltersEnum.CLIENT_MOBILE.getCode(),
                searchQueryStrategyContext,
                dataRequestDTO.getValueCustomFilterDTO()
        );

        ProcessFilterContext<OrderEntity> filterContextOrderIntegration = new ProcessFilterContext<>(new OrderIntegrationStrategy<>());

        filterContextOrderIntegration.processFilter(
                dataRequestDTO.getIdCustomFilterDTO(),
                CustomFiltersEnum.ORDER_INTEGRATION.getCode(),
                searchQueryStrategyContext,
                dataRequestDTO.getValueCustomFilterDTO()
        );

        return ordersRepository.findAll(searchQueryStrategyContext,
                PageRequest.of(
                        dataRequestDTO.getCurrentPageDTO() - DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT,
                        dataRequestDTO.getPerPageDTO()
                ));
    }

    private void buildDefaultParameters(
            SearchQueryStrategyContext<OrderEntity> searchSpecificationsQueryDTO,
            DataToSearchIdsCanceledOrdersDTO dataRequestDTO
    ) {
        searchSpecificationsQueryDTO.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDERS_BRAND, dataRequestDTO.getBrandsIdsDTO()),
                dataRequestDTO.getBrandsIdsDTO(),
                new InPredicateStrategy<>());

        searchSpecificationsQueryDTO.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDERS_COMPANY, dataRequestDTO.getCompanyIdDTO()),
                dataRequestDTO.getCompanyIdDTO(),
                new EqualPredicateStrategy<>());

        searchSpecificationsQueryDTO.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDERS_STORE, dataRequestDTO.getStoresIdsDTO()),
                dataRequestDTO.getStoresIdsDTO(),
                new InPredicateStrategy<>());

        searchSpecificationsQueryDTO.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDERS_ORIGIN, dataRequestDTO.getOriginIdsDTO()),
                dataRequestDTO.getOriginIdsDTO(),
                new InPredicateStrategy<>());

        searchSpecificationsQueryDTO.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDERS_LOCAL_DATE,
                                List.of(dataRequestDTO.getLocalDateStartDTO(), dataRequestDTO.getLocalDateEndDTO())),
                dataRequestDTO.getLocalDateStartDTO(),
                new BetweenDateRangePredicateStrategy<>());

        searchSpecificationsQueryDTO.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDERS_STATUS,
                                StatusOrderCancellationEnum.parseToArrayStatus(dataRequestDTO.getStatusCodeDTO())),
                dataRequestDTO.getStatusCodeDTO(),
                new InPredicateStrategy<>());

        searchSpecificationsQueryDTO.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDERS_PAID, ORDER_PAID),
                ORDER_PAID,
                new AndPredicateStrategy<>());

        searchSpecificationsQueryDTO.add(
                SearchToCriteriaMapper
                        .buildDataSearch(ORDER_PAYMENT_METHODS,
                                dataRequestDTO.getPaymentMethodIdsDTO()),
                dataRequestDTO.getPaymentMethodIdsDTO(),
                new JoinOrderPaymentPredicateStrategy<>());
    }
}
