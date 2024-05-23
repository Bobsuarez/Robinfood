package com.robinfood.app.usecases.getsalebysegmentreport;

import com.robinfood.core.dtos.report.salebysegment.DataIdsToFindTheSegment;
import com.robinfood.core.entities.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IGetSaleBySegmentReportUseCase {

    CompletableFuture<List<OrderEntity>> invoke(
            DataIdsToFindTheSegment dataRequestDTO,
            LocalDateTime dateToSearch
    );
}
