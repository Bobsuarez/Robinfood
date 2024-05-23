package com.robinfood.app.usecases.getlistchannelsegment;

import com.robinfood.core.dtos.configuration.ChannelDTO;
import com.robinfood.core.dtos.report.salebysegment.ChannelSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.ChannelsDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.ItemDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.OrdersDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.PercentageSalesDifferenceDTOResponse;
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
public class GetChannelBySegmentUseCase implements IGetChannelBySegmentUseCase {

    @Override
    public ChannelsDTOResponse invoke(
            String currencyType,
            List<ChannelSegmentDTO> channelSegmentDTOS,
            List<ChannelDTO> channelDTOList
    ) {

        return ChannelsDTOResponse.builder()
                .items(toBuildListItems(currencyType, channelSegmentDTOS, channelDTOList))
                .total(toBuildTotal(currencyType, channelSegmentDTOS))
                .build();
    }

    private List<ItemDTOResponse> toBuildListItems(
            String currencyType,
            List<ChannelSegmentDTO> channelSegmentDTOS,
            List<ChannelDTO> channelDTOList
    ) {
        return channelSegmentDTOS.stream()
                .map((ChannelSegmentDTO channel) -> {

                    final String nameChannel = getChannelName(channel.getId(), channelDTOList);
                    final OrdersDTOResponse orders = OrdersSegmentToOrdersResponseMappers
                            .ordersResponse(channel.getOrders());

                    return ItemResponseMappers
                            .itemResponseMappers(currencyType, channel.getId(), nameChannel, orders);

                }).collect(Collectors.toList());
    }

    private TotalDTOResponse toBuildTotal(String currencyType, List<ChannelSegmentDTO> channelSegmentDTOS) {

        final BigDecimal sumResultSalesCurrent = sumValueSales(channelSegmentDTOS, DEFAULT_BOOLEAN_FALSE_VALUE);
        final BigDecimal sumResultSaleAWeekBefore = sumValueSales(channelSegmentDTOS, DEFAULT_BOOLEAN_TRUE_VALUE);

        return TotalDTOResponseMappers.totalToResponse(
                currencyType,
                percentageValueSalesCurrent(sumResultSaleAWeekBefore, sumResultSalesCurrent),
                ValueAWeekBeforeToResponseMappers.valueAssignment(sumResultSaleAWeekBefore),
                ValueCurrentToResponseMappers.valueAssignment(sumResultSalesCurrent)
        );
    }

    @NotNull
    private BigDecimal sumValueSales(List<ChannelSegmentDTO> channelSegmentDTOS, boolean isSalesAWeekBefore) {

        // Return sum sales a week before
        if (isSalesAWeekBefore) {
            return channelSegmentDTOS
                    .stream()
                    .map(convertTotal -> convertTotal.getOrders().getSalesAWeekBefore().getValue())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        // Return sum sales current day
        return channelSegmentDTOS
                .stream()
                .map(convertTotal -> convertTotal.getOrders().getSalesCurrent().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @NotNull
    private PercentageSalesDifferenceDTOResponse percentageValueSalesCurrent(
            BigDecimal valueAWeekBefore,
            BigDecimal valueCurrent
    ) {
        final BigDecimal resultCalculate = CalculatorPercentageUtil
                .getPercentageDifference(valueCurrent, valueAWeekBefore);

        return PercentageToResponseMappers.valueAssignment(resultCalculate);
    }

    private static String getChannelName(Long channelId, List<ChannelDTO> channelDTOList) {

        return Optional.ofNullable(channelDTOList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(channelDTO -> channelDTO.getId().equals(channelId.intValue()))
                .map(ChannelDTO::getName)
                .findFirst()
                .orElse(DEFAULT_STRING_VALUE);
    }
}
