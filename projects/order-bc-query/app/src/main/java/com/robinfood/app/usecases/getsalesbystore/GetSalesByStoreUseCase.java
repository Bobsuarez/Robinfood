package com.robinfood.app.usecases.getsalesbystore;

import com.google.gson.Gson;
import com.robinfood.app.mappers.salesbystore.GetStoreSaleByStoreDTOMappers;
import com.robinfood.app.usecases.getlistpaymentbystore.IGetListPaymentMethodsByStoreUseCase;
import com.robinfood.app.usecases.getordersquerybysegmentstatement.IGetOrdersQueryBySegmentStatementUseCase;
import com.robinfood.core.dtos.report.salebysegment.DataIdsToFindTheSegment;
import com.robinfood.core.dtos.report.salebystore.GetSaleByStoreResponseDTO;
import com.robinfood.core.dtos.report.salebystore.PaymentMethodsOfStoreDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.utilities.KeyAnyWithTimezonesUtil;
import com.robinfood.core.utilities.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

@Component
@Slf4j
public class GetSalesByStoreUseCase implements IGetSalesByStoreUseCase {

    private final IGetListPaymentMethodsByStoreUseCase getListPaymentMethodsByStoreUseCase;

    private final IGetOrdersQueryBySegmentStatementUseCase orderEntityBuilderJpaSpecification;

    public GetSalesByStoreUseCase(
            IGetListPaymentMethodsByStoreUseCase getListPaymentMethodsByStoreUseCase,
            IGetOrdersQueryBySegmentStatementUseCase orderEntityBuilderJpaSpecification
    ) {
        this.getListPaymentMethodsByStoreUseCase = getListPaymentMethodsByStoreUseCase;
        this.orderEntityBuilderJpaSpecification = orderEntityBuilderJpaSpecification;
    }

    @Override
    public GetSaleByStoreResponseDTO invoke(DataIdsToFindTheSegment toFindTheSegment) {

        List<Integer> idStores = toFindTheSegment.getStoresList().stream()
                .mapToInt(Long::intValue)
                .boxed()
                .collect(Collectors.toList());

        List<String> timezones = List.of(toFindTheSegment.getTimeZone());

        final KeyAnyWithTimezonesUtil keyAnyWithTimezonesUtil = new KeyAnyWithTimezonesUtil(
                idStores,
                timezones
        );

        Map<Integer, ZoneId> storeWithTimezones = keyAnyWithTimezonesUtil.withZoneId();

        log.info("Store with timezones: {}", storeWithTimezones);

        Map<Integer, LocalDateTime> storesWithLocaltime = keyAnyWithTimezonesUtil.withLocalTimeByZoneIds(
                toFindTheSegment.getDateToSearch(),
                storeWithTimezones
        );

        log.info(
                "Start of the process that obtains the orders by week ago: {} with store list  {}",
                new Gson().toJson(toFindTheSegment.getStoresList()), storesWithLocaltime
        );

        List<PaymentMethodsOfStoreDTO> getPaymentList = new ArrayList<>();

        for (Long idStore : toFindTheSegment.getStoresList()) {

            final LocalDateTime dateByStore = storesWithLocaltime.get(idStore.intValue());

            final LocalDateTime dateLastTimeWeek = new LocalDateTimeUtil(dateByStore).lastOfWeekSameHour();

            log.info("Start of the process that obtains the orders by week ago: {} with store ids list  {}",
                    dateLastTimeWeek, new Gson().toJson(toFindTheSegment));

            final List<OrderEntity> orderEntityListCurrent =
                    orderEntityBuilderJpaSpecification.invoke(
                            dateByStore,
                            toFindTheSegment
                    );

            final List<OrderEntity> orderEntityListLastTimeWeek =
                    orderEntityBuilderJpaSpecification.invoke(dateLastTimeWeek, toFindTheSegment);

            log.info(" Invoke list size orderEntityListCurrent : {}", orderEntityListCurrent.size());
            log.info(" Invoke list size orderEntityListLastTimeWeek : {}", orderEntityListLastTimeWeek.size());

            getPaymentList = getListPaymentMethodsByStoreUseCase
                    .invoke(orderEntityListCurrent, orderEntityListLastTimeWeek);
        }

        return GetStoreSaleByStoreDTOMappers.storeDTOListToGetSaleByStoreResponseDTO(
                toFindTheSegment.getStoresList().get(DEFAULT_INTEGER_VALUE).longValue(),
                getPaymentList
        );
    }
}
