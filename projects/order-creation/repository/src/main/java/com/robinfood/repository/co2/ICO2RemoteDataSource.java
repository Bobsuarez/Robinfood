package com.robinfood.repository.co2;

import com.robinfood.core.entities.CO2CalculateRequestEntity;
import com.robinfood.core.entities.CO2CalculateResponseEntity;

/**
 * Data source that handles remote related co2 calculations
 */
public interface ICO2RemoteDataSource {

    /**
     * Method that invokes the remote data source to obtain the co2 compensation calculation
     * @param token token from SSO
     * @param request order products info
     * @return co2 compensation calculation
     */
    CO2CalculateResponseEntity calculateCO2Compensation(
        String token,
        CO2CalculateRequestEntity request
    );
}
