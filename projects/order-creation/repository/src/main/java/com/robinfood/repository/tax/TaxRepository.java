package com.robinfood.repository.tax;

import com.robinfood.core.dtos.ValidateTaxRequestDTO;
import com.robinfood.core.dtos.ValidateTaxResponseDTO;
import com.robinfood.core.entities.ValidateTaxRequestEntity;
import com.robinfood.core.mappers.ValidateTaxMappers;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;

import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of ITaxRepository
 */
@Slf4j
public class TaxRepository implements ITaxRepository {

    private final ITaxRemoteDataSource taxRemoteDataSource;

    public TaxRepository(ITaxRemoteDataSource taxRemoteDataSource) {
        this.taxRemoteDataSource = taxRemoteDataSource;
    }

    @Override
    public CompletableFuture<List<ValidateTaxResponseDTO>> getFinalProductsTaxes(
            String token,
            ValidateTaxRequestDTO validateTaxRequest
    ) {
        log.info("Going out to get final products taxes with data: {}", objectToJson(validateTaxRequest));

        final ValidateTaxRequestEntity validateTaxRequestEntity = ValidateTaxMappers.toValidateTaxRequestEntity(
                validateTaxRequest);
        return taxRemoteDataSource.getFinalProductsTaxes(token, validateTaxRequestEntity).thenApply(
                ValidateTaxMappers::toValidateTaxResponseDTOList
        );
    }
}
