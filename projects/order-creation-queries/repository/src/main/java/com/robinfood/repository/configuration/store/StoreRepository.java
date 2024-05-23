package com.robinfood.repository.configuration.store;

import com.robinfood.core.dtos.configuration.StoresDTO;
import com.robinfood.core.dtos.configuration.StoreDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.configurations.StoreEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.core.mappers.StoreMappers;
import com.robinfood.network.api.ConfigurationBcAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.SORT_ENTITY;

/**
 * Implementation of IStoreRepository
 */
@Repository
@Slf4j
public class StoreRepository implements IStoreRepository {

    private final ConfigurationBcAPI configurationBcAPI;

    public StoreRepository(ConfigurationBcAPI configurationBcAPI) {
        this.configurationBcAPI = configurationBcAPI;
    }

    public Result<StoreDTO> getStore(Long storeId, String token) {

        final Result<APIResponseEntity<StoreEntity>> result = NetworkExtensionsKt.safeAPICall(
                configurationBcAPI.getStore(
                        storeId,
                        token
                )
        );

        if (result instanceof Result.Error) {

            log.info("Configuration store result error");

            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<StoreEntity>> data =
                ((Result.Success<APIResponseEntity<StoreEntity>>) result);

        log.info("Configuration store result successfully");
        return new Result.Success(
                StoreMappers.storeEntityToStoreDTO(data.getData().getData())
        );
    }

    @Override
    public Result<List<StoreDTO>> getStores(String token) {

        final Result<APIResponseEntity<StoresDTO>> result = NetworkExtensionsKt.safeAPICall(
                configurationBcAPI.getStores(SORT_ENTITY, token)
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<StoresDTO>> data =
                ((Result.Success<APIResponseEntity<StoresDTO>>) result);

        return new Result.Success(data.getData().getData().getContent());
    }
}
