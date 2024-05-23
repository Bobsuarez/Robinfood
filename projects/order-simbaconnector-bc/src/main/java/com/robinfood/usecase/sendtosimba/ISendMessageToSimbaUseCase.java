package com.robinfood.usecase.sendtosimba;

import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.response.OrderElectronicBillingDTO;

public interface ISendMessageToSimbaUseCase {

    OrderElectronicBillingDTO invoke(TransactionRequestDTO transactionRequestDTO);
}
