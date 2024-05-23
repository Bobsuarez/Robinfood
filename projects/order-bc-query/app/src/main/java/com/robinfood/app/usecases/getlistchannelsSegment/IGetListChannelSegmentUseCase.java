package com.robinfood.app.usecases.getlistchannelsSegment;

import com.robinfood.core.dtos.report.salebysegment.ChannelSegmentDTO;
import com.robinfood.core.entities.OrderEntity;

import java.util.List;

/**
 * Use case containing channel segment logic
 */
public interface IGetListChannelSegmentUseCase {

    List<ChannelSegmentDTO> invoke(List<OrderEntity> listOrderCurrent, List<OrderEntity> listOrdersLastWeek);
}
