package com.robinfood.usecases.sendmessagetosimba;

import com.robinfood.dtos.sendordertosimba.request.TransactionRequestDTO;

public interface ISendMessageToSimbaUseCase {

    String invoke(TransactionRequestDTO transactionRequestDTO, String token);

}
