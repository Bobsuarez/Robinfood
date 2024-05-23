package com.robinfood.repository.electronicbill;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.core.util.ObjectMapperSingleton;
import com.robinfood.network.api.OrderBillNumberGeneratorBCAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.enums.logs.OrderErrorLogEnum.ERROR_GENERIC_FE;
import static com.robinfood.core.enums.logs.OrderErrorLogEnum.ERROR_SERVICES_UNAVAILABLE_FE;

@Slf4j
@Repository
public class ElectronicBillRemoteDataSource implements IElectronicBillRemoteDataSource{

    private final OrderBillNumberGeneratorBCAPI orderBillNumberGeneratorBCAPI;

    public ElectronicBillRemoteDataSource(OrderBillNumberGeneratorBCAPI orderBillNumberGeneratorBCAPI) {
        this.orderBillNumberGeneratorBCAPI = orderBillNumberGeneratorBCAPI;
    }

    @Override
    public Boolean sendElectronicBill(String token, TransactionRequestDTO transactionRequestDTO) {

        final Result<ApiResponseEntity> responseEntityResult = NetworkExtensionsKt.safeAPICall(
                orderBillNumberGeneratorBCAPI.sendElectronicBill(token, transactionRequestDTO));

        if (responseEntityResult instanceof Result.Error) {

            final Result.Error serviceError = (Result.Error) responseEntityResult;
            log.error(ERROR_GENERIC_FE.getMessage(), serviceError.getException().getMessage());

            if(((Result.Error) responseEntityResult).getHttpStatus().is5xxServerError()){

                log.error(ERROR_SERVICES_UNAVAILABLE_FE.getMessage(),
                        ObjectMapperSingleton.objectToJson(serviceError.getException().getMessage()));

                throw new ResponseStatusException(
                        serviceError.getHttpStatus(),
                        serviceError.getException().getLocalizedMessage()
                );
            }
        }

        log.info("Electronic bill send successfully");
        return Boolean.TRUE;
    }
}
