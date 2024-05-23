package com.robinfood.repository.tax;

import com.robinfood.core.dtos.ValidateTaxRequestDTO;
import com.robinfood.core.dtos.ValidateTaxResponseDTO;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;

/**
 * Repository that handles all taxes related data
 */
public interface ITaxRepository {

    /**
     * Retrieves the list of taxes for some final products
     * @param token the authorization token
     * @param validateTaxRequest the final products that will request their taxes
     * @return the final product taxes
     */
    @Async
    CompletableFuture<List<ValidateTaxResponseDTO>> getFinalProductsTaxes(
            String token,
            ValidateTaxRequestDTO validateTaxRequest
    );
}
