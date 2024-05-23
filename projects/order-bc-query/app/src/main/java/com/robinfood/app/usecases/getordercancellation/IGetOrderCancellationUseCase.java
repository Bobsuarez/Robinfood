package com.robinfood.app.usecases.getordercancellation;

import com.robinfood.core.dtos.report.ordercancellation.DataToSearchIdsCanceledOrdersDTO;
import com.robinfood.core.dtos.report.ordercancellation.GetOrderCancellationResponseDTO;
import com.robinfood.core.dtos.response.EntityDTO;

/**
 * Use case that contain the logic for the cancellation's report
 */
public interface IGetOrderCancellationUseCase {

    EntityDTO<GetOrderCancellationResponseDTO> invoke(DataToSearchIdsCanceledOrdersDTO dataContainingTheSearchIds);
}
