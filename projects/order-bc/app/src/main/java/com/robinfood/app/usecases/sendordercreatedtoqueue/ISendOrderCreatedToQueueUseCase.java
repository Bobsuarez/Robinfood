package com.robinfood.app.usecases.sendordercreatedtoqueue;

import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.dtos.response.transaction.ResponseCreatedOrderTransactionDTO;

public interface ISendOrderCreatedToQueueUseCase {

    void invoke(
        RequestOrderTransactionDTO orderTransactionDTO,
        ResponseCreatedOrderTransactionDTO responseCreatedOrderTransactionDTO
    );

}
