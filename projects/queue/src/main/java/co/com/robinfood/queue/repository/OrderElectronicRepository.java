package co.com.robinfood.queue.repository;

import co.com.robinfood.queue.app.lasting.QueryConstants;
import co.com.robinfood.queue.database.DatabaseManager;
import co.com.robinfood.queue.persistencia.dto.OrderBillingJsonResponseDTO;
import co.com.robinfood.queue.persistencia.entity.OrderElectronicBillingEntity;
import co.com.robinfood.queue.utils.ObjectMapperSingleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static co.com.robinfood.queue.app.lasting.GlobalConstants.DataGlobal.DATE_QUERY;

@Slf4j
public class OrderElectronicRepository extends DatabaseManager implements IOrderElectronicRepository {

    private static OrderElectronicRepository instance;

    public static OrderElectronicRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new OrderElectronicRepository();
        }
        return instance;
    }

    @Override
    public List<OrderBillingJsonResponseDTO> findAllOrdersByStatusCodeAndDateInitialAndDateFinal(String dateToSearch) {

        String queryWithDate = QueryConstants.SEARCH_ALL_ORDERS_BY_RANGE_DATES.replace(DATE_QUERY, dateToSearch);

        OrderElectronicBillingEntity orderElectronicBillingEntity =
                executeQuery(
                        queryWithDate,
                        OrderElectronicBillingEntity.class
                );

        AtomicReference<List<OrderBillingJsonResponseDTO>> dataReturn = new AtomicReference<>();

        Optional.ofNullable(orderElectronicBillingEntity.getInfo())
                .ifPresentOrElse(
                        value -> dataReturn.set(
                                ObjectMapperSingleton.jsonToListClass(value, OrderBillingJsonResponseDTO.class)
                        ), () -> log.info("No data no found query")
                );

        return dataReturn.get();
    }
}
