package com.robinfood.app.usecases.getliststoreSegment;

import com.robinfood.app.mappers.salesbysegment.GetDataGenericSegmentDTOMappers;
import com.robinfood.app.mappers.salesbysegment.GetOrdersSalesDTOMappers;
import com.robinfood.app.mappers.salesbysegment.GetStoreSaleDTOMappers;
import com.robinfood.core.dtos.report.salebysegment.DataGenericSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.StoreSegmentDTO;
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
public class GetListStoreSegmentUseCase implements IGetListStoreSegmentUseCase {

    @Override
    public List<StoreSegmentDTO> invoke(List<OrderEntity> listOrdersCurrent, List<OrderEntity> listOrdersLastWeek) {

        List<StoreSegmentDTO> segmentDTOList = new ArrayList<>();

        Map<Long, List<OrderEntity>> groupedListCurrent = OrdersUtil
                .groupedMultipleByEntityAttribute(listOrdersCurrent, OrderEntity::getStoreId);

        Map<Long, List<OrderEntity>> groupedListLastWeek = OrdersUtil
                .groupedMultipleByEntityAttribute(listOrdersLastWeek, OrderEntity::getStoreId);

        List<Long> toStoreIds = OrdersUtil
                .findAndGroupSegmentIds(groupedListCurrent, groupedListLastWeek);

        log.info("The identity is built by storeId identifier : {}", toStoreIds);

        for (Long storeId : toStoreIds) {

            BigDecimal getTotalCurrent = OrdersUtil
                    .sumTotalOrders(groupedListCurrent, storeId);

            BigDecimal getTotalLastWeek = OrdersUtil
                    .sumTotalOrders(groupedListLastWeek, storeId);

            DataGenericSegmentDTO dtoWithValueCurrent = GetDataGenericSegmentDTOMappers
                    .valueSaleToDataGenericSegmentDTO(getTotalCurrent);

            DataGenericSegmentDTO dtoWithValueLastWeek = GetDataGenericSegmentDTOMappers
                    .valueSaleToDataGenericSegmentDTO(getTotalLastWeek);

            StoreSegmentDTO getChannel = GetStoreSaleDTOMappers.ordersSalesDTOToStoreSegmentDTO(
                    storeId,
                    GetOrdersSalesDTOMappers
                            .valueCurrentAndLastWeekToOrderSalesSegmentDTO(dtoWithValueCurrent, dtoWithValueLastWeek)
            );

            segmentDTOList.add(getChannel);
        }
        return segmentDTOList;
    }
}
