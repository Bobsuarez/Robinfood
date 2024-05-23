package com.robinfood.repository.tax;

import com.robinfood.core.entities.ValidateTaxRequestEntity;
import com.robinfood.core.entities.ValidateTaxResponseEntity;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Remote data source that handles all taxes related data
 */
public interface ITaxRemoteDataSource {

    /**
     * Retrieves the list of taxes for some final products
     * @param token the authorization token
     * @param validateTaxRequest the final products that will request their taxes
     * @return the final product taxes
     */
    CompletableFuture<List<ValidateTaxResponseEntity>> getFinalProductsTaxes(
            String token,
            ValidateTaxRequestEntity validateTaxRequest
    );
}
