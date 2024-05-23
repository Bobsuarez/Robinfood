package com.robinfood.app.usecases.saverequestordercreated;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.NonNull;

public interface ISaveRequestOrderCreatedUseCase {

    void invoke(@NonNull TransactionRequestDTO transactionRequestDTO);
}
