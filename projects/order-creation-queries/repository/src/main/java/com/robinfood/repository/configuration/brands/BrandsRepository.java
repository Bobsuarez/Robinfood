package com.robinfood.repository.configuration.brands;

import com.robinfood.core.dtos.configuration.BrandsDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.ConfigurationBcAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class BrandsRepository implements IBrandsRepository {

    private final ConfigurationBcAPI configurationBcAPI;

    public BrandsRepository(ConfigurationBcAPI configurationBcAPI) {
        this.configurationBcAPI = configurationBcAPI;
    }

    @Override
    public Result<BrandsDTO> getAll(
            String token
    ) {

        log.info("Invoke get configuration bc query, get brands");

        final Result<APIResponseEntity<BrandsDTO>> result = NetworkExtensionsKt.safeAPICall(
                configurationBcAPI.getBrands(token)
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<BrandsDTO>> data =
                ((Result.Success<APIResponseEntity<BrandsDTO>>) result);

        final BrandsDTO getBrands = data.getData().getData();

        return new Result.Success(getBrands);
    }
}
