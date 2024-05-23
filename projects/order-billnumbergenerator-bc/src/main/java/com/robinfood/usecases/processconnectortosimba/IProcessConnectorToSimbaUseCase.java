package com.robinfood.usecases.processconnectortosimba;

import com.robinfood.dtos.sendordertosimba.request.TransactionRequestDTO;
import com.robinfood.dtos.sendordertosimba.response.TransactionResponseDTO;

public interface IProcessConnectorToSimbaUseCase {
    TransactionResponseDTO invoke(TransactionRequestDTO transactionRequestDTO, String token);
}
