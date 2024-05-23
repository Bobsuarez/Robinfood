package com.robinfood.repository.services;

import com.robinfood.core.dtos.transactionrequestdto.ServiceDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IServicesRepository {

    CompletableFuture<Boolean> validateService(String token,
                                               List<ServiceDTO> servicesDTOList,
                                               TransactionRequestDTO transactionRequest);
}
