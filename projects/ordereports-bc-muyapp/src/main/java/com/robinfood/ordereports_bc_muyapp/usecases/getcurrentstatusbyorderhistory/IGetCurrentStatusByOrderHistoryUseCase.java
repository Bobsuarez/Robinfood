package com.robinfood.ordereports_bc_muyapp.usecases.getcurrentstatusbyorderhistory;

import com.robinfood.ordereports_bc_muyapp.dto.OrderHistoryDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IGetCurrentStatusByOrderHistoryUseCase {

    CompletableFuture<OrderHistoryDTO> invoke(List<OrderHistoryDTO> historyDTOS, Short statusId);

    CompletableFuture<List<OrderHistoryDTO>> getListOrderHistory(Integer orderId);
}
