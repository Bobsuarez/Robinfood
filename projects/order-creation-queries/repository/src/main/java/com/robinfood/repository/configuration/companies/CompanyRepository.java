package com.robinfood.repository.configuration.companies;

import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.ConfigurationBcAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class CompanyRepository implements ICompanyRepository {

    private final ConfigurationBcAPI configurationBcAPI;

    public CompanyRepository(ConfigurationBcAPI configurationBcAPI) {
        this.configurationBcAPI = configurationBcAPI;
    }

    @Override
    public Result<CompaniesDTO> getActive(String token) {

        final Integer ACTIVE = 1;

        final Result<APIResponseEntity<CompaniesDTO>> result = NetworkExtensionsKt.safeAPICall(
                configurationBcAPI.getCompanies(
                        token,
                        ACTIVE
                )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<CompaniesDTO>> data =
                ((Result.Success<APIResponseEntity<CompaniesDTO>>) result);

        return new Result.Success(
                data.getData().getData()
        );
    }
}
