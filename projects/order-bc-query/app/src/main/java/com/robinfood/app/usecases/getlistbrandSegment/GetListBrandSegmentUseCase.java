package com.robinfood.app.usecases.getlistbrandSegment;

import com.robinfood.app.mappers.salesbysegment.GetBrandSaleDTOMappers;
import com.robinfood.app.mappers.salesbysegment.GetDataGenericSegmentDTOMappers;
import com.robinfood.app.mappers.salesbysegment.GetOrdersSalesDTOMappers;
import com.robinfood.core.dtos.report.salebysegment.BrandSegmentDTO;
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
public class GetListBrandSegmentUseCase implements IGetListBrandSegmentUseCase {

    @Override
    public List<BrandSegmentDTO> invoke(List<OrderEntity> listOrderCurrent, List<OrderEntity> listOrdersLastWeek) {

        List<BrandSegmentDTO> brandDTOList = new ArrayList<>();

        Map<Long, List<OrderEntity>> groupedListCurrent = OrdersUtil
                .groupedMultipleByEntityAttribute(listOrderCurrent, OrderEntity::getBrandId);

        Map<Long, List<OrderEntity>> groupedListLastWeek = OrdersUtil
                .groupedMultipleByEntityAttribute(listOrdersLastWeek, OrderEntity::getBrandId);

        List<Long> brandIds = OrdersUtil.findAndGroupSegmentIds(groupedListCurrent, groupedListLastWeek);

        log.info("The identity is built by brand identifier : {}", brandIds);

        for (Long brandId : brandIds) {

            BigDecimal getTotalCurrent = OrdersUtil.sumTotalOrders(groupedListCurrent, brandId);
            BigDecimal getTotalLastWeek = OrdersUtil.sumTotalOrders(groupedListLastWeek, brandId);

            DataGenericSegmentDTO dtoWithValueCurrent = GetDataGenericSegmentDTOMappers
                    .valueSaleToDataGenericSegmentDTO(getTotalCurrent);

            DataGenericSegmentDTO dtoWithValueLastWeek = GetDataGenericSegmentDTOMappers
                    .valueSaleToDataGenericSegmentDTO(getTotalLastWeek);

            BrandSegmentDTO getBrand = GetBrandSaleDTOMappers.brandIdAndOrderSalesDTOTOBrandSegmentDTO(
                    brandId,
                    GetOrdersSalesDTOMappers
                            .valueCurrentAndLastWeekToOrderSalesSegmentDTO(dtoWithValueCurrent, dtoWithValueLastWeek)
            );

            brandDTOList.add(getBrand);
        }
        return brandDTOList;
    }
}
