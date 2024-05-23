package com.robinfood.ordereports_bc_muyapp.usecases.getorderbyuid;

import com.robinfood.ordereports_bc_muyapp.dto.TransactionFlowDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDetailDTO;

import java.util.concurrent.CompletableFuture;

public interface IGetOrderByOrderIdUseCase {

    /**
     * Method that searches for a transaction
     *
     * @param transactionId transactionId
     *
     * @return {@link TransactionFlowDTO}
     */
    CompletableFuture<ResponseOrderDetailDTO> invoke(Integer transactionId);
}
