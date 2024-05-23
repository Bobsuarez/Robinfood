package com.robinfood.app.usecases.ordersquerystatement;

import com.robinfood.core.dtos.report.ordercancellation.DataToSearchIdsCanceledOrdersDTO;
import com.robinfood.core.entities.OrderEntity;
import org.springframework.data.domain.Page;

public interface IOrdersQueryStatementUseCase {

    Page<OrderEntity> invoke(DataToSearchIdsCanceledOrdersDTO dataRequestDTO);
}
