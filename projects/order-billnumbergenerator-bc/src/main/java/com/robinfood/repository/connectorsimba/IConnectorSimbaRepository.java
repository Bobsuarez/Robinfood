package com.robinfood.repository.connectorsimba;

import com.robinfood.dtos.sendordertosimba.request.TransactionRequestDTO;

public interface IConnectorSimbaRepository {

    String invoke(TransactionRequestDTO transactionRequestDTO, String token);
}
