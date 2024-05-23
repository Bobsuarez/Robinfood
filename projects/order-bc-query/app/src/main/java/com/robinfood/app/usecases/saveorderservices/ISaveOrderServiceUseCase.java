package com.robinfood.app.usecases.saveorderservices;

import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ISaveOrderServiceUseCase {

    CompletableFuture<Boolean> invoke (RequestOrderTransactionDTO requestOrderTransactionDTO,
                                       List<Long> orderIds);

}
