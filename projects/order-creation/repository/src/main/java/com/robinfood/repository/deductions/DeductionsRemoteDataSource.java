package com.robinfood.repository.deductions;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.network.api.DeductionsApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.robinfood.core.enums.TransactionCreationErrors.GET_POS_ID_ERROR;
import static com.robinfood.core.extensions.NetworkExtensionsKt.safeAPICall;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@Component
@Slf4j
public class DeductionsRemoteDataSource implements IDeductionsRemoteDataSource{

    private final DeductionsApi deductionsApi;

    public DeductionsRemoteDataSource(DeductionsApi deductionsApi) {
        this.deductionsApi = deductionsApi;
    }

    @Override
    public Map<Long, String> getAllActiveTypeDeductions(String token) {

        log.info("Going out to get all active deductions");

        Result<ApiResponseEntity<Map<Long,String>>> response = safeAPICall(
                deductionsApi.getAllActiveTypeDeductions(token));

        if (response instanceof Result.Error) {
            final Result.Error error = (Result.Error) response;

            throw new TransactionCreationException(
                    error.component1().getMessage(),
                    "Error obtained all the active type deductions",
                    GET_POS_ID_ERROR,
                    PRECONDITION_FAILED
            );
        }
        return ((Result.Success<ApiResponseEntity<Map<Long,String>>>) response).getData()
                .getData();
    }
}
