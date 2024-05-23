package com.robinfood.repository.configuration.timezonesbycompany;

import com.robinfood.core.dtos.configuration.TimezonesByCompanyDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.core.mappers.TimezonesByCompanyMappers;
import com.robinfood.network.api.ConfigurationBcAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of ITimezonesByCompanyRepository
 */
@Repository
@Slf4j
public class TimezonesByCompanyRepository implements ITimezonesByCompanyRepository {

    private final ConfigurationBcAPI configurationBcAPI;

    public TimezonesByCompanyRepository(ConfigurationBcAPI configurationBcAPI) {
        this.configurationBcAPI = configurationBcAPI;
    }

    @Override
    public Result<TimezonesByCompanyDTO> getTimezonesByCompanyStores(String token, Long companyId) {

        final Result<APIResponseEntity<List<String>>> response = NetworkExtensionsKt.safeAPICall(
                configurationBcAPI.getTimezonesByCompanyId(token, companyId)
        );

        if (response instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) response;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<List<String>>> data = (
                (Result.Success<APIResponseEntity<List<String>>>) response
        );

        return new Result.Success(
                TimezonesByCompanyMappers.listStringToTimezonesByCompanyDTO(data.getData().getData())
        );
    }
}
