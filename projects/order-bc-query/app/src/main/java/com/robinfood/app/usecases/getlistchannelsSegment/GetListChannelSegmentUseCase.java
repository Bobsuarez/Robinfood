package com.robinfood.app.usecases.getlistchannelsSegment;

import com.robinfood.app.mappers.salesbysegment.GetChannelSaleDTOMappers;
import com.robinfood.app.mappers.salesbysegment.GetDataGenericSegmentDTOMappers;
import com.robinfood.app.mappers.salesbysegment.GetOrdersSalesDTOMappers;
import com.robinfood.core.dtos.report.salebysegment.ChannelSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.DataGenericSegmentDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.utilities.OrdersUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class GetListChannelSegmentUseCase implements IGetListChannelSegmentUseCase {

    @Override
    public List<ChannelSegmentDTO> invoke(List<OrderEntity> listOrderCurrent, List<OrderEntity> listOrdersLastWeek) {

        List<ChannelSegmentDTO> segmentDTOList = new ArrayList<>();

        Map<Long, List<OrderEntity>> groupedListCurrent = OrdersUtil
                .groupedMultipleByEntityAttribute(listOrderCurrent, OrderEntity::getOriginId);

        Map<Long, List<OrderEntity>> groupedListLastWeek = OrdersUtil
                .groupedMultipleByEntityAttribute(listOrdersLastWeek, OrderEntity::getOriginId);

        List<Long> channelsIds = OrdersUtil
                .findAndGroupSegmentIds(groupedListCurrent, groupedListLastWeek);

        log.info("The identity is built by idChannel identifier : {}", channelsIds);

        for (Long channelId : channelsIds) {

            BigDecimal getTotalCurrent = OrdersUtil.sumTotalOrders(groupedListCurrent, channelId);
            BigDecimal getTotalLastWeek = OrdersUtil.sumTotalOrders(groupedListLastWeek, channelId);

            DataGenericSegmentDTO dtoWithValueCurrent = GetDataGenericSegmentDTOMappers
                    .valueSaleToDataGenericSegmentDTO(getTotalCurrent);

            DataGenericSegmentDTO dtoWithValueLastWeek = GetDataGenericSegmentDTOMappers
                    .valueSaleToDataGenericSegmentDTO(getTotalLastWeek);

            ChannelSegmentDTO getChannel = GetChannelSaleDTOMappers.channelIdAndOrderSalesSegmentDTOToChannelSegmentDTO(
                    channelId,
                    GetOrdersSalesDTOMappers
                            .valueCurrentAndLastWeekToOrderSalesSegmentDTO(dtoWithValueCurrent, dtoWithValueLastWeek)
            );

            segmentDTOList.add(getChannel);
        }
        return segmentDTOList;
    }
}
