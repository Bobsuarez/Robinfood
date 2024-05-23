package com.robinfood.app.usecases.getliststoresegment;

import com.robinfood.core.dtos.configuration.StoreWithIdAndNameDTO;
import com.robinfood.core.dtos.report.salebysegment.StoreSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.ItemDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.OrdersDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.PercentageSalesDifferenceDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.StoresDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.TotalDTOResponse;
import com.robinfood.core.mappers.report.salesegment.ItemResponseMappers;
import com.robinfood.core.mappers.report.salesegment.OrdersSegmentToOrdersResponseMappers;
import com.robinfood.core.mappers.report.salesegment.PercentageToResponseMappers;
import com.robinfood.core.mappers.report.salesegment.TotalDTOResponseMappers;
import com.robinfood.core.mappers.report.salesegment.ValueAWeekBeforeToResponseMappers;
import com.robinfood.core.mappers.report.salesegment.ValueCurrentToResponseMappers;
import com.robinfood.core.utilities.CalculatorPercentageUtil;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_FALSE_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;

@Service
public class GetStoreBySegmentUseCase implements IGetStoreBySegmentUseCase {

    @Override
    public StoresDTOResponse invoke(
            String currencyType,
            List<StoreSegmentDTO> storeSegmentDTOS,
            List<StoreWithIdAndNameDTO> storeDTOList
    ) {

        return StoresDTOResponse.builder()
                .items(toBuildListItems(currencyType, storeSegmentDTOS, storeDTOList))
                .total(toBuildTotal(currencyType, storeSegmentDTOS))
                .build();
    }

    private List<ItemDTOResponse> toBuildListItems(
            String currencyType,
            List<StoreSegmentDTO> storeSegmentDTOS,
            List<StoreWithIdAndNameDTO> storeDTOList
    ) {
        return storeSegmentDTOS.stream()
                .map((StoreSegmentDTO store) -> {

                    final String nameStore = getStoreName(store.getId(), storeDTOList);
                    final OrdersDTOResponse orders = OrdersSegmentToOrdersResponseMappers
                            .ordersResponse(store.getOrders());

                    return ItemResponseMappers
                            .itemResponseMappers(currencyType, store.getId(), nameStore, orders);

                }).collect(Collectors.toList());
    }

    private TotalDTOResponse toBuildTotal(String currencyType, List<StoreSegmentDTO> storeSegmentDTO) {

        final BigDecimal sumResultSalesCurrent = sumValueSales(DEFAULT_BOOLEAN_FALSE_VALUE, storeSegmentDTO);
        final BigDecimal sumResultSaleAWeekBefore = sumValueSales(DEFAULT_BOOLEAN_TRUE_VALUE, storeSegmentDTO);

        return TotalDTOResponseMappers.totalToResponse(
                currencyType,
                percentageValueSalesCurrent(sumResultSaleAWeekBefore, sumResultSalesCurrent),
                ValueAWeekBeforeToResponseMappers.valueAssignment(sumResultSaleAWeekBefore),
                ValueCurrentToResponseMappers.valueAssignment(sumResultSalesCurrent)
        );
    }

    @NotNull
    private BigDecimal sumValueSales(boolean isSalesAWeekBefore, List<StoreSegmentDTO> storeSegmentDTOS) {

        // Return sum sales a week before
        if (isSalesAWeekBefore) {
            return storeSegmentDTOS
                    .stream()
                    .map(convertTotal -> convertTotal.getOrders().getSalesAWeekBefore().getValue())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        // Return sum sales current day
        return storeSegmentDTOS
                .stream()
                .map(convertTotal -> convertTotal.getOrders().getSalesCurrent().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @NotNull
    private PercentageSalesDifferenceDTOResponse percentageValueSalesCurrent(
            BigDecimal valueAWeekBefore,
            BigDecimal valueCurrent
    ) {
        BigDecimal result = CalculatorPercentageUtil.getPercentageDifference(valueCurrent, valueAWeekBefore);
        return PercentageToResponseMappers.valueAssignment(result);
    }

    private static String getStoreName(Long storeId, List<StoreWithIdAndNameDTO> storeDTOList) {

        return Optional.ofNullable(storeDTOList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(store -> store.getId().equals(storeId))
                .map(StoreWithIdAndNameDTO::getName)
                .findFirst()
                .orElse(DEFAULT_STRING_VALUE);
    }
}
