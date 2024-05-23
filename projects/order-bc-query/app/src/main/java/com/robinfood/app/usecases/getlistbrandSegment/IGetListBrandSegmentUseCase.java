package com.robinfood.app.usecases.getlistbrandSegment;

import com.robinfood.core.dtos.report.salebysegment.BrandSegmentDTO;
import com.robinfood.core.entities.OrderEntity;

import java.util.List;

/**
 * Use case containing brand segment logic
 */
public interface IGetListBrandSegmentUseCase {

    List<BrandSegmentDTO> invoke(List<OrderEntity> listOrderCurrent, List<OrderEntity> listOrdersLastWeek);
}
