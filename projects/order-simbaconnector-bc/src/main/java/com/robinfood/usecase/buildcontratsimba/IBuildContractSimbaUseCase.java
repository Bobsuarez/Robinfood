package com.robinfood.usecase.buildcontratsimba;

import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.SimbaContractDTO;

public interface IBuildContractSimbaUseCase {

    SimbaContractDTO invoke(TransactionRequestDTO transactionRequestDTO);
}
