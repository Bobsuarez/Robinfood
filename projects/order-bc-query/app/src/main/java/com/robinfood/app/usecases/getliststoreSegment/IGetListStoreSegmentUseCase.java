package com.robinfood.app.usecases.getliststoreSegment;

import com.robinfood.core.dtos.report.salebysegment.StoreSegmentDTO;
import com.robinfood.core.entities.OrderEntity;

import java.util.List;

/**
 * Use case containing store segment logic
 */
public interface IGetListStoreSegmentUseCase {

    List<StoreSegmentDTO> invoke(List<OrderEntity> listOrdersCurrent, List<OrderEntity> listOrdersLastWeek);
}
