package com.robinfood.repository.tax;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.ValidateTaxRequestEntity;
import com.robinfood.core.entities.ValidateTaxResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.core.logging.mappeddiagnosticcontext.CreateTransactionCustomLog;
import com.robinfood.network.api.TaxesBCAPI;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of ITaxRemoteDataSource
 */
public class TaxRemoteDataSource implements ITaxRemoteDataSource {

    private final TaxesBCAPI taxesBCAPI;

    public TaxRemoteDataSource(TaxesBCAPI taxesBCAPI) {
        this.taxesBCAPI = taxesBCAPI;
    }

    @Override
    public CompletableFuture<List<ValidateTaxResponseEntity>> getFinalProductsTaxes(
            String token,
            ValidateTaxRequestEntity validateTaxRequest
    ) {
        final Result<ApiResponseEntity<List<ValidateTaxResponseEntity>>> result = NetworkExtensionsKt
                .safeAPICall(taxesBCAPI.getFinalProductsTaxes(token, validateTaxRequest));
        if (result instanceof Result.Error) {
            final Result.Error error = ((Result.Error) result);
            throw new TransactionCreationException(
                    error.getHttpStatus(),
                    error.getException().getLocalizedMessage(),
                    TransactionCreationErrors.TAXES_BC_ERROR
            );
        }
        CreateTransactionCustomLog.removeHits();
        return CompletableFuture.completedFuture(
                ((Result.Success<ApiResponseEntity<List<ValidateTaxResponseEntity>>>) result)
                        .getData()
                        .getData()
        );
    }
}
