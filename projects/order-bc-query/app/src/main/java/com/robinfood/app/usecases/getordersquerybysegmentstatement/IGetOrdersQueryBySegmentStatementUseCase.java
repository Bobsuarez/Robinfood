package com.robinfood.app.usecases.getordersquerybysegmentstatement;

import com.robinfood.core.dtos.report.salebysegment.DataIdsToFindTheSegment;
import com.robinfood.core.entities.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface IGetOrdersQueryBySegmentStatementUseCase {

    List<OrderEntity> invoke(LocalDateTime dateToSearch ,DataIdsToFindTheSegment dataRequestDTO);
}
